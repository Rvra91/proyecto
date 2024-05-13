/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.Cliente;


/**
 *
 * @author MI COMPUTADOR
 */
public interface InterfaceCliente {
boolean verificarCliente(Cliente objeto);
    public boolean registrarCliente(Cliente objeto);

    public void imprimirCliente();

    public boolean eliminarCliente(Cliente objeto);

}
