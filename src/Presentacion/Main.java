package Presentacion;

import java.util.Scanner;
import Entidades.Cliente;

public class Main {
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        String nombre;
        int Id;
        int cedula;
        int eleccion = 0;
        int eleccion2 = 0;
        
        Cliente c = new Cliente();
        menu.menu(eleccion, eleccion2);
        
    }
}
