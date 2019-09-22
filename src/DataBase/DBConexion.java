package DataBase;

import java.sql.CallableStatement;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DBConexion {
    
    public Connection miConexion;
    private final String servidor;
    private final String puerto;
    private final String baseDatos;
    private final String contrasena;
    private final String usuario;
    
    public DBConexion(String servidor, String puerto, String baseDatos, String usuario, String contrasena) {
       this.servidor = servidor;
       this.puerto = puerto;
       this.baseDatos = baseDatos;
       this.usuario = usuario;
       this.contrasena = contrasena;
    }
    
    public void Conectar() {
        try {
            miConexion = DriverManager.getConnection( 
                        "jdbc:mysql://" + servidor.trim() + ":" + puerto.trim() + "/" + baseDatos.trim() + "?" +
                        "allowPublicKeyRetrieval=true&useSSL=false&user=" + usuario.trim() + "&password=" + contrasena.trim());                             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null
                                    , "Excepción: " + e.toString());
        }
    }
    
    public void Desconectar() {
        try {
                // if( st != null ) st.close();
                if( miConexion != null ) miConexion.close();
            } catch (Exception ex) {
                System.out.println("Excepción: " + ex.toString());
        }
    }
    
    
    public String ejecutaStoreProcedure( String consulta
                                        , CallableStatement cs) {
        
        String mensaje = "Falló";
        try {
            cs = miConexion.prepareCall(consulta);
            /*
              IN pIDSucursal int
            , IN pNombre varchar(45)
            , IN pDireccion varchar(45) 
            , IN pIDCiudad int
            , OUT pMensaje varchar(100)
            */
            
            cs.setInt(1, 0);
            cs.setString(2, "Ramos Software");
            cs.setString(3, "Enrique Segoviano #345");
            cs.setInt(4, 1);
            cs.registerOutParameter(5, Types.VARCHAR);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, cs.getString(5));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null
                                    , "Excepción: " + e.toString());
        }       
        
        
        return mensaje;
    }
    
    
    public boolean ejecutaInsert(String consulta) {
        Statement st = null;
        int regreso = 0;
        try { // try-catch-finally
            miConexion = DriverManager.getConnection( 
                        "jdbc:mysql://" + servidor.trim() + ":" + puerto.trim() + "/" + baseDatos.trim() + "?" +
                        "allowPublicKeyRetrieval=true&useSSL=false&user=" + usuario.trim() + "&password=" + contrasena.trim());                             
            // Inicializar Statement y Resulset
            st = miConexion.createStatement();
            regreso = st.executeUpdate(consulta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            // System.out.println("Excepción: " + ex.toString());
        } 
        finally {
            // Cierra o null los objetos
            try {
                if( st != null ) st.close();
                if( miConexion != null ) miConexion.close();
            } catch (Exception ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }  
        
        // return regreso > 0;
        if( regreso > 0 ) {
            return true;
        }
        
        return false;
        
    }
    
    public DefaultTableModel ejecutaSelect(String consulta) {
        Statement st = null;
        ResultSet rs = null;
        DefaultTableModel dtm = new DefaultTableModel();
        
        try { // try-catch-finally
            miConexion = DriverManager.getConnection( 
                        "jdbc:mysql://" + servidor.trim() + ":" + puerto.trim() + "/" + baseDatos.trim() + "?" +
                        "allowPublicKeyRetrieval=true&useSSL=false&user=" + usuario.trim() + "&password=" + contrasena.trim());                             
            // Inicializar Statement y Resulset
            st = miConexion.createStatement();
            // rs = st.executeQuery( "SELECT expediente, nombre, sexo, activo FROM Alumno");
            rs = st.executeQuery(consulta);
            
            // Obtiene Meta Datos de la consulta que regresa.
            // En ete caso Número y Nombre de Columnas
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();           
            // Agregamos las columas en mi modelo de datos
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dtm.addColumn(rsmd.getColumnName(i));
            }
            // Agregamos los registos al modelo de datos
            int numeroRegistros = 0;
            if( rs.last() ) // Pone el cursor en el último registro
                numeroRegistros = rs.getRow(); // Obtiene el número de registros
            rs.beforeFirst(); // Pone el cursor antes del primer registo
            
            if( numeroRegistros > 0 ) {
                // Se utiliza un arreglo de Object para obtener los datos, ya que son de diferentes tipos
                Object[] registros = new Object[rsmd.getColumnCount()];
                while( rs.next() ) {
                    // Por cada columna obtiene el valor de la celda
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // registros obtiene i, ya que un arreglo empieza en la posición 0
                        // rs.getObject(i+1) ya que la columna empieza en 1, no 0
                        registros[i] = rs.getObject(i+1);
                    }
                    // Agrega un registro de datos al modelo
                    dtm.addRow(registros);
                }
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            System.out.println("Excepción: " + ex.toString());
        } 
        finally {
            // Cierra o null los objetos
            try {
                if( rs != null ) rs.close();
                if( st != null ) st.close();
                if( miConexion != null ) miConexion.close();
            } catch (Exception ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }     
        
        return dtm; // Regresa un modelo de datos para ser enlazado a una Table o similar
    }
    
    
        
    
}
