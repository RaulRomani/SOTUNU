/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Programacion;
import com.sot.entidades.Usuario;
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
  
  List<Programacion> findByCiclo(String ciclo);
  
  List<Programacion> findProgramacionDirector(Usuario idUsuario,Cicloacademico idCicloAcademico);

  List<Programacion> findAll();

  List<Programacion> findRange(int[] range);

  int count();
  
}
