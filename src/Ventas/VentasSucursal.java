/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import RemoteProxy.Tienda;

public class VentasSucursal extends Ventas {

    public Tienda tienda;

    public VentasSucursal(Tienda tienda) {
        this.tienda = tienda;
    }

    public void setNombre(String nombre) {
        tienda.setNombre(nombre);
    }

    public String getNombre() {
        return tienda.getNombre();
    }

    public float getVentas() {
        System.out.print("\t" + getNombre() + " " + tienda.getVentas() + "\n");
        return tienda.getVentas();
    }

}
