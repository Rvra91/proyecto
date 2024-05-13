package Entidades;

import Conexion.Conexion;
import Entidades.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

/**
 *
 * @author MI COMPUTADOR
 */
public class Producto extends ProductoAbs {

    
    @Override
    public boolean registrarCat(Categoria objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 

    @Override
    public boolean eliminar(int id) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("DELETE FROM producto WHERE id = ?");
            consulta.setInt(1, id);

            int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("PRODUCTO ELIMINADO");
                respuesta = true;
            } else {
                System.out.println("ESE ID NO EXISTE");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
        }
        return respuesta;

    }

    @Override
    public void imprimir() {
        Connection cn = Conexion.conectar();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;

        try {
            consulta = cn.prepareStatement("SELECT * FROM producto");
            boolean hayResultados = consulta.execute();

            if (hayResultados) {
                resultSet = consulta.getResultSet(); // Obtener el ResultSet

                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Nombre", "Descripcion", "Stock", "Precio");
                System.out.println("--------------------------------------------------");

                // Imprimir datos de los clientes
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    String stock = resultSet.getString("stock");
                    double precio = resultSet.getDouble("precio");

                    System.out.printf("| %-10d | %-15s | %-10s | %-10s | %-10s |\n", ID, nombre, descripcion, stock, precio);
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
    public boolean editarC(Categoria cat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
  

}
