/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.ResultSet;
import Conexion.Conexion;
import Persistencia.VentaInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ramon
 */
public class Venta implements VentaInter {

    private int id;
    private int id_cat;
    private int id_pr;
    private int cliente;
    private double dinero;
    private String tipo;
    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public int getId_pr() {
        return id_pr;
    }

    public void setId_pr(int id_pr) {
        this.id_pr = id_pr;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void procesarVenta(Venta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Recuperar el precio del producto
            PreparedStatement consultaPrecio = cn.prepareStatement("SELECT precio, stock FROM producto WHERE id = ?");
            consultaPrecio.setInt(1, objeto.getId_pr());
            ResultSet resultadoPrecio = consultaPrecio.executeQuery();

            if (resultadoPrecio.next()) {
                double precioProducto = resultadoPrecio.getDouble("precio");
                int stockProducto = resultadoPrecio.getInt("stock");
                // Calcular el dinero a partir del precio del producto y el dinero en objeto.getDinero
                double dinero = objeto.getDinero() - precioProducto * objeto.getStock();

                // Verificar si el dinero es suficiente para la compra
                if (dinero >= 0) {
                    // Recuperar el id_categoria asociado al id_producto
                    PreparedStatement consultaCategoria = cn.prepareStatement("SELECT id_categoria FROM producto WHERE id = ?");
                    consultaCategoria.setInt(1, objeto.getId_pr());
                    ResultSet resultadoCategoria = consultaCategoria.executeQuery();

                    if (resultadoCategoria.next()) {
                        int id_categoria = resultadoCategoria.getInt("id_categoria");

                        // Insertar la venta en la tabla ventas
                        PreparedStatement consultaVentas = cn.prepareStatement("INSERT INTO ventas (id_producto, id_categoria, id_cliente,tipo,cant_vendida, dinero) VALUES (?, ?,?, ?, ?,?)");
                        PreparedStatement consulta3 = cn.prepareStatement("UPDATE producto SET stock = ? WHERE id = ?");
                        int nuevoStock = stockProducto - objeto.getStock();
                        consulta3.setInt(1, nuevoStock);
                        consulta3.setInt(2, objeto.getId_pr());
                        consulta3.executeUpdate();

                        consultaVentas.setInt(1, objeto.getId_pr());
                        consultaVentas.setInt(2, id_categoria);
                        consultaVentas.setInt(3, objeto.getCliente());
                        consultaVentas.setString(4, objeto.getTipo());
                        consultaVentas.setInt(5, objeto.getStock());

                        consultaVentas.setDouble(6, dinero);

                        if (consultaVentas.executeUpdate() > 0) {
                            System.out.println("Venta Realizada \n");

                            System.out.println("Cambio a dar: " + dinero);
                            respuesta = true;
                        }
                    } else {
                        System.out.println("No se encontró el id_categoria asociado al id_producto: " + objeto.getId_pr());
                    }
                } else {
                    System.out.println("La venta no puede ser realizada. El dinero proporcionado no es suficiente.");
                    System.out.println("Faltan: " + dinero * -1);
                }
            } else {
                System.out.println("No se encontró el precio del producto con el id: " + objeto.getId_pr());
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al procesar la venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void generarFactura(int id) {
        try (Connection cn = Conexion.conectar(); PreparedStatement consulta = cn.prepareStatement(
                "SELECT v.id, v.id_producto, p.nombre AS nombre_producto, p.precio AS precio_producto, c.nombre AS nombre_cliente, v.dinero, v.tipo "
                + "FROM ventas v "
                + "INNER JOIN producto p ON v.id_producto = p.id "
                + "INNER JOIN clientes c ON v.id_cliente = c.id "
                + "WHERE v.id = (SELECT MAX(id) FROM ventas)")) {

            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    int idVentaObtenida = resultado.getInt("id");
                    int idProducto = resultado.getInt("id_producto");
                    String nombreProducto = resultado.getString("nombre_producto");
                    double precioProducto = resultado.getDouble("precio_producto");
                    String nombreCliente = resultado.getString("nombre_cliente");
                    double dineroRecibido = resultado.getDouble("dinero");
                    String tipoPago = resultado.getString("tipo");

                    System.out.println("*******************************************");
                    System.out.println("************** FACTURA *********************");
                    System.out.println("*******************************************");
                    System.out.println("ID Venta: " + idVentaObtenida);
                    System.out.println("ID Producto: " + idProducto);
                    System.out.println("Nombre Producto: " + nombreProducto);
                    System.out.println("Precio Producto: " + precioProducto);
                    System.out.println("Nombre Cliente: " + nombreCliente);
                    System.out.println("Dinero Recibido: " + dineroRecibido);
                    System.out.println("Tipo de Pago: " + tipoPago);
                    System.out.println("*******************************************");
                } else {
                    System.out.println("No se encontró la venta con el ID especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar la factura: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void verVentas(Venta objeto) {

        Connection cn = null;
        try {
            cn = Conexion.conectar();

            // Consulta SQL para obtener información de las ventas
            String sql = "SELECT v.id, v.id_producto, p.nombre AS nombre_producto, p.precio AS precio_producto, c.nombre || ' (' || v.id_cliente || ')' AS nombre_cliente, v.dinero "
                    + "FROM ventas v "
                    + "INNER JOIN producto p ON v.id_producto = p.id "
                    + "INNER JOIN clientes c ON v.id_cliente = c.id";

            PreparedStatement consulta = cn.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            // Imprimir encabezado del informe
            System.out.println("ID Venta\t ID Producto\t Nombre Producto\tPrecio Producto\tNombre Cliente\tDinero");
            System.out.println("------------------------------------------------------------------------");

            double totalDinero = 0;

            // Iterar sobre los resultados y mostrarlos en el informe
            while (resultado.next()) {
                int idVenta = resultado.getInt("id");
                int idProducto = resultado.getInt("id_producto");
                String nombreProducto = resultado.getString("nombre_producto");
                double precioProducto = resultado.getDouble("precio_producto");
                String nombreCliente = resultado.getString("nombre_cliente");
                double dinero = resultado.getDouble("dinero");

                System.out.printf("%d\t\t%d\t\t%s\t\t%.2f\t\t%s\t\t%.2f\n", idVenta, idProducto, nombreProducto, precioProducto, nombreCliente, dinero);

                totalDinero += dinero; // Acumular el dinero de cada venta
            }

            // Imprimir el total de dinero ganado
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Total dinero ganado: " + totalDinero);
            System.out.println("------------------------------------------------------------------------");

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al generar el informe de ventas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void generarInforme() {

    }

    @Override
    public void ventaLibre(Venta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Recuperar el precio del producto
            PreparedStatement consultaPrecio = cn.prepareStatement("SELECT precio FROM producto WHERE id = ?");
            consultaPrecio.setInt(1, objeto.getId_pr());
            ResultSet resultadoPrecio = consultaPrecio.executeQuery();

            if (resultadoPrecio.next()) {
                double precioProducto = resultadoPrecio.getDouble("precio");

                // Calcular el dinero a partir del precio del producto y el dinero en objeto.getDinero
                double dinero = objeto.getDinero() - precioProducto;

                // Verificar si el dinero es suficiente para la compra
                if (dinero >= 0) {
                    // Recuperar el id_categoria asociado al id_producto
                    PreparedStatement consultaCategoria = cn.prepareStatement("SELECT id_categoria FROM producto WHERE id = ?");
                    consultaCategoria.setInt(1, objeto.getId_pr());
                    ResultSet resultadoCategoria = consultaCategoria.executeQuery();

                    if (resultadoCategoria.next()) {
                        int id_categoria = resultadoCategoria.getInt("id_categoria");

                        // Insertar la venta en la tabla ventas
                        PreparedStatement consultaVentas = cn.prepareStatement("INSERT INTO ventas (id_producto, id_categoria, id_cliente, dinero) VALUES (?, ?, ?, ?)");
                        consultaVentas.setInt(1, objeto.getId_pr());
                        consultaVentas.setInt(2, id_categoria);
                        consultaVentas.setInt(3, 0);
                        consultaVentas.setDouble(4, precioProducto);

                        if (consultaVentas.executeUpdate() > 0) {
                            System.out.println("Venta Realizada \n");

                            System.out.println("Cambio a dar: " + dinero);
                            respuesta = true;
                        }
                    } else {
                        System.out.println("No se encontró el id_categoria asociado al id_producto: " + objeto.getId_pr());
                    }
                } else {
                    System.out.println("La venta no puede ser realizada. El dinero proporcionado no es suficiente.");
                }
            } else {
                System.out.println("No se encontró el precio del producto con el id: " + objeto.getId_pr());
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al procesar la venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
