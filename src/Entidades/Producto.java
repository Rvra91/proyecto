/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Conexion.Conexion;
import Persistencia.Inventario;
import Entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

/**
 *
 * @author MI COMPUTADOR
 */
public class Producto implements Inventario {

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

    @Override
    public boolean registrarCat(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarProd(Producto objeto) {


    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    
    try {
        // Verificar si existe el id_categoria en la tabla categoria
        PreparedStatement verificarCategoria = cn.prepareStatement("SELECT * FROM categoria WHERE ID = ?");
        verificarCategoria.setInt(1, objeto.getId_categoria());
        ResultSet resultadoCategoria = verificarCategoria.executeQuery();
        
        if (!resultadoCategoria.next()) {
            System.out.println("El ID De la categoria especificado no existe en la tabla categoria.");
            return respuesta; // Retorna false y no registra el producto
        }
        
        // Insertar el producto en la tabla producto
        PreparedStatement consulta = cn.prepareStatement("INSERT INTO producto VALUES(?, ?, ?, ?, ?,?)");
        consulta.setInt(1, objeto.getId());
        consulta.setString(2, objeto.getNombre());
        consulta.setString(3, objeto.getDescripcion());
        consulta.setDouble(4, objeto.getPrecio());
        consulta.setInt(5, objeto.getStock());
        consulta.setInt(6, objeto.getId_categoria());
        
        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al guardar el producto: " + e.getMessage());
        e.printStackTrace();
    }
    return respuesta;
    }

    @Override
    public void imprimir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  public boolean verificarId(int id){
      
      
     
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    
    try {
        // Verificar si existe el id_categoria en la tabla categoria
        PreparedStatement verificarCategoria = cn.prepareStatement("SELECT * FROM categoria WHERE ID = ?");
        verificarCategoria.setInt(1, id);
        ResultSet resultadoCategoria = verificarCategoria.executeQuery();
        
        if (!resultadoCategoria.next()) {
            System.out.println("El ID De la categoria especificado no existe en la tabla categoria.");
            return respuesta; // Retorna false y no registra el producto
        }else{
            
     respuesta=true;
     
            System.out.println("EXISTE");
        }
        
       
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al guardar el producto: " + e.getMessage());
        e.printStackTrace();
    }
    return respuesta;
        
  }
}
