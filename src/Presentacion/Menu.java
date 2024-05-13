package Presentacion;

import Entidades.Categoria;
import Entidades.Cliente;
import Entidades.Inventario;
import Entidades.Producto;
import Entidades.Venta;
import java.util.Scanner;

public class Menu {

    public void menu(int eleccion, int eleccion2) {
        Cliente c = new Cliente();
        Categoria cat = new Categoria();
        Producto p = new Producto();
        Inventario i = new Inventario();
        Venta v = new Venta();
        int id;
        int eleccion3 = 0;

        Scanner scanner = new Scanner(System.in);
        while (eleccion != 7) {
            eleccion = 0;
            System.out.println("1: Administrar Clientes\n"
                    + "2: Administrar Categorias\n"
                    + "3: Administrar Productos\n"
                    + "4: Inventario\n"
                    + "5: Hacer Venta\n"
                    + "6: Administrar Ventas\n"
                    + "7: Salir");

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
                                id = scanner.nextInt();

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
                                + "3: Eliminar Categoria"
                                + "\n 4: Salir");

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
                                int e = 0;
                                cat.imprimir();
                                System.out.println("Indique el ID De la categoria a editar");
                                id = scanner.nextInt();
                                cat.setID(id);
                                System.out.println("Que desea hacer?\n"
                                        + "1: Cambiar Nombre y Descripcion\n"
                                        + "2: Eliminar\n"
                                        + "3: Regresar");
                                e = scanner.nextInt();
                                if (e == 1) {
                                    System.out.println("Ingrese nombre");
                                    nombre = scanner.nextLine();
                                    nombre = scanner.nextLine();
                                    cat.setNombre(nombre);
                                    System.out.println("Ingrese La Descripcion");

                                    descripcion = scanner.nextLine();
                                    cat.setDescripcion(descripcion);
                                    cat.editarC(cat);
                                } else if (e == 2) {

                                    cat.eliminar(id);

                                } else if (e == 3) {

                                } else if (e <= 1 || e >= 3) {

                                    System.out.println("OPCION INVALIDA");
                                    break;
                                }
                                break;

                            case 3:
                                cat.imprimir();
                                System.out.println("Id de la categoria a eliminar");
                                id = scanner.nextInt();
                                cat.setID(id);

                                cat.eliminar(id);
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

                                limpiarConsola();

                                p.registrarProd(p);

                                break;

                            case 2:
                                eleccion3 = 0;

                                while (eleccion3 != 3 && eleccion3 != 2) {

                                    System.out.println("1: Editar producto \n"
                                            + "2: Regresar\n"
                                            + "3: Salir menu principal");

                                    eleccion3 = scanner.nextInt();
                                    switch (eleccion3) {

                                        case 1:
                                            p.imprimir();

                                            System.out.println("ID DEL PRODUCTO A EDITAR");

                                            id = scanner.nextInt();

                                            p.setId(id);
                                            System.out.println("Que desea hacer con ese producto?");
                                            System.out.println("1:Mover de categoria");
                                            System.out.println("2:Editarlo");
                                            System.out.println("3:Eliminar");

                                            eleccion3 = scanner.nextInt();

                                            switch (eleccion3) {

                                                case 1:

                                                    break;
                                                case 2:

                                                    System.out.println("Ingrese nombre");

                                                    nombre = scanner.nextLine();
                                                    nombre = scanner.nextLine();
                                                    p.setNombre(nombre);
                                                    System.out.println("Ingrese descripcion");

                                                    descripcion = scanner.nextLine();
                                                    p.setDescripcion(descripcion);

                                                    System.out.println("Ingrese stock");
                                                    stock = scanner.nextInt();
                                                    p.setStock(stock);
                                                    System.out.println("Ingrese el precio");

                                                    precio = scanner.nextDouble();
                                                    p.setPrecio(precio);

                                                    p.editarP(p);
                                                    break;
                                                case 3:
                                                    p.eliminar(id);
                                                    break;

                                                default:
                                                    System.out.println("Opcion invalida");
                                                    break;
                                            }

                                            break;

                                        case 2:

                                            System.out.println(" ");

                                            break;
                                        case 3:
                                            eleccion2 = 4;
                                            System.out.println(" ");
                                            break;

                                        default:
                                            System.out.println("Opcion invalida");
                                            break;
                                    }
                                }

                                break;

                            case 3:

                                p.imprimir();

                                System.out.println("Ingrese el ID Del producto a eliminar");
                                id = scanner.nextInt();

                                p.setId(id);
                                p.eliminar(id);

                                break;

                        }

                    }
                    break;

                case 4:
                    i.imprimir();
                    System.out.println("Que desea hacer? ");
                    
                    System.out.println("1: Informe de un producto");
                    System.out.println("2: Informe de todos los productos");
                    
                    
                    break;

                case 5:
                    System.out.println("Que desea vender?");

                    p.imprimir();

                    System.out.println("Ingrese el ID De lo que desea vender");

                    id = scanner.nextInt();

                    v.setId_pr(id);
                    System.out.println("Cuantos desea vender?");
                    int stock = scanner.nextInt();
                    v.setStock(stock);
                    eleccion2 = 0;

                    while (eleccion2 != 1 && eleccion2 != 2) {
                        System.out.println("1:Hacer venta a un cliente\n"
                                + "2:Hacer venta libre");
                        eleccion2 = scanner.nextInt();
                        String tipo = "";
                        switch (eleccion2) {

                            case 1:
                                System.out.println("Ingresar ID Del cliente");
                                c.imprimirCliente();
                                id = scanner.nextInt();

                                c.verificarCliente(c);
                                c.setID(id);

                                if (!c.verificarCliente(c)) {

                                    System.out.println("NO EXISTE CLIENTE CON ESE ID");
                                    break;
                                }
                                v.setCliente(id);

                                System.out.println("Ingrese el tipo de pago");
                                System.out.println("1: Nequi");
                                System.out.println("2: Transferencia");
                                System.out.println("3: Efectivo");

                                int e = scanner.nextInt();

                                if (e == 1) {

                                    tipo = "Nequi";
                                } else if (e == 2) {

                                    tipo = "Transferencia";
                                } else if (e == 3) {

                                    tipo = "Efectivo";

                                } else if (e <= 1 || e >= 3) {

                                    System.out.println("Opcion no valida");
                                    break;
                                }

                                System.out.println(tipo);
                                v.setTipo(tipo);

                                System.out.println("Ingresar Dinero");

                                int dinero = scanner.nextInt();

                                v.setDinero(dinero);
                                v.procesarVenta(v);

                                System.out.println(id);
                                v.generarFactura(id);
                                break;

                            case 2:
                                System.out.println("Ingrese el tipo de pago");
                                System.out.println("1: Nequi");
                                System.out.println("2: Transferencia");
                                System.out.println("3: Efectivo");

                                e = scanner.nextInt();

                                if (e == 1) {

                                    tipo = "Nequi";
                                } else if (e == 2) {

                                    tipo = "Transferencia";
                                } else if (e == 3) {

                                    tipo = "Efectivo";

                                } else if (e <= 1 || e >= 3) {

                                    System.out.println("Opcion no valida");
                                    break;
                                }

                                System.out.println(tipo);
                                v.setTipo(tipo);

                                System.out.println("Ingresar Dinero");

                                dinero = scanner.nextInt();

                                v.setDinero(dinero);
                                v.ventaLibre(v);
                                v.generarFactura(id);
                                break;
                        }

                    }

                    break;
                case 6:
                    v.verVentas(v);

                    System.out.println("Que desea hacer");
                  /*  System.out.println("1: Informe de una venta");
                    System.out.println("2: Generar Informe de todas");

                    eleccion2 = 0;
                    eleccion2=scanner.nextInt();
                    switch (eleccion2) {

                        case 1:

                            break;
                        case 2:
                            v.generarInforme();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            break;
                    }*/
                    break;
            }

        }

    }

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
