/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Persistencia.InventarioInter;

/**
 *
 * @author Ramon
 */
public class Inventario implements InventarioInter{

    @Override
    public void imprimir() {
         try (Connection cn = Conexion.conectar();
         Statement consulta = cn.createStatement()) {

        // Consulta SQL para obtener el informe del inventario
        String sql = "SELECT p.nombre AS nombre_producto, c.nombre AS nombre_categoria, " +
                     "SUM(v.cant_vendida) AS total_vendido, " +
                     "SUM(v.dinero) AS dinero_ganado, " +
                     "p.stock AS stock_actual " +
                     "FROM ventas v " +
                     "INNER JOIN producto p ON v.id_producto = p.id " +
                     "INNER JOIN categoria c ON p.id_categoria = c.id " +
                     "GROUP BY p.id";
        
        try (ResultSet resultado = consulta.executeQuery(sql)) {
            // Imprimir encabezado del informe
            System.out.println("******************************************************");
            System.out.println("************* INFORME DEL INVENTARIO ****************");
            System.out.println("******************************************************");
            System.out.printf("| %-20s | %-15s | %-15s | %-15s | %-15s |\n", 
                              "Nombre Producto", "Categoria", "Total Vendido", "Dinero Ganado", "Stock Actual");
            System.out.println("------------------------------------------------------");

            // Iterar sobre los resultados y mostrarlos en el informe
            while (resultado.next()) {
                String nombreProducto = resultado.getString("nombre_producto");
                String nombreCategoria = resultado.getString("nombre_categoria");
                int totalVendido = resultado.getInt("total_vendido");
                double dineroGanado = resultado.getDouble("dinero_ganado");
                int stockActual = resultado.getInt("stock_actual");

                // Imprimir detalles del producto en el inventario
                System.out.printf("| %-20s | %-15s | %-15d | %-15.2f | %-15d |\n", 
                                  nombreProducto, nombreCategoria, totalVendido, dineroGanado, stockActual);
            }
            System.out.println("******************************************************");
        }
    } catch (SQLException e) {
        System.out.println("Error al generar el informe del inventario: " + e.getMessage());
        e.printStackTrace();
    }}
}
