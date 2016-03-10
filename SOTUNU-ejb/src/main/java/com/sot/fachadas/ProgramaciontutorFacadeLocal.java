/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Personal;
import com.sot.entidades.Programacion;
import com.sot.entidades.Programaciontutor;
import java.sql.Connection;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author Raul
 */
@Local
public interface ProgramaciontutorFacadeLocal {

  void create(Programaciontutor programaciontutor);

  void edit(Programaciontutor programaciontutor);

  void remove(Programaciontutor programaciontutor);

  Programaciontutor find(Object id);
  
  List<Programaciontutor> findByIdProgramacion(Programacion idProgramacion);
  
  List<Programaciontutor> findByProgramacionTutor(Programacion Programacion, Personal tutor);

  List<Programaciontutor> findAll();

  List<Programaciontutor> findRange(int[] range);

  int count();
  
}
