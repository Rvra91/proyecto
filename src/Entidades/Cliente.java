/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Conexion.Conexion;
import Persistencia.InterfaceCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

/**
 *
 * @author MI COMPUTADOR
 */
public class Cliente extends Persona {

    @Override
    public double registrarVenta(Producto pr, double Precio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarCliente(Cliente objeto) {

        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into clientes values(?,?,?)");

            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getCedula());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
            e.printStackTrace();

        }
        return respuesta;
    }

    @Override
    public void imprimirCliente() {
        Connection cn = Conexion.conectar();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;

        try {
            consulta = cn.prepareStatement("SELECT * FROM clientes");
            boolean hayResultados = consulta.execute();

            if (hayResultados) {
                resultSet = consulta.getResultSet(); // Obtener el ResultSet

                // Imprimir encabezados
                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %-20s | %-15s |\n", "ID", "Nombre", "Cedula");
                System.out.println("--------------------------------------------------");

                // Imprimir datos de los clientes
                while (resultSet.next()) {
                    int idCliente = resultSet.getInt("ID");
                    String nombre = resultSet.getString("nombre");
                    String cedula = resultSet.getString("cedula");

                    System.out.printf("| %-10d | %-20s | %-15s |\n", idCliente, nombre, cedula);
                }

                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("No hay clientes en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al imprimir clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close(); // Cerrar el ResultSet
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean eliminarCliente(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("DELETE FROM clientes WHERE id = ?");

            consulta.setInt(1, objeto.getID());
     imprimirCliente();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("CLIENTE ELIMINADO");
                     imprimirCliente();
            }else{
                
                System.out.println("NO SE ENCONTRO CLIENTE CON ESE ID");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
            e.printStackTrace();

        }
        return respuesta;

    }

}
