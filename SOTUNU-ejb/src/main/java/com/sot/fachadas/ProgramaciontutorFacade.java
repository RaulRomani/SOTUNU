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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Raul
 */
@Stateless
public class ProgramaciontutorFacade extends AbstractFacade<Programaciontutor> implements ProgramaciontutorFacadeLocal {
  @PersistenceContext(unitName = "com.sot_SOTUNU-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ProgramaciontutorFacade() {
    super(Programaciontutor.class);
  }

  @Override
  public List<Programaciontutor> findByIdProgramacion(Programacion idProgramacion) {
    TypedQuery<Programaciontutor> q = getEntityManager().createNamedQuery("Programaciontutor.findByIdProgramacion", Programaciontutor.class);
    q.setParameter("idProgramacion", idProgramacion);
    List<Programaciontutor> list;
    try {
      list = q.getResultList();
    } catch (NoResultException e) {
      list = null;
    }
    return list;
  }

  @Override
  public List<Programaciontutor> findByProgramacionTutor(Programacion Programacion, Personal tutor) {
    TypedQuery<Programaciontutor> q = getEntityManager().createNamedQuery("Programaciontutor.findByProgramacionTutor", Programaciontutor.class);
    q.setParameter("idProgramacion", Programacion);
    q.setParameter("idPersonal", tutor);
    List<Programaciontutor> list;
    try {
      list = q.getResultList();
    } catch (NoResultException e) {
      list = null;
    }
    return list;
  }

}
