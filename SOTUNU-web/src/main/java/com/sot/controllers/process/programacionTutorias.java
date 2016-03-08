/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import com.sot.controllers.util.Log4jConfig;
import java.io.Serializable;
import javax.ejb.EJB;
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
  
  
  
}
