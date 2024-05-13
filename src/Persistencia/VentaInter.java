/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.Venta;

public interface VentaInter {

    void procesarVenta(Venta objeto);

    void ventaLibre(Venta objeto);

    void generarFactura();

    void verVentas(Venta objeto);

    void generarInforme();
}
