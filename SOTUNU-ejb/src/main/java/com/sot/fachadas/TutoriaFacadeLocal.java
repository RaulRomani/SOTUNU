/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Programaciontutor;
import com.sot.entidades.Tutoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface TutoriaFacadeLocal {

  void create(Tutoria tutoria);

  void edit(Tutoria tutoria);

  void remove(Tutoria tutoria);

  Tutoria find(Object id);
  
  List<Tutoria> findByProgramacionTutor(Programaciontutor programacionTutor);

  List<Tutoria> findAll();

  List<Tutoria> findRange(int[] range);

  int count();
  
}
