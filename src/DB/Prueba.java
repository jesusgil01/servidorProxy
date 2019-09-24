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
        
        // Clase de pruebas
        
        Factory mifactoria = new FactoryIMPL();
        DataBase postgresql = mifactoria.CreaConexion("PostgreSQL");
        //postgresql.Open();
        DefaultTableModel dtm = postgresql.Select("SELECT id, nombre, direccion, telefono, encargadoid\n" +
"	FROM public.\"Tiendas\";");
        
         //System.out.println(dtm.getColumnName(0));
         System.out.println(dtm.getValueAt(0, 0));
         
         boolean a = postgresql.Insert("INSERT INTO public.\"Usuarios\"(\n" +
"	nombre, apellidos, fechanaci, genero, curp, rfc, estadocivil, telefono, email, rol, username, password, id_tienda, salario, direccion)\n" +
"	VALUES ('Hector', 'Valenzuela', '1999-07-28', 'M', 'VIVJS99072826H807', 'VIVJ990728GG2', \n" +
"			'Soltero', 6621485453, 'hectorvlbs@gmail.com', 'Empleado', 'Hectorr', 'toor1234', 1, 10000, 'Sin nombre #270');");
         
         System.out.println(a);
         
        
    }
}
