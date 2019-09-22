package RemoteProxy;

import static RemoteProxy.ReportGeneratorImpl.u;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuarios {

    static ArrayList<Usuario> lista = new ArrayList<>();
    private static Usuarios instance;
    private static Usuario usuario;
    private static final File archivo = new File("C:\\Users\\jesus\\Documents\\DSIV\\archivo.txt");

    public static Usuarios getInstance() {
        if (instance == null) {
            instance = new Usuarios();
        }
        return instance;
    }

    public ArrayList returnList() {

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split(",");
                usuario = new Usuario("", "", "");
                usuario.setUser(separado[0]);
                usuario.setPassword(separado[1]);
                usuario.setRol(separado[2]);
                lista.add(usuario);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    private static void EscribeArchivo(ArrayList<Usuario> lista) throws IOException {

        try {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter wr = new PrintWriter(bw);
            for (int i = 0; i < lista.size(); i++) {
                wr.write(lista.get(i).user + "," + lista.get(i).password + "," + lista.get(i).rol + "\n");
            }
            wr.close();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static boolean userExist(String user, ArrayList<Usuario> lista) {
        for (Usuario u : lista) {
            if (u.getUser().compareTo(user) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInfo(String user, String pass, String rol, ArrayList<Usuario> lista) {
        Usuario usuario = getUser(user, lista);
        if (usuario.getPassword().compareTo(pass) == 0) {
            if (usuario.getRol().compareTo(rol) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean addUser(String user, String pass, String rol, ArrayList<Usuario> list) throws IOException {

        if (userExist(user, list) == false) {
            lista.add(new Usuario(user, pass, rol));
            EscribeArchivo(lista);
            return true;
        }
        return false;
    }

    public static Usuario getUser(String name, ArrayList<Usuario> lista) {
        Usuario user = new Usuario("", "", "");
        if (userExist(name, lista)) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getUser().compareTo(name) == 0) {
                    user.setUser(lista.get(i).getUser());
                    user.setPassword(lista.get(i).getPassword());
                    user.setRol(lista.get(i).getRol());
                    break;
                }
            }
        }

        return user;
    }

    public boolean login(String user, String password, String rol, ArrayList<Usuario> lista) {
        if (checkInfo(user, password, rol, lista)) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        ArrayList <Usuario> l;
        try {
            l = getInstance().returnList();
            if (Usuarios.addUser("Villa", "123", "rol", l)) {
                l = u.returnList();
                System.out.println("Si");
            }
        } catch (IOException ex) {
            Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
