/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Cicloacademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface CicloacademicoFacadeLocal {

  void create(Cicloacademico cicloacademico);

  void edit(Cicloacademico cicloacademico);

  void remove(Cicloacademico cicloacademico);

  Cicloacademico find(Object id);

  List<Cicloacademico> findAll();

  List<Cicloacademico> findRange(int[] range);

  int count();
  
}
