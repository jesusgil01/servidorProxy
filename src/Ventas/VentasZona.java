/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class VentasZona extends Ventas {
    
    public List<Ventas> hijos = new ArrayList<Ventas>();
    private String nombre;
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre()
    {
      return nombre;
    }
    
    public float getVentas(){
        System.out.print(getNombre()+ "\n");
        float suma = 0;
        
        for(Ventas hijo: hijos ){           
            suma = hijo.getVentas() + suma;
        }
        
        System.out.print("\tTotales "+suma+"\n");
        return suma;
    }
    
   //@Override
    public void addObject(Ventas hijo){
       hijos.add(hijo);
    }
    
   // @Override
    public void removeObject(Ventas hijo){
        hijos.remove(hijo);
    }

    
}
