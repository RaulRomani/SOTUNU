/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Escuelaprofesional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface EscuelaprofesionalFacadeLocal {

  void create(Escuelaprofesional escuelaprofesional);

  void edit(Escuelaprofesional escuelaprofesional);

  void remove(Escuelaprofesional escuelaprofesional);

  Escuelaprofesional find(Object id);

  List<Escuelaprofesional> findAll();

  List<Escuelaprofesional> findRange(int[] range);

  int count();
  
}
