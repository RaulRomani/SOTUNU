/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import static com.sot.controllers.process.ProgramacionTutores.logger;
import com.sot.controllers.util.AccesoDB;
import com.sot.controllers.util.JsfUtil;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Escuelaprofesional;
import com.sot.entidades.Facultad;
import com.sot.entidades.Personal;
import com.sot.entidades.Programacion;
import com.sot.entidades.Programaciontutor;
import com.sot.entidades.Tutoria;
import com.sot.entidades.Usuario;
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
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
@Named(value = "programacionTutorias")
@ViewScoped
public class programacionTutorias implements Serializable {

  private static final long serialVersionUID = -2564031884483676327L;

  final static Logger logger = Log4jConfig.getLogger(ProgramacionTutores.class.getName());

  @EJB
  private com.sot.fachadas.ProgramacionFacadeLocal ejbFacadeProgramacion;

  @EJB
  private com.sot.fachadas.CicloacademicoFacadeLocal ejbFacadeCiclo;

  @EJB
  private com.sot.fachadas.PersonalFacadeLocal ejbFacadePersonal;

  @EJB
  private com.sot.fachadas.ProgramaciontutorFacadeLocal ejbFacadeProgramacionTutor;
  
  @EJB
  private com.sot.fachadas.TutoriaFacadeLocal ejbFacadeTutoria;

  @Inject
  private LoginController login;

  private Integer idCicloAcademico = null;
  private List<SelectItem> cicloAcademicoList = null;

  private Programacion programacion;
  private Programaciontutor programacionTutor;

  private Integer idProgramacionTutor;
  private List<SelectItem> programacionTutorList = null;
  
  private List<Tutoria> tutoriaList = null;
  private Tutoria tutoriaSelected ;

  private Escuelaprofesional escuelaProfesional;

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

  public List<SelectItem> getProgramacionTutorList() {
    return programacionTutorList;
  }

