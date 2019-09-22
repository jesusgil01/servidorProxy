package RemoteProxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReportGenerator extends Remote {

    public String generateDailyReport(String name) throws RemoteException;

    public boolean login(String user, String password, String rol) throws RemoteException;

    public ArrayList<String> getTiendas() throws RemoteException;

    public String getInfoSucursal(String name) throws RemoteException;

    public boolean addUser(String name, String pass, String rol) throws RemoteException;

}
