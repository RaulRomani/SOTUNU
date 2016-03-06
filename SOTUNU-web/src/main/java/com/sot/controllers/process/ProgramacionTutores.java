/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers.process;

import com.sot.controllers.LoginController;
import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Cicloacademico;
import com.sot.entidades.Programacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

  @Inject
  private LoginController login;

  private Programacion progTutores;
  private List<Programacion> items;

  private String ciclo; // 2016-
  private Integer idCicloAcademico = null; // 2016-
  private List<SelectItem> cicloAcademicoList=null;

  @PostConstruct
  void init() {
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

    if (idCicloAcademico != null) { //si no existen items y existe ciclo
      //login.getPersonal().getIdEscuelaProfesional().g   //filtrar por facultad too
      
      
      //Integer idUsuario = login.getUsuario().getIdUsuario();
      Cicloacademico ca = new Cicloacademico();
      ca.setIdCicloAcademico(idCicloAcademico);
      items = ejbFacadeProgramacion.findProgramacionDirector(login.getUsuario(), ca);
      if (items.isEmpty() == true) {
        items = new ArrayList<>();
        logger.info("CARGAR PROGRAMACION TUTORES - FALSE");
        //items = ejbFacade.findByCiclo(ciclo); //tambien tiene que filtrar por id de usuario "facultad"
        //logger.info("CARGAR PROGRAMACION TUTORES - true");
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

  public Integer getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Integer idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
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

//  public List<SelectItem> getClientes() {
//    if (clientes == null) {
//      try {
//        clientes = new ArrayList<SelectItem>();
//        List<Cliente> lista = serviceFactory.getClienteService().listar();
//        for (Cliente c : lista) {
//          SelectItem op = new SelectItem();
//          op.setValue(c.getId());
//          op.setLabel(c.getNombre());
//          clientes.add(op);
//        }
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//
//    return clientes;
//  }

}
