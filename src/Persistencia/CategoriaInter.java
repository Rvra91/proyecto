/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.Categoria;

/**
 *
 * @author Ramon
 */
public interface CategoriaInter {
      public boolean registrarCat(Categoria objeto);



    public void imprimir();

    public boolean eliminar(int id);

  

    public boolean editarC(Categoria cat);

}
