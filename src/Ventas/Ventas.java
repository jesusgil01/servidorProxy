/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import RemoteProxy.Tienda;

/**
 *
 * @author pedro
 */
public abstract class Ventas {
    
    public abstract float  getVentas();
    public abstract void   setNombre(String nombre);
    public abstract String getNombre();
    
    public void addObject(Tienda hijo){
       System.out.print("No estas autorizado");
    }
    public void removeObject(Tienda hijo){
        System.out.print("No estas autorizado");
    }
 
}
