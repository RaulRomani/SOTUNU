/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import com.sot.controllers.PersonalController;
import com.sot.controllers.util.JsfUtil;
import com.sot.controllers.util.JsfUtil.PersistAction;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Escuelaprofesional;
import com.sot.entidades.Personal;
import com.sot.entidades.Programacion;
import com.sot.entidades.Programaciontutor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Raul
 */
@Named(value = "programacionTutores")
@ViewScoped
public class ProgramacionTutores implements Serializable {

  /**
   * Creates a new instance of ProgramacionTutores
   */
  private static final long serialVersionUID = -2564031884483676327L;

  public ProgramacionTutores() {
  }

  final static Logger logger = Log4jConfig.getLogger(ProgramacionTutores.class.getName());

  @EJB
  private com.sot.fachadas.ProgramacionFacadeLocal ejbFacadeProgramacion;

  @EJB
  private com.sot.fachadas.CicloacademicoFacadeLocal ejbFacadeCiclo;
  
  @EJB
  private com.sot.fachadas.PersonalFacadeLocal ejbFacadePersonal;
  
  @EJB
  private com.sot.fachadas.ProgramaciontutorFacadeLocal ejbFacadeProgramacionTutor;

  @Inject
  private LoginController login;

  private Programacion progTutores;
  private Programacion programacion=null;
  private List<Programaciontutor> programacionTutorList = null;
  private Programaciontutor programacionTutorSelected;
  
  private String ciclo; // 2016-
  private Integer idCicloAcademico = null; // 2016-
  private List<SelectItem> cicloAcademicoList=null;
  
  private Integer idTutor = null; 
  private List<SelectItem> TutorList=null;

  @PostConstruct
  void init() {
    progTutores = new Programacion();
  }


  public void cargarProgramacionTutores() {

    if (idCicloAcademico != null) { //si ciclo academico no ha sido seteado
      Cicloacademico ca = new Cicloacademico();
      ca.setIdCicloAcademico(idCicloAcademico);
      // Busqueda por usuario y ciclo académico
      programacion = ejbFacadeProgramacion.findProgramacionDirector(login.getUsuario(), ca);  //Una programacion por ciclo
      
      if (programacion == null) {
        programacionTutorList = null;
        logger.info("No ENCONTRO PROGRAMACION DEL TUTOR");
      } else {
        programacionTutorList = ejbFacadeProgramacionTutor.findByIdProgramacion(programacion);
        logger.info("SE CARGO EL DETALLE DE LA PROGRAMACIÓN DEL TUTOR");
      }
    }
  }
  
  public void crearDetalleProgramacion() {
    programacionTutorSelected.setIdProgramacion(programacion);
    Personal tutor = new Personal();
    tutor.setIdPersonal(idTutor);
    programacionTutorSelected.setIdPersonal(tutor);
    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorCreated"));
    if (!JsfUtil.isValidationFailed()) {
      programacionTutorList = null;    // Invalidate list of items to trigger re-query.
    }
  }
  
  public Programaciontutor prepareCreate() {
    programacionTutorSelected = new Programaciontutor();
    return programacionTutorSelected;
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      programacionTutorSelected = null; // Remove selection
      programacionTutorList = null;    // Invalidate list of items to trigger re-query.
    }
  }
  
  private void persist(PersistAction persistAction, String successMessage) {
    if (programacionTutorSelected != null) {
      try {
        if (persistAction != PersistAction.DELETE) {
          ejbFacadeProgramacionTutor.edit(programacionTutorSelected);
        } else {
          ejbFacadeProgramacionTutor.remove(programacionTutorSelected);
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
        java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  public Programacion getProgTutores() {
    return progTutores;
  }

  public void setProgTutores(Programacion progTutores) {
    this.progTutores = progTutores;
  }

  public Integer getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Integer idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public Programacion getProgramacion() {
    return programacion;
  }
  
  

  public List<SelectItem> getCicloAcademicoList() {
    if (cicloAcademicoList == null) {
      try {
        cicloAcademicoList = new ArrayList<>();
        List<Cicloacademico> lista = ejbFacadeCiclo.findAll();
        logger.info("getCicloAcademico OK");
        for (Cicloacademico c : lista) {
          SelectItem option = new SelectItem();
          option.setValue(c.getIdCicloAcademico());
          option.setLabel(c.getAño() + "-" + c.getPeriodo());
          cicloAcademicoList.add(option);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return cicloAcademicoList;
  }
  
  public List<SelectItem> getTutorList() {
    if (TutorList == null) {
      try {
        TutorList = new ArrayList<>();
        Escuelaprofesional escuela = login.getUsuario().getIdPersonal().getIdEscuelaProfesional();
        List<Personal> lista = ejbFacadePersonal.findByEscuelaProfesional(escuela, "tutor");
        logger.info("getTutorList OK");
        for (Personal p : lista) {
          SelectItem option = new SelectItem();
          option.setValue(p.getIdPersonal());
          option.setLabel(p.getNombre()+ " " + p.getApellido());
          TutorList.add(option);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return TutorList;
  }

  public Integer getIdTutor() {
    return idTutor;
  }

  public void setIdTutor(Integer idTutor) {
    this.idTutor = idTutor;
  }

  public List<Programaciontutor> getProgramacionTutorList() {
//    if(programacionTutorList!=null){
//      programacionTutorList = ejbFacadeProgramacionTutor.findAll();// (idProgramacion)
//    }
    if(idCicloAcademico != null && programacion != null)
      programacionTutorList = ejbFacadeProgramacionTutor.findByIdProgramacion(programacion);
    return programacionTutorList;
  }

  public Programaciontutor getProgramacionTutorSelected() {
    return programacionTutorSelected;
  }

  public void setProgramacionTutorSelected(Programaciontutor programacionTutorSelected) {
    this.programacionTutorSelected = programacionTutorSelected;
  }
  

}
