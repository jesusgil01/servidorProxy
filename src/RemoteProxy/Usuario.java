/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteProxy;

/**
 *
 * @author jesus
 */
public class Usuario {

    String user;
    String password;
    String rol;

    public Usuario(String usuario, String password, String rol) {
        this.user = usuario;
        this.password = password;
        this.rol = rol;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
