package RemoteProxy;

import DB.DataBase;
import DB.Factory;
import DB.FactoryIMPL;
import Validator.NewUserInfo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Consultas.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator {

    private static final long serialVersionUID = 3107413009881629428L;

    public final static String Manejador = "MySQL";
    
    Factory mifactoria = new FactoryIMPL();
    DataBase db = mifactoria.CreaConexion(Manejador);
    
    ConsultaMySQL consulta = new ConsultaMySQL();
    //ConsultaSQL consulta = new ConsultaSQL();
    //ConsultasPostgreSQL consulta = new ConsultasPostgreSQL();
    
    DefaultTableModel dt;

    protected ReportGeneratorImpl() throws RemoteException {
    }

    @Override
    public boolean insertUsuario(NewUserInfo user, String query) {

        query = consulta.insertUsuario(user);

        try {

            if (db.Insert(query) == true) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean insertTienda(String nombre, String direccion, String telefono, String encargadoid, String ventas, String query) {

        query = consulta.insertTienda(nombre, direccion, telefono, encargadoid, ventas);

        try {

            if (db.Insert(query) == true) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public DefaultTableModel consultaTiendas(String query) {

        query = consulta.consultaTiendas();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel ventasTodasSucursales(String query) {
        query = consulta.ventasTodasSucursales();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel ventasDeSucursal(String sucursal, String query) {
        query = consulta.ventasDeSucursal(sucursal);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosTotales(String query) {
        query = consulta.usuariosTotales();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosPorNombre(String nombre, String apellido, String query) {
        query = consulta.usuariosPorNombre(nombre, apellido);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosSucursal(String nombreSucursal, String query) {
        query = consulta.usuariosSucursal(nombreSucursal);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    public static void main(String[] args) {

        try {
            Registry reg = LocateRegistry.createRegistry(3458);
            ReportGenerator reportGenerator = new ReportGeneratorImpl();
            reg.rebind("PizzaCoRemoteGenerator", reportGenerator);

        } catch (RemoteException e) {
        }

    }

}
