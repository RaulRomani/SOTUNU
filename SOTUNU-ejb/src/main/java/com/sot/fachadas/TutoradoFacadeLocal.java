/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Tutorado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface TutoradoFacadeLocal {

  void create(Tutorado tutorado);

  void edit(Tutorado tutorado);

  void remove(Tutorado tutorado);

  Tutorado find(Object id);

  List<Tutorado> findAll();

  List<Tutorado> findRange(int[] range);

  int count();
  
}
