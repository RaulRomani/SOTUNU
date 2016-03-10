/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Programaciontutor;
import com.sot.entidades.Tutoria;
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
public class TutoriaFacade extends AbstractFacade<Tutoria> implements TutoriaFacadeLocal {
  @PersistenceContext(unitName = "com.sot_SOTUNU-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public TutoriaFacade() {
    super(Tutoria.class);
  }

  @Override
  public List<Tutoria> findByProgramacionTutor(Programaciontutor programacionTutor) {
    TypedQuery<Tutoria> q = getEntityManager().createNamedQuery("Tutoria.findByProgramacionTutor", Tutoria.class);
    q.setParameter("idProgramacionTutor", programacionTutor);
    List<Tutoria> list;
    try {
      list = q.getResultList();
    } catch (NoResultException e) {
      list = null;
    }
    return list;
  }
  
}
