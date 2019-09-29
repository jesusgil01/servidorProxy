
package Consultas;

import Validator.NewUserInfo;

public class ConsultasPostgreSQL {

    public String login(String usuario, String password, String rol) {
        return "SELECT id\n"
                + "	FROM public.\"Usuarios\"\n"
                + "    WHERE username = '" + usuario + "'\n"
                + "    AND password = '" + password + "' \n"
                + "    AND rol = '" + rol + "';";
    }

    public String insertTienda(String nombre, String direccion, String telefono, String encargadoid, String ventas) {
        return "INSERT INTO public.\"Tiendas\"(\n"
                + "nombre, direccion, "
                + "telefono, encargadoid, ventas)\n"
                + "	VALUES ('" + nombre + "', '" + direccion + "', "
                + "'" + telefono + "', '" + encargadoid + "', '" + ventas + "');";
    }

    public String consultaTiendas() {
        return "SELECT *\n"
                + "	FROM public.\"Tiendas\";";
    }

    public String ventasTodasSucursales() {
        return "SELECT id, nombre, ventas\n"
                + "	FROM public.\"Tiendas\";";
    }

    public String ventasDeSucursal(String sucursal) {
        return "SELECT id, nombre, ventas\n"
                + "	FROM public.\"Tiendas\"\n"
                + "	WHERE nombre = '" + sucursal + "';";
    }

    public String insertUsuario(NewUserInfo user) {
        return "INSERT INTO public.\"Usuarios\"(\n"
                + "id, nombre, apellidos, fechanaci, genero, curp, rfc, estadocivil, "
                + "telefono, email, rol, username, password, id_tienda, salario, direccion)"
                + "VALUES\n"
                + "('" + user.getNombre() + "', '" + user.getApellido() + "', '" + user.getFechanaci() + "', '" + user.getGenero() + "',\n"
                + "'" + user.getCurp() + "', '" + user.getRfc() + "', '" + user.getEstadocivil() + "', '" + user.getTelefono() + "', '" + user.getEmail() + "', \n"
                + "'" + user.getRol() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', " + user.getId_tienda() + ", '" + user.getSalario() + "',\n"
                + "'" + user.getDireccion() + "');";
    }

    public String usuariosTotales() {
        return "SELECT * FROM public.\"Usuarios\"";
    }

    public String usuariosSucursal(String nombreSucursal) {
        return "SELECT \"Usuarios\".id, \"Usuarios\".nombre, apellidos, fechanaci, genero, curp, rfc, \n"
                + "	estadocivil, \"Usuarios\".telefono, email, rol, username, password, \n"
                + "	id_tienda, salario, \"Usuarios\".direccion\n"
                + "	FROM \"Usuarios\"\n"
                + "	INNER JOIN \"Tiendas\"\n"
                + "	ON id_tienda = \"Tiendas\".id\n"
                + "	WHERE \"Tiendas\".nombre = '"+nombreSucursal+"'";
    }

}
