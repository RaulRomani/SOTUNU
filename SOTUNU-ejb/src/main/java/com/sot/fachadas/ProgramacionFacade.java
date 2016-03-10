/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Escuelaprofesional;
import com.sot.entidades.Programacion;
import com.sot.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raul
 */
@Stateless
public class ProgramacionFacade extends AbstractFacade<Programacion> implements ProgramacionFacadeLocal {

  @PersistenceContext(unitName = "com.sot_SOTUNU-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ProgramacionFacade() {
    super(Programacion.class);
  }

  @Override
  public Programacion findProgramacionDirector(Usuario idUsuario, Cicloacademico idCicloAcademico) {

//    TypedQuery<Programacion> q = getEntityManager().createNamedQuery("Programacion.findProgramacionDirector", Programacion.class);
//    q.setParameter("idUsuario", idUsuario);
//    q.setParameter("idCicloAcademico", idCicloAcademico);
//    List<Programacion> list;
//    try {
//      list = q.getResultList();
//      
//    } catch (NoResultException e) {
//      list = null;
//    }
//    return list;
    Programacion programacion;

    try {
      Query q = getEntityManager().createNamedQuery("Programacion.findProgramacionDirector");
      q.setParameter("idUsuario", idUsuario);
      q.setParameter("idCicloAcademico", idCicloAcademico);
      programacion = (Programacion) q.getSingleResult();
    } catch (NoResultException e) {
      programacion = null;
    }
    return programacion;

  }

  @Override
  public Programacion findByEscuelaCiclo(Escuelaprofesional escuelaProfesional, Cicloacademico cicloAcademico) {
    Programacion programacion;

    try {
      Query q = getEntityManager().createNamedQuery("Programacion.findByEscuelaCiclo");
      q.setParameter("idEscuelaProfesional", escuelaProfesional);
      q.setParameter("idCicloAcademico", cicloAcademico);
      programacion = (Programacion) q.getSingleResult();
    } catch (NoResultException e) {
      programacion = null;
    }
    return programacion;
  }
}
