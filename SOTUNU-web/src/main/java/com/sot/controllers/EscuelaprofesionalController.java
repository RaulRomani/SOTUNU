package com.sot.controllers;

import com.sot.entidades.Escuelaprofesional;
import com.sot.controllers.util.JsfUtil;
import com.sot.controllers.util.JsfUtil.PersistAction;
import com.sot.fachadas.EscuelaprofesionalFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("escuelaprofesionalController")
@SessionScoped
public class EscuelaprofesionalController implements Serializable {

  @EJB
  private com.sot.fachadas.EscuelaprofesionalFacadeLocal ejbFacade;
  private List<Escuelaprofesional> items = null;
  private Escuelaprofesional selected;

  public EscuelaprofesionalController() {
  }

  public Escuelaprofesional getSelected() {
    return selected;
  }

  public void setSelected(Escuelaprofesional selected) {
    this.selected = selected;
  }

  protected void setEmbeddableKeys() {
  }

  protected void initializeEmbeddableKey() {
  }

  private EscuelaprofesionalFacadeLocal getFacade() {
    return ejbFacade;
  }

  public Escuelaprofesional prepareCreate() {
    selected = new Escuelaprofesional();
    initializeEmbeddableKey();
    return selected;
  }

  public void create() {
    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EscuelaprofesionalCreated"));
    if (!JsfUtil.isValidationFailed()) {
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EscuelaprofesionalUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EscuelaprofesionalDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      selected = null; // Remove selection
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public List<Escuelaprofesional> getItems() {
    if (items == null) {
      items = getFacade().findAll();
    }
    return items;
  }

  private void persist(PersistAction persistAction, String successMessage) {
    if (selected != null) {
      setEmbeddableKeys();
      try {
        if (persistAction != PersistAction.DELETE) {
          getFacade().edit(selected);
        } else {
          getFacade().remove(selected);
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public Escuelaprofesional getEscuelaprofesional(java.lang.Integer id) {
    return getFacade().find(id);
  }

  public List<Escuelaprofesional> getItemsAvailableSelectMany() {
    return getFacade().findAll();
  }

  public List<Escuelaprofesional> getItemsAvailableSelectOne() {
    return getFacade().findAll();
  }

  @FacesConverter(forClass = Escuelaprofesional.class)
  public static class EscuelaprofesionalControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      EscuelaprofesionalController controller = (EscuelaprofesionalController) facesContext.getApplication().getELResolver().
              getValue(facesContext.getELContext(), null, "escuelaprofesionalController");
      return controller.getEscuelaprofesional(getKey(value));
    }

    java.lang.Integer getKey(String value) {
      java.lang.Integer key;
      key = Integer.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Integer value) {
      StringBuilder sb = new StringBuilder();
      sb.append(value);
      return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof Escuelaprofesional) {
        Escuelaprofesional o = (Escuelaprofesional) object;
        return getStringKey(o.getIdEscuelaProfesional());
      } else {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Escuelaprofesional.class.getName()});
        return null;
      }
    }

  }

}
