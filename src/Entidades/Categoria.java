/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Conexion.Conexion;
import Persistencia.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author MI COMPUTADOR
 */
public class Categoria implements Inventario {
private int ID;
    private String nombre;
    private String descripcion;

    public Categoria() {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public boolean registrarCat(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into categoria values(?,?,?,?)");

            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar la categoria: " + e.getMessage());
            e.printStackTrace();

        }
        return respuesta;
    }


    @Override
    public boolean registrarProd(Producto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void imprimir() {
        Connection cn = Conexion.conectar();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;

        try {
            consulta = cn.prepareStatement("SELECT * FROM categoria");
            boolean hayResultados = consulta.execute();
   
            if (hayResultados) {
                resultSet = consulta.getResultSet(); // Obtener el ResultSet

                // Imprimir encabezados
                System.out.println("--------------------------------------------------");
System.out.printf("| %-10s | %-20s | %-20s | %-10s |\n", "ID", "Nombre", "Descripción", "Stock");
                System.out.println("--------------------------------------------------");

                // Imprimir datos de los clientes
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                      int Stock = resultSet.getInt("Stock");
    System.out.printf("| %-10d | %-20s | %-20s | %-10d |\n",ID, nombre, descripcion,Stock);

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
    public boolean eliminar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("DELETE FROM categoria WHERE id = ?");

            consulta.setInt(1, objeto.getID());
            imprimir();
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("CATEGORIA ELIMINADA");
                imprimir();
            } else {

                System.out.println("NO SE ENCONTRO CATEGORIA CON ESE ID");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la categoria: " + e.getMessage());
            e.printStackTrace();

        }
        return respuesta;

    }

}
