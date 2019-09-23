/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author villalobos28
 */
public class Prueba {
    public static void main (String [] args) throws SQLException{
        Factory mifactoria = new FactoryIMPL();
        DataBase postgresql = mifactoria.CreaConexion("PostgreSQL");
        //postgresql.Open();
        DefaultTableModel dtm = postgresql.Select("SELECT id, nombre, direccion, telefono, encargadoid\n" +
"	FROM public.\"Tiendas\";");
        
         //System.out.println(dtm.getColumnName(0));
         System.out.println(dtm.getValueAt(0, 0));
         
        
    }
}
