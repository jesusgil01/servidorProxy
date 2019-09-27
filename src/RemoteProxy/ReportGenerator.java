package RemoteProxy;

import java.rmi.Remote;
import Validator.*;
import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;

public interface ReportGenerator extends Remote {
    
    // DefaultTableModel dtm
    
    public boolean insertUsuario (NewUserInfo user, String query);
    
    public boolean insertTienda (String nombre, String direccion, String telefono, String encargadoid, String ventas, String query);
    
    public DefaultTableModel consultaTiendas (String query);
    
    public DefaultTableModel ventasTodasSucursales(String query);
    
    public DefaultTableModel ventasDeSucursal(String sucursal, String query);
    
    public DefaultTableModel usuariosTotales(String query);
    
    public DefaultTableModel usuariosPorNombre(String nombre, String apellido, String query);
    
    public DefaultTableModel usuariosSucursal(String nombreSucursal, String query);
}
