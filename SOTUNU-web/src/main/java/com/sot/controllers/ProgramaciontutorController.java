package com.sot.controllers;

import com.sot.entidades.Programaciontutor;
import com.sot.controllers.util.JsfUtil;
import com.sot.controllers.util.JsfUtil.PersistAction;
import com.sot.fachadas.ProgramaciontutorFacadeLocal;

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

@Named("programaciontutorController")
@SessionScoped
public class ProgramaciontutorController implements Serializable {

  @EJB
  private com.sot.fachadas.ProgramaciontutorFacadeLocal ejbFacade;
  private List<Programaciontutor> items = null;
  private Programaciontutor selected;

  public ProgramaciontutorController() {
  }

  public Programaciontutor getSelected() {
    return selected;
  }

  public void setSelected(Programaciontutor selected) {
    this.selected = selected;
  }

  protected void setEmbeddableKeys() {
  }

  protected void initializeEmbeddableKey() {
  }

  private ProgramaciontutorFacadeLocal getFacade() {
    return ejbFacade;
  }

  public Programaciontutor prepareCreate() {
    selected = new Programaciontutor();
    initializeEmbeddableKey();
    return selected;
  }

  public void create() {
    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorCreated"));
    if (!JsfUtil.isValidationFailed()) {
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      selected = null; // Remove selection
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public List<Programaciontutor> getItems() {
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

  public Programaciontutor getProgramaciontutor(java.lang.Integer id) {
    return getFacade().find(id);
  }

  public List<Programaciontutor> getItemsAvailableSelectMany() {
    return getFacade().findAll();
  }

  public List<Programaciontutor> getItemsAvailableSelectOne() {
    return getFacade().findAll();
  }

  @FacesConverter(forClass = Programaciontutor.class)
  public static class ProgramaciontutorControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      ProgramaciontutorController controller = (ProgramaciontutorController) facesContext.getApplication().getELResolver().
              getValue(facesContext.getELContext(), null, "programaciontutorController");
      return controller.getProgramaciontutor(getKey(value));
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
      if (object instanceof Programaciontutor) {
        Programaciontutor o = (Programaciontutor) object;
        return getStringKey(o.getIdProgramacionTutor());
      } else {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Programaciontutor.class.getName()});
        return null;
      }
    }

  }

}
