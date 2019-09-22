/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import RemoteProxy.Tienda;

public class VentasFranquicia extends VentasZona {

    public float getVentas() {
        System.out.print("**************** " + getNombre() + " ****************\n");
        float suma = 0;

        for (Ventas hijo : hijos) {

            suma = hijo.getVentas() + suma;
        }

        System.out.print("Totales " + suma + "\n");
        return suma;
    }

    public static void main(String[] args) {

        VentasFranquicia franquicia;

        franquicia = new VentasFranquicia();
        franquicia.setNombre("Franquicia: Pizzeria Nueva Italia");

        VentasZona hermosillo = new VentasZona();
        hermosillo.setNombre("Zona: Hermosillo");

        VentasZona obregon = new VentasZona();
        obregon.setNombre("Zona: Obregon");

        Tienda colosio = new Tienda("Colosio", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        colosio.setVentas(30);
        Tienda navarrete = new Tienda("Navarrete", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        navarrete.setVentas(30);
        Tienda solidaridad = new Tienda("Solidaridad", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        solidaridad.setVentas(30);
        Tienda reforma = new Tienda("reforma", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        reforma.setVentas(80);

        Tienda laguna = new Tienda("Laguna", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        laguna.setVentas(30);
        Tienda estadio = new Tienda("estadio", "Avenida Colosio, 45, Hermosillo, Sonora", 1);
        estadio.setVentas(80);

        VentasSucursal sucursalColosio = new VentasSucursal(colosio);
        VentasSucursal sucursalReforma = new VentasSucursal(reforma);
        VentasSucursal sucursalNavarrete = new VentasSucursal(navarrete);
        VentasSucursal sucursalSoliradidad = new VentasSucursal(solidaridad);

        hermosillo.addObject(sucursalColosio);
        hermosillo.addObject(sucursalReforma);
        hermosillo.addObject(sucursalNavarrete);
        hermosillo.addObject(sucursalSoliradidad);

        VentasSucursal sucursalEstadio = new VentasSucursal(laguna);
        VentasSucursal sucursalLaguna = new VentasSucursal(estadio);

        obregon.addObject(sucursalEstadio);
        obregon.addObject(sucursalLaguna);

        franquicia.addObject(obregon);
        franquicia.addObject(hermosillo);

        hermosillo.getVentas();
        //franquicia.getVentas();

    }
}
