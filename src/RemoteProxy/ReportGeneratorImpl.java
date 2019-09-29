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
    String query = "";

    protected ReportGeneratorImpl() throws RemoteException {
    }

    @Override
    public boolean insertUsuario(NewUserInfo user) {

        query = consulta.insertUsuario(user);
        System.out.println(query);

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
    public boolean insertTienda(String nombre, String direccion, String telefono, String encargadoid, String ventas) throws RemoteException {

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
    public DefaultTableModel consultaTiendas() throws RemoteException {

        query = consulta.consultaTiendas();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel ventasTodasSucursales() throws RemoteException {
        query = consulta.ventasTodasSucursales();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel ventasDeSucursal(String sucursal) throws RemoteException{
        query = consulta.ventasDeSucursal(sucursal);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosTotales() throws RemoteException {
        query = consulta.usuariosTotales();

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosPorNombre(String nombre, String apellido) throws RemoteException {
        query = consulta.usuariosPorNombre(nombre, apellido);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel usuariosSucursal(String nombreSucursal) throws RemoteException {
        query = consulta.usuariosSucursal(nombreSucursal);

        try {
            dt = db.Select(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    @Override
    public boolean login(String usuario, String password, String rol) throws RemoteException {
        System.out.println("ll");

        query = consulta.login(usuario, password, rol);
        
        try {
            if (db.login(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static void main(String[] args) {

        try {
            System.out.println("Corriendo");
            Registry reg = LocateRegistry.createRegistry(4513);
            ReportGenerator reportGenerator = new ReportGeneratorImpl();
            reg.rebind("ServidorProxy", reportGenerator);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
