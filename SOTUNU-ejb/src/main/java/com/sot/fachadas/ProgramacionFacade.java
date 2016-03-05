/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Programacion;
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
  public List<Programacion> findByCiclo(String ciclo) {
    //Query q = getEntityManager().createNamedQuery("Programacion.findByCiclo");
    
    TypedQuery<Programacion> q = getEntityManager().createNamedQuery("Programacion.findByCiclo", Programacion.class);
    q.setParameter("ciclo", ciclo);
    List<Programacion> list;
    try {
      list = q.getResultList();
      
    } catch (NoResultException e) {
      list = null;
    }
    return list;
  }
  
}
