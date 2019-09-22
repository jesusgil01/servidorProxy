package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class PostgreSql implements DataBase {
    
    
    
    Connection connection;
    public static final String HOSTNAME = "localhost";
    public static final String DBNAME   = "dbds413";
    public static final String USERNAME = "usuario";
    public static final String PASSWORD = "toor";
    
    
    public boolean Open(){
        String URL="jdbc:postgresql://"+HOSTNAME+":5432/"+DBNAME;
        try {
             Class.forName("org.postgresql.Driver");
             connection = (Connection)DriverManager.getConnection(URL, USERNAME, PASSWORD);
             System.out.println("Hola entro");
             return true;
        }    catch(Exception e){
             System.out.println(e);
        }
        return false;
    }
    public  ResultSet Query(String queryString) throws SQLException{
        
        PreparedStatement ps;
        ResultSet res;
        
        ps = connection.prepareStatement(queryString);
        res = ps.executeQuery();
        return res;
        
    }
    
    
    public boolean Close(){
        
        return true;
    }   

    @Override
    public int CreateTable(String queryString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
