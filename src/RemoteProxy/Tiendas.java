/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteProxy;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Tiendas {

    ArrayList<Tienda> lista = new ArrayList<>();

    public Tiendas() {
        lista.add(new Tienda("Luigis Pizza", "Roberto Bolanos 8", 1));
        lista.add(new Tienda("Pizzas Lupita", "Toronja 2", 2));
        lista.add(new Tienda("Chocolata Pizzeria", "De la Uva 37", 3));
    }

    public ArrayList regresaLista() {
        return lista;
    }
    
    public String getInfoSucursal(String name){
        StringBuilder sb = new StringBuilder();         
        for (Tienda s : lista) {
            if (s.getNombre().compareTo(name) == 0) {
                sb.append("Nombre: " + s.getNombre() + 
                        "\nDirección: " + s.getDireccion() + 
                        "\nCódigo: " + s.getCodigo());
            }
        }
        
        String info = sb.toString();
        return info;
    }

}
