/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import static com.sot.controllers.process.ProgramacionTutores.logger;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Escuelaprofesional;
import com.sot.entidades.Personal;
import com.sot.entidades.Programacion;
import com.sot.entidades.Programaciontutor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

  @Inject
  private LoginController login;

  private Integer idCicloAcademico = null;
  private List<SelectItem> cicloAcademicoList = null;
  
  private Programacion programacion;
  private Programaciontutor programacionTutor;
  
  private Integer idProgramaciónTutor;
  private List<SelectItem> programacionTutorList;

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

  public void cargaPabellonAula(ValueChangeEvent event) {
    try {
      //Integer idCicloAcademico = (Integer) event.getNewValue();
      Personal tutor = login.getUsuario().getIdPersonal();
      Escuelaprofesional escuela = tutor.getIdEscuelaProfesional();
      
        ejbFacadePersonal.find(escuela); //FindDirectorByEscuela
      
      programacion = ejbFacadeProgramacion.find(idCicloAcademico); //find(ciclo, escuela)
      
      List<Programaciontutor> progTutorList = ejbFacadeProgramacionTutor.findAll(); // find (idProg, idTutor)
      
      
      if (programacionTutorList == null) {
      try {
        programacionTutorList = new ArrayList<>();
        //List<Cicloacademico> lista = ejbFacadeCiclo.findAll();
        
        logger.info("getProgramacionTutor - lista de programacion de un Tutor OK");
        for (Programaciontutor pt : progTutorList) {
          SelectItem option = new SelectItem();
          option.setValue(pt.getIdProgramacionTutor());
          option.setLabel(pt.getPabellon() + "-" + pt.getAula());
          programacionTutorList.add(option);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      
    } catch (Exception e) {
      //precio = 0.0;
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

  public Programaciontutor getProgramacionTutor() {
    return programacionTutor;
  }

  public void setProgramacionTutor(Programaciontutor programacionTutor) {
    this.programacionTutor = programacionTutor;
  }

  

  
  
  
  
}
