/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Facultad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface FacultadFacadeLocal {

  void create(Facultad facultad);

  void edit(Facultad facultad);

  void remove(Facultad facultad);

  Facultad find(Object id);

  List<Facultad> findAll();

  List<Facultad> findRange(int[] range);

  int count();
  
}
