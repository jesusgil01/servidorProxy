/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public interface DataBase {
    public Connection Open();
    public void Close();
    public DefaultTableModel Select(String queryString)throws SQLException;
    public boolean Insert(String queryString)throws SQLException;
    //public int CreateTable(String queryString)throws SQLException;
}
