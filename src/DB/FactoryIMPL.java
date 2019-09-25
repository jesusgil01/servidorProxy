/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author villalobos28
 */
public class FactoryIMPL implements Factory {

    public final static String POSTGRESQL = "PostgreSQL";
    public final static String MYSQL = "MySQL";
    
    @Override
    public DataBase CreaConexion(String DBMS) {
        switch (DBMS){
            case POSTGRESQL:
                return new PostgreSQL();
            case MYSQL:
                return new MySQL();
        }
        return null;
    }
    
}
