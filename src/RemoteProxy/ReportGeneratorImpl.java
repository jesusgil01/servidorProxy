package RemoteProxy;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator {

    private static final long serialVersionUID = 3107413009881629428L;
    Tiendas t = new Tiendas();
    static Usuarios u = Usuarios.getInstance();
    ArrayList<Usuario> list = u.returnList();

    protected ReportGeneratorImpl() throws RemoteException {
    }

    @Override
    public String generateDailyReport(String name) throws RemoteException {

        String report = "";

        Tienda sucursal = null;
        ArrayList<Tienda> lista = t.regresaLista();
        for (Tienda e : lista) {
            if (e.getNombre().compareTo(name) == 0) {
                sucursal = e;
                break;
            }
        }

        if (sucursal == null) {
            report = "La sucursal seleccionada no existe";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("********************Location X Daily Report********************");
            sb.append("\n Location Name: " + sucursal.getNombre());
            sb.append("\n Location ID: " + sucursal.getCodigo());
            sb.append("\n Today's Date: " + new Date());
            sb.append("\n Total Pizza Sell: 112");
            sb.append("\n Total Sale: $2534");
            sb.append("\n Net Profit: $1985");
            sb.append("\n ***************************************************************");
            report = sb.toString();
        }

        return report;
    }

    @Override
    public String getInfoSucursal(String name) throws RemoteException {
        String info = t.getInfoSucursal(name);
        return info;
    }

    @Override
    public boolean login(String user, String password, String rol) throws RemoteException {
        if (u.checkInfo(user, password, rol, list)) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getTiendas() throws RemoteException {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Tienda> tList = t.regresaLista();

        for (Tienda tList1 : tList) {
            list.add(tList1.getNombre());
        }

        return list;
    }

    @Override
    public boolean addUser(String name, String pass, String rol) throws RemoteException {

        try {
            if (Usuarios.addUser(name, pass, rol, list)) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

        try {
            Registry reg = LocateRegistry.createRegistry(3458);
            ReportGenerator reportGenerator = new ReportGeneratorImpl();
            reg.rebind("PizzaCoRemoteGenerator", reportGenerator);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
