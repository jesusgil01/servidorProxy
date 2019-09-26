/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author villalobos28
 */
public class Prueba {

    public static void main(String[] args) throws SQLException {

        // Clase de pruebas
        Factory mifactoria = new FactoryIMPL();
        DataBase postgresql = mifactoria.CreaConexion("MySQL");

        String select = "SELECT `Tiendas`.`id`,\n"
                + "    `Tiendas`.`nombre`,\n"
                + "    `Tiendas`.`direccion`,\n"
                + "    `Tiendas`.`telefono`,\n"
                + "    `Tiendas`.`encargadoid`,\n"
                + "    `Tiendas`.`ventas`\n"
                + "FROM `dbds413`.`Tiendas`;";
        
        
        String insert = "INSERT INTO `dbds413`.`Tiendas`\n"
                + "(`nombre`,\n"
                + "`direccion`,\n"
                + "`telefono`,\n"
                + "`encargadoid`,\n"
                + "`ventas`)\n"
                + "VALUES\n"
                + "(\"Quiroga\",\n"
                + "\"Boulevard Quiroga\",\n"
                + "\"6621485411\",\n"
                + "002,\n"
                + "10400.10);";

        //postgresql.Insert(insert);
        
        //Connection con = postgresql.Open();
        

        DefaultTableModel dtm = postgresql.Select(select);

        //dtm.getValueAt(0, 0);
        System.out.println(dtm.getValueAt(3, 3).toString());

    }
}
