package Presentacion;

import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Producto;
import java.util.Scanner;

public class Menu {

    public void menu(int eleccion, int eleccion2) {
        Cliente c = new Cliente();
        Categoria cat = new Categoria();
        Producto p = new Producto();
        Scanner scanner = new Scanner(System.in);
        while (eleccion != 5) {
            eleccion = 0;
            System.out.println("1: Administrar Clientes\n"
                    + "2: Administrar Categorias\n"
                    + "3: Administrar Inventario\n"
                    + "4: Administrar Ventas \n"
                    + "5: Salir");

            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1:
                    eleccion2 = 0;
                    while (eleccion2 != 4) {
                        eleccion2 = 0;
                        System.out.println("1: Agregar Cliente\n"
                                + "2: Ver Clientes\n"
                                + "3: Eliminar Cliente"
                                + "\n 4: Salir");
                        eleccion2 = scanner.nextInt();
                        switch (eleccion2) {

                            case 1:
                                System.out.println("Añada nombre del cliente");

                                String nombre = scanner.nextLine();
                                nombre = scanner.nextLine();
                                c.setNombre(nombre);

                                System.out.println("Añada la cedula del cliente");

                                String cedula = scanner.nextLine();

                                c.setCedula(cedula);
                                c.registrarCliente(c);

                                break;

                            case 2:

                                c.imprimirCliente();
                                break;

                            case 3:
                                c.imprimirCliente();
                                System.out.println("INGRESE EL ID DEL CLIENTE A ELIMINAR");
                                int id = scanner.nextInt();

                                c.setID(id);
                                c.eliminarCliente(c);

                                break;

                            case 4:
                                System.out.println("");

                                break;
                            default:
                                System.out.println("Opcion invalida");
                                break;
                        }
                    }
                    break;

                case 2:
                    eleccion2 = 0;
                    while (eleccion2 != 4) {
                        eleccion2 = 0;
                        System.out.println("1: Agregar Categoria\n"
                                + "2: Gestionar Categoria\n"
                                + "3: Eliminar Categoria");

                        eleccion2 = scanner.nextInt();
                        switch (eleccion2) {

                            case 1:
                                System.out.println("Añada nombre de la categoria");

                                String nombre = scanner.nextLine();
                                nombre = scanner.nextLine();
                                cat.setNombre(nombre);
                                System.out.println("Descripcion de la categoria");

                                String descripcion = scanner.nextLine();

                                cat.setDescripcion(descripcion);
                                cat.registrarCat(cat);
                                break;

                            case 2:

                                cat.imprimir();

                                break;

                            case 3:
                                cat.imprimir();
                                System.out.println("Id de la categoria a eliminar");
                                int id = scanner.nextInt();
                                cat.setID(id);

                                cat.eliminar(cat);
                                break;

                            case 4:
                                System.out.println("");

                                break;
                            default:
                                System.out.println("Opcion invalida");
                                break;
                        }

                    }
                    break;
                case 3:
                    eleccion2 = 0;
                    while (eleccion2 != 4) {
                        System.out.println("1: Agregar Producto\n"
                                + "2: Gestionar Productos\n"
                                + "3: Eliminar Producto"
                                + "\n 4: Salir");

                        eleccion2 = scanner.nextInt();

                        switch (eleccion2) {

                            case 1:
                                limpiarConsola();
                                System.out.println("Nombre del producto a agregar");
                                String nombre = scanner.nextLine();
                                nombre = scanner.nextLine();

                                p.setNombre(nombre);
                                System.out.println("Descripcion del producto");

                                String descripcion = scanner.nextLine();
                                p.setDescripcion(descripcion);
                                System.out.println("Precio del producto " + p.getNombre());
                                double precio = scanner.nextDouble();

                                p.setPrecio(precio);

                                System.out.println("Cantidad existente");
                                int stock = scanner.nextInt();

                                p.setStock(stock);
                                int id_cat = 0;
                                cat.imprimir();
                                System.out.println("En que categoria quiere guardar el producto " + p.getNombre() + " ?");
                                System.out.println("Inserte ID Categoria");

                                id_cat = scanner.nextInt();
                                p.setId_categoria(id_cat);
                                p.verificarId(id_cat);

                                limpiarConsola();

                                if (!p.verificarId(id_cat)) {

                                    while (!p.verificarId(id_cat)) {

                                        cat.imprimir();
                                        System.out.println("En que categoria quiere guardar el producto " + p.getNombre() + " ?");
                                        System.out.println("Inserte ID Categoria");

                                        id_cat = scanner.nextInt();
                                        p.setId_categoria(id_cat);
                                        p.verificarId(id_cat);

                                    }
                                } else {

                                    p.registrarProd(p);
                                }

                                break;

                        }

                    }
                    break;
            }

        }

    }

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
