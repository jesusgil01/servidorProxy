/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Validator.NewUserInfo;

/*
    consultar usuarios
    consultar usuarios por sucursal
    consultar tiendas
    consultar ventas todas sucursales
    consultar ventas por sucursal
 */
public class ConsultaMySQL {

    public String login(String usuario, String password, String rol) {
        return "SELECT `usuarios`.`id`  FROM dbds413.usuarios\n"
                + "WHERE `usuarios`.`username` = '"+usuario+"' "
                + "AND `usuarios`.`password` = '"+password+"' "
                + "AND `usuarios`.`rol` = '"+rol+"';";
    }

    public String insertTienda(String nombre, String direccion, String telefono, String encargadoid, String ventas) {
        return "INSERT INTO `Tiendas`(\n"
                + "	nombre, direccion, telefono, encargadoid, ventas)\n"
                + "	VALUES ('" + nombre + "', '" + direccion + "', '" + telefono + "', " + encargadoid + ", " + ventas + ");";
    }

    public String consultaTiendas() {
        return "SELECT *\n"
                + "FROM `dbds413`.`tiendas`;";
    }

    public String ventasTodasSucursales() {
        return "SELECT `tiendas`.`id`,\n"
                + "    `tiendas`.`nombre`,\n"
                + "    `tiendas`.`ventas`\n"
                + "FROM `dbds413`.`tiendas`;";
    }

    public String ventasDeSucursal(String sucursal) {
        return "SELECT `tiendas`.`id`,\n"
                + "    `tiendas`.`nombre`,\n"
                + "    `tiendas`.`ventas`\n"
                + "FROM `dbds413`.`tiendas`\n"
                + "WHERE `tiendas`.`nombre` = '" + sucursal + "';";
    }

    public String insertUsuario(NewUserInfo user) {
        return "INSERT INTO `dbds413`.`usuarios`\n"
                + "(`nombre`, `apellidos`, `fechanaci`, `genero`,\n"
                + "`curp`, `rfc`, `estadocivil`, `telefono`, `email`,\n"
                + "`rol`, `username`, `password`, `id_tienda`, `salario`,\n"
                + "`direccion`)\n"
                + "VALUES\n"
                + "('" + user.getNombre() + "', '" + user.getApellido() + "', '" + user.getFechanaci() + "', '" + user.getGenero() + "',\n"
                + "'" + user.getCurp() + "', '" + user.getRfc() + "', '" + user.getEstadocivil() + "', '" + user.getTelefono() + "', '" + user.getEmail() + "', \n"
                + "'" + user.getRol() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', " + user.getId_tienda() + ", '" + user.getSalario() + "',\n"
                + "'" + user.getDireccion() + "');";
    }

    public String usuariosTotales() {
        return "SELECT *\n"
                + "FROM `dbds413`.`usuarios`;";
    }

    public String usuariosPorNombre(String nombre, String apellido) {
        return "SELECT *\n"
                + "FROM `dbds413`.`usuarios`\n"
                + "WHERE  `usuarios`.`nombre` = '" + nombre + "' "
                + "AND `usuarios`.`apellidos` = '" + apellido + "';";
    }

    public String usuariosSucursal(String nombreSucursal) {
        return "SELECT *\n"
                + "FROM `dbds413`.`usuarios`\n"
                + "INNER JOIN `dbds413`.`tiendas`\n"
                + "ON usuarios.id_tienda = tiendas.id\n"
                + "WHERE tiendas.nombre = '" + nombreSucursal + "';";
    }

}
