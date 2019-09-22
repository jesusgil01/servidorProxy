
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQL implements DataBase {
    
    Connection connection;
    public static final String HOSTNAME = "localhost";
    public static final String DBNAME   = "alumnos";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "pedro1234";
    
    public boolean Open(){
        
        
        String connectionUrl =
                "jdbc:sqlserver://"+HOSTNAME+".database.windows.net:1433;"
                + "database=AdventureWorks;"
                + "user=yourusername@"+HOSTNAME+";"
                + "password="+PASSWORD+";"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";
        
        
        try  {
    
            connection = DriverManager.getConnection(connectionUrl);
             return true;
         }   catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    @Override
    public boolean Close(){
        return true;
    }

    @Override
    public ResultSet Query(String queryString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int CreateTable(String queryString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
