/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Programacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class ProgramacionTutores  implements Serializable{

  /**
   * Creates a new instance of ProgramacionTutores
   */
  
  private static final long serialVersionUID = -2564031884483676327L;  
  public ProgramacionTutores() {
  }
  
  final static Logger logger = Log4jConfig.getLogger(ProgramacionTutores.class.getName());
  
  @EJB
  private com.sot.fachadas.ProgramacionFacadeLocal ejbFacade;
  
  @Inject 
  private LoginController login;
  
  private Programacion progTutores;
  private List<Programacion> items ;
  
  private String ciclo ; // 2016-
  
  @PostConstruct
  void init(){
    progTutores = new Programacion();
    items = new ArrayList<>();
  }
  
  
  public List<Programacion> getItems() {
//    if (items == null && !ciclo.isEmpty()) { //si no existen items y existe ciclo
//      items = ejbFacade.findByCiclo(ciclo); //tambien tiene que filtrar por id de usuario "facultad"
//    }
    return items;
  }
  
  public void cargarProgramacionTutores() {
    if ( ciclo.isEmpty() == false) { //si no existen items y existe ciclo
      //login.getPersonal().getIdEscuelaProfesional().g   //filtrar por facultad too
      if(ejbFacade.findByCiclo(ciclo).isEmpty() == false ){
        items = ejbFacade.findByCiclo(ciclo); //tambien tiene que filtrar por id de usuario "facultad"
        logger.info("EN CARGAR PROGRAMACION TUTORES - true");
      }
      else{
        items = new ArrayList<>();
        logger.info("EN CARGAR PROGRAMACION TUTORES - FALSE");
      }
      
    }
    //return items;
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
  
  
  
  
  
  
}
