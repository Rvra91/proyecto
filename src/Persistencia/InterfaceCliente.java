/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.Cliente;
import Entidades.Producto;

/**
 *
 * @author MI COMPUTADOR
 */
public interface InterfaceCliente  {
    
    public double registrarVenta(Producto pr, double Precio);
    
    
    public boolean registrarCliente(Cliente objeto);
    
    public void imprimirCliente();
    
    public boolean eliminarCliente(Cliente objeto);
    
    
}