  public void cargarPabellonAula() {
    try {
      //Integer idCicloAcademico = (Integer) event.getNewValue();
      Personal tutor = login.getUsuario().getIdPersonal();
      Escuelaprofesional escuela = tutor.getIdEscuelaProfesional();

      programacion = ejbFacadeProgramacion.findByEscuelaCiclo(escuela, new Cicloacademico(idCicloAcademico));

      List<Programaciontutor> progTutorList = ejbFacadeProgramacionTutor.findByProgramacionTutor(programacion, tutor);

      logger.info("lista de programacion de un Tutor OK");
//      if (programacionTutorList == null) {
        try {
          programacionTutorList = new ArrayList<>();
        //List<Cicloacademico> lista = ejbFacadeCiclo.findAll();

          logger.info("getProgramacionTutor - lista de programacion de un Tutor OK");
          for (Programaciontutor pt : progTutorList) {
            SelectItem option = new SelectItem();
            option.setValue(pt.getIdProgramacionTutor());
            option.setLabel(pt.getPabellon() + " / " + pt.getAula());
            programacionTutorList.add(option);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
//      }

    } catch (Exception e) {
      //precio = 0.0;
    }

  }

  public void cargarTutorias() {
    
    tutoriaList = ejbFacadeTutoria.findByProgramacionTutor(new Programaciontutor(idProgramacionTutor));
    tutoriaSelected = null;
    logger.info("cargarTutorias - true, tutoriaSelected=null");
  }

  public Escuelaprofesional getEscuelaProfesional() {
    if (escuelaProfesional == null) {
      escuelaProfesional = login.getUsuario().getIdPersonal().getIdEscuelaProfesional();
    }

    return escuelaProfesional;
  }
  
  public Tutoria prepareCreateTutoria() {
    tutoriaSelected = new Tutoria();
    logger.info("prepareCreatedTutoria - true, new Tutoria");
    return tutoriaSelected;
  }
  
  public void createTutoria() {
    
    Usuario usuario = login.getUsuario();
    Programaciontutor pt = new Programaciontutor(idProgramacionTutor);
    
    tutoriaSelected.setIdUsuario(usuario);
    tutoriaSelected.setIdProgramacionTutor(pt);
    
    persistTutoria(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TutoriaCreated"));
    if (!JsfUtil.isValidationFailed()) {
      tutoriaList = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public void updateTutoria() {
    persistTutoria(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TutoriaUpdated"));
  }

  public void destroyTutoria() {
    persistTutoria(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TutoriaDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      tutoriaSelected = null; // Remove selection
      tutoriaList = null;    // Invalidate list of items to trigger re-query.
    }
  }

  private void persistTutoria(JsfUtil.PersistAction persistAction, String successMessage) {
    if (tutoriaSelected != null) {
      try {
        if (persistAction != JsfUtil.PersistAction.DELETE) {
          ejbFacadeTutoria.edit(tutoriaSelected);
        } else {
          ejbFacadeTutoria.remove(tutoriaSelected);
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
  
  
  public void reporteProgramacionVisitasPDF() throws JRException, IOException, NamingException, SQLException, Exception {

    Map<String, Object> parametro = new HashMap<>();
    Cicloacademico ca = ejbFacadeCiclo.find(idCicloAcademico);
    Personal tutor = login.getUsuario().getIdPersonal();
    Escuelaprofesional escuela = tutor.getIdEscuelaProfesional();
    Facultad facultad = escuela.getIdFacultad();
    Programaciontutor progTutor = new Programaciontutor(idProgramacionTutor);

    parametro.put("idProgramacionTutor", progTutor.getIdProgramacionTutor());
    parametro.put("cicloAcademico", ca.getAño() + "-" + ca.getPeriodo());
    parametro.put("facultad", facultad.getNombre());
    parametro.put("escuelaProfesional", escuela.getNombre());
    parametro.put("tutor", tutor.getNombre() + " " + tutor.getApellido());

    //ejbFacadeProgramacionTutor.
    File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/tutorias/programacionVisitas.jasper"));
    logger.info("OK exportarPDF, idProg: " + progTutor.getIdProgramacionTutor());

    Connection con = AccesoDB.getConnection();
    //java.sql.Connection co = em.unwrap(java.sql.Connection.class);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con);

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-disposition", "attachment; filename=Programacion de visitas a salones.pdf");
    ServletOutputStream stream = response.getOutputStream();

    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    JasperExportManager.exportReportToPdfFile(jasperPrint, "D://ProgramacionDeVisitasSalones.pdf");
    stream.flush();
    stream.close();

    FacesContext.getCurrentInstance().responseComplete();
    //con.close();
  }
  
  public void reporteRegistroTutoriasPDF() throws JRException, IOException, NamingException, SQLException, Exception {

    Map<String, Object> parametro = new HashMap<>();
    Cicloacademico ca = ejbFacadeCiclo.find(idCicloAcademico);
    Personal tutor = login.getUsuario().getIdPersonal();
    Escuelaprofesional escuela = tutor.getIdEscuelaProfesional();
    Facultad facultad = escuela.getIdFacultad();
    Programaciontutor progTutor = new Programaciontutor(idProgramacionTutor);

    parametro.put("idProgramacionTutor", progTutor.getIdProgramacionTutor());
    parametro.put("cicloAcademico", ca.getAño() + "-" + ca.getPeriodo());
    parametro.put("facultad", facultad.getNombre());
    parametro.put("escuelaProfesional", escuela.getNombre());
    parametro.put("tutor", tutor.getNombre() + " " + tutor.getApellido());

    //ejbFacadeProgramacionTutor.
    File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/tutorias/registroTutoria.jasper"));
    logger.info("OK exportarPDF, idProg: " + progTutor.getIdProgramacionTutor());

    Connection con = AccesoDB.getConnection();
    //java.sql.Connection co = em.unwrap(java.sql.Connection.class);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con);

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-disposition", "attachment; filename=Registro de tutorias.pdf");
    ServletOutputStream stream = response.getOutputStream();

    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    JasperExportManager.exportReportToPdfFile(jasperPrint, "D://RegistroDeTutorias.pdf");
    stream.flush();
    stream.close();

    FacesContext.getCurrentInstance().responseComplete();
    //con.close();
  }
  
  public void loadTutoriaSelected(Integer idTutoria){
    tutoriaSelected = ejbFacadeTutoria.find(idTutoria);
    logger.info("loadTutoriaSelected OK");
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

  public Programaciontutor getProgramacionTutor() {
    return programacionTutor;
  }

  public void setProgramacionTutor(Programaciontutor programacionTutor) {
    this.programacionTutor = programacionTutor;
  }

  public Integer getIdProgramacionTutor() {
    return idProgramacionTutor;
  }

  public void setIdProgramacionTutor(Integer idProgramacionTutor) {
    this.idProgramacionTutor = idProgramacionTutor;
  }

  public List<Tutoria> getTutoriaList() {
    
    if (idCicloAcademico != null && programacion != null) {
      tutoriaList = ejbFacadeTutoria.findByProgramacionTutor(new Programaciontutor(idProgramacionTutor));
    }
    return tutoriaList;
  }

  public void setTutoriaList(List<Tutoria> tutoriaList) {
    this.tutoriaList = tutoriaList;
  }

  public Tutoria getTutoriaSelected() {
    return tutoriaSelected;
  }

  public void setTutoriaSelected(Tutoria tutoriaSelected) {
    this.tutoriaSelected = tutoriaSelected;
  }
  
  

}
