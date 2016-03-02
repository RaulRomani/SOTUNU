/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.fachadas;

import com.sot.entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Raul
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
  @PersistenceContext(unitName = "com.sot_SOTUNU-ejb_ejb_1.0PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public UsuarioFacade() {
    super(Usuario.class);
  }
  
  @Override
  public Usuario validar(String u, String p) {
    Query q = getEntityManager().createNamedQuery("Usuario.validar");
    
    q.setParameter("usuario", u);
    q.setParameter("clave", p);
    
    Usuario usuario;
    try {
      usuario = (Usuario) q.getSingleResult();
      
    } catch (NoResultException e) {
      usuario = null;
    }
    return usuario;
  }
  
}
