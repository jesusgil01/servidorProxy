/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public interface DataBase {
    
    public boolean Open();
    public ResultSet Query(String queryString)throws SQLException;
    public boolean Close();
    public int CreateTable(String queryString)throws SQLException;
    
}
