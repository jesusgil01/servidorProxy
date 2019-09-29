package RemoteProxy;

import java.rmi.Remote;
import Validator.*;
import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;

public interface ReportGenerator extends Remote {

    public boolean insertUsuario(NewUserInfo user) throws RemoteException;

    public boolean insertTienda(String nombre, String direccion, String telefono, String encargadoid, String ventas) throws RemoteException;

    public DefaultTableModel consultaTiendas() throws RemoteException;

    public DefaultTableModel ventasTodasSucursales() throws RemoteException;

    public DefaultTableModel ventasDeSucursal(String sucursal) throws RemoteException;

    public DefaultTableModel usuariosTotales() throws RemoteException;

    public DefaultTableModel usuariosPorNombre(String nombre, String apellido) throws RemoteException;

    public DefaultTableModel usuariosSucursal(String nombreSucursal) throws RemoteException;

    public boolean login(String usuario, String password, String rol) throws RemoteException;
}
