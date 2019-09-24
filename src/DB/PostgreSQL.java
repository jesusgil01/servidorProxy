/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author villalobos28
 */
public class PostgreSQL implements DataBase {

    public static Connection CONNECTION;
    public static final String HOSTNAME = "localhost";
    public static final String DBNAME = "dbds413";
    public static final String USERNAME = "usuario";
    public static final String PASSWORD = "toor";
    public static final String PORT = "5432";

    @Override
    public Connection Open() {
        String URL = "jdbc:postgresql://" + HOSTNAME + ":5432/" + DBNAME;
        try {
            Class.forName("org.postgresql.Driver");
            CONNECTION = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (CONNECTION.isReadOnly()) {
                System.out.println("si");
            }
            return CONNECTION;

        } catch (Exception e) {
            System.out.println(e + "...");
        }
        return null;
    }

    @Override
    public void Close() {
        try {
            if (CONNECTION != null) {
                CONNECTION.close();
                System.out.println("Se cerro");
            }
        } catch (Exception ex) {
            System.out.println("Excepción: " + ex.toString());
        }
    }

    @Override
    public DefaultTableModel Select(String queryString) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        DefaultTableModel dtm = new DefaultTableModel();

        try { // try-catch-finally
            CONNECTION = Open();

            // Inicializar Statement y Resulset
            st = CONNECTION.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // rs = st.executeQuery( "SELECT expediente, nombre, sexo, activo FROM Alumno");
            rs = st.executeQuery(queryString);

            // Obtiene Meta Datos de la consulta que regresa.
            // En ete caso Número y Nombre de Columnas
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            // Agregamos las columas en mi modelo de datos
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dtm.addColumn(rsmd.getColumnName(i));
            }
            // Agregamos los registos al modelo de datos
            int numeroRegistros = 0;
            if (rs.last()) // Pone el cursor en el último registro
            {
                numeroRegistros = rs.getRow(); // Obtiene el número de registros
            }
            rs.beforeFirst(); // Pone el cursor antes del primer registo

            if (numeroRegistros > 0) {
                // Se utiliza un arreglo de Object para obtener los datos, ya que son de diferentes tipos
                Object[] registros = new Object[rsmd.getColumnCount()];
                while (rs.next()) {
                    // Por cada columna obtiene el valor de la celda
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // registros obtiene i, ya que un arreglo empieza en la posición 0
                        // rs.getObject(i+1) ya que la columna empieza en 1, no 0
                        registros[i] = rs.getObject(i + 1);
                    }
                    // Agrega un registro de datos al modelo
                    dtm.addRow(registros);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            System.out.println("Excepción: " + ex.toString());
        } finally {
            // Cierra o null los objetos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (CONNECTION != null) {
                    CONNECTION.close();
                }
            } catch (SQLException ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }

        return dtm; // Regresa un modelo de datos para ser enlazado a una Table o similar
    }

    @Override
    public boolean Insert(String queryString) throws SQLException {
        Statement st = null;
        int regreso = 0;
        try { // try-catch-finally
            CONNECTION = Open();
            // Inicializar Statement y Resulset
            st = CONNECTION.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            regreso = st.executeUpdate(queryString);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            // System.out.println("Excepción: " + ex.toString());
        } finally {
            // Cierra o null los objetos
            try {
                if (st != null) {
                    st.close();
                }
                if (CONNECTION != null) {
                    CONNECTION.close();
                }
            } catch (Exception ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }
        // return regreso > 0;
        if (regreso > 0) {
            return true;
        }

        return false;

    }

}
