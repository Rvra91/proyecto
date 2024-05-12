/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Conexion.Conexion;
import Persistencia.InventarioInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ramon
 */
public abstract class ProductoAbs implements InventarioInter {
     
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int id_categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    
    public boolean editarP(Producto objeto) {
          try {
            Connection conexion = Conexion.conectar();
            PreparedStatement consulta = conexion.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?,stock=?,precio=? WHERE id = ?");
            consulta.setString(1, objeto.getNombre());
               consulta.setString(2, objeto.getDescripcion());
                           consulta.setInt(3, objeto.getStock());
            consulta.setDouble(4, objeto.getPrecio());
consulta.setInt(5, objeto.getId());
            consulta.executeUpdate();
            consulta.close();
            conexion.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
      }
      
       public boolean registrarProd(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            // Verificar si existe el id_categoria en la tabla categoria
            PreparedStatement verificarCategoria = cn.prepareStatement("SELECT * FROM categoria WHERE ID = ?");
            verificarCategoria.setInt(1, objeto.getId_categoria());
            ResultSet resultadoCategoria = verificarCategoria.executeQuery();

            if (!resultadoCategoria.next()) {
                System.out.println("El ID de la categoría especificado no existe en la tabla categoria.");
                return respuesta; // Retorna false y no registra el producto
            }

            // Verificar si el ID del producto ya existe en la tabla producto
            // Si el producto ya existe, puede que desees manejarlo de alguna manera
            // Aquí asumiré que no permitimos productos con el mismo ID en la tabla producto
            // Insertar el producto en la tabla producto
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO producto VALUES(?, ?, ?, ?, ?, ?)");

            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getDescripcion());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setInt(5, objeto.getStock());
            consulta.setInt(6, objeto.getId_categoria());

            if (consulta.executeUpdate() > 0) {
                // Actualizar el stock en la tabla categoria
                PreparedStatement actualizarStock = cn.prepareStatement("UPDATE categoria SET stock = COALESCE(stock, 0) + ? WHERE ID = ?");
                actualizarStock.setInt(1, objeto.getStock());
                actualizarStock.setInt(2, objeto.getId_categoria());
                actualizarStock.executeUpdate();
                System.out.println("Producto Guardado Correctamente ");
                respuesta = true;

            }
        } catch (SQLException e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return respuesta;
    }
      
}
