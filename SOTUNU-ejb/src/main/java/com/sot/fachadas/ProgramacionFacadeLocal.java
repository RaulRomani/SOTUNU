/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Programacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface ProgramacionFacadeLocal {

  void create(Programacion programacion);

  void edit(Programacion programacion);

  void remove(Programacion programacion);

  Programacion find(Object id);

  List<Programacion> findAll();

  List<Programacion> findRange(int[] range);

  int count();
  
}
