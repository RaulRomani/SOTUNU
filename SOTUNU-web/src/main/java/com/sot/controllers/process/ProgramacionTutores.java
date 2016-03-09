/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import com.sot.controllers.util.AccesoDB;
import com.sot.controllers.util.JsfUtil;
import com.sot.controllers.util.JsfUtil.PersistAction;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Escuelaprofesional;
import com.sot.entidades.Facultad;
import com.sot.entidades.Personal;
import com.sot.entidades.Programacion;
import com.sot.entidades.Programaciontutor;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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

  private Programacion programacion = null;
  private List<Programaciontutor> programacionTutorList = null;
  private Programaciontutor programacionTutorSelected;

  private Integer idCicloAcademico = null; // 2016-
  private List<SelectItem> cicloAcademicoList = null;

  private Integer idTutor = null;
  private List<SelectItem> TutorList = null;

  private Escuelaprofesional escuelaProfesional;

  @PostConstruct
  void init() {
  }

  public void cargarProgramacionTutores() {

    if (idCicloAcademico != null) { //si ciclo academico no ha sido seteado
      Cicloacademico ca = new Cicloacademico();
      ca.setIdCicloAcademico(idCicloAcademico);
      // Busqueda por usuario(Director) y ciclo académico
      programacion = ejbFacadeProgramacion.findProgramacionDirector(login.getUsuario(), ca);  //Una programacion por ciclo

      if (programacion == null) {  //Si no hay programacion de un Director - CicloAcadémico
        programacionTutorList = null;
        logger.info("No ENCONTRO PROGRAMACION DEL TUTOR");
      } else {
        programacionTutorList = ejbFacadeProgramacionTutor.findByIdProgramacion(programacion);
        logger.info("SE CARGO EL DETALLE DE LA PROGRAMACIÓN DEL TUTOR");
      }
    }
  }

  public void crearDetalleProgramacion() {

//    Escuelaprofesional ep = new Escuelaprofesional();
//    ep.setIdEscuelaProfesional(getEscuelaProfesional().getIdEscuelaProfesional());
    logger.info("Escuela: " + getEscuelaProfesional().getNombre());

    Personal tutor = new Personal();
    tutor.setIdPersonal(idTutor);
    programacionTutorSelected.setIdPersonal(tutor);

    if (programacion == null) {  //Cuando no hay ninguna programacion del director en un ciclo académico

      programacion = new Programacion();
      programacion.setIdEscuelaProfesional(getEscuelaProfesional());
      programacion.setIdUsuario(login.getUsuario());
      programacion.setIdCicloAcademico(new Cicloacademico(idCicloAcademico));

      //Lista de programacion de un tutor
      List<Programaciontutor> ptList = new ArrayList<>();
      ptList.add(programacionTutorSelected);
      
      
      programacion.setProgramaciontutorList(ptList);
      
      //Una programacion de tutor esta asociado con una programación de Director
      programacionTutorSelected.setIdProgramacion(programacion);
      
      
      //programacion.getProgramaciontutorList().add(programacionTutorSelected);
      
      //save jpa many to one
      ejbFacadeProgramacion.create(programacion);
      //ejbFacadeProgramacionTutor.create(programacionTutorSelected);

      logger.info("SE AGREGO UNA PROGRAMACIÓN Y SU DETALLE");
    } else {

      programacionTutorSelected.setIdProgramacion(programacion);

      logger.info("SE AGREGO UN DETALLE DE PROGRAMACION EXISTENTE");

      persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProgramaciontutorCreated"));
      if (!JsfUtil.isValidationFailed()) {
        programacionTutorList = null;    // Invalidate list of items to trigger re-query.
      }
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

  public Integer getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Integer idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public Programacion getProgramacion() {
    return programacion;
  }

  public void setProgramacion(Programacion programacion) {
    this.programacion = programacion;
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
        Escuelaprofesional escuela = getEscuelaProfesional();
        List<Personal> lista = ejbFacadePersonal.findByEscuelaProfesional(escuela, "tutor");
        logger.info("getTutorList OK");
        for (Personal p : lista) {
          SelectItem option = new SelectItem();
          option.setValue(p.getIdPersonal());
          option.setLabel(p.getNombre() + " " + p.getApellido());
          TutorList.add(option);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return TutorList;
  }

  public Escuelaprofesional getEscuelaProfesional() {
    if (escuelaProfesional == null) {
      escuelaProfesional = login.getUsuario().getIdPersonal().getIdEscuelaProfesional();
    }

    return escuelaProfesional;
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
    if (idCicloAcademico != null && programacion != null) {
      programacionTutorList = ejbFacadeProgramacionTutor.findByIdProgramacion(programacion);
    }
    return programacionTutorList;
  }

  public Programaciontutor getProgramacionTutorSelected() {
    return programacionTutorSelected;
  }

  public void setProgramacionTutorSelected(Programaciontutor programacionTutorSelected) {
    this.programacionTutorSelected = programacionTutorSelected;
  }

  public void exportarPDF() throws JRException, IOException, NamingException, SQLException, Exception {

    Map<String, Object> parametro = new HashMap<>();
    Cicloacademico ca = ejbFacadeCiclo.find(idCicloAcademico);
    Personal director = login.getUsuario().getIdPersonal();
    Escuelaprofesional escuela = director.getIdEscuelaProfesional();
    Facultad facultad = escuela.getIdFacultad();

    parametro.put("idProgramacion", programacion.getIdProgramacion());
    parametro.put("cicloAcademico", ca.getAño() + "-" + ca.getPeriodo());
    parametro.put("facultad", facultad.getNombre());
    parametro.put("escuelaProfesional", escuela.getNombre());
    parametro.put("director", director.getNombre() + " " + director.getApellido());

    //ejbFacadeProgramacionTutor.
    File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/programacionTutores/programacionTutores.jasper"));
    logger.info("OK exportarPDF, idProg: " + programacion.getIdProgramacion());

    Connection con = AccesoDB.getConnection();
    //java.sql.Connection co = em.unwrap(java.sql.Connection.class);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con);

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-disposition", "attachment; filename=Programacion Tutores.pdf");
    ServletOutputStream stream = response.getOutputStream();

    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    JasperExportManager.exportReportToPdfFile(jasperPrint, "D://programacionTutores.pdf");
    stream.flush();
    stream.close();

    FacesContext.getCurrentInstance().responseComplete();
    //con.close();
  }

}
