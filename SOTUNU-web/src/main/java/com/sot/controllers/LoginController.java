/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.controllers;

import com.sot.controllers.util.Log4jConfig;
import com.sot.entidades.Personal;
import com.sot.entidades.Usuario;
import com.sot.tests.Log4j;
import java.io.IOException;
//import com.sot.fachadas.PersonalFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
//import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Raul
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

  /**
   * Creates a new instance of LoginController
   */
  public LoginController() {
  }

  private static final long serialVersionUID = -2564031884483676327L;
  
  //final static Logger logger = Logger.getLogger(LoginController.class.getName());
  
  final static Logger logger = Log4jConfig.getLogger(LoginController.class.getName());

  

  private Usuario usuario;
  private Personal personal;
  
  private String mensaje = null;
  private String mensajeError = null;

  @PostConstruct
  void init() {
    usuario = new Usuario();
    personal = new Personal();
    logger.info("Inicio login constructor");
    BasicConfigurator.configure();
  }

  //@Inject
  //private transient Logger logger;
  @EJB
  private com.sot.fachadas.UsuarioFacadeLocal ejbFacade;

  @EJB
  private com.sot.fachadas.PersonalFacadeLocal ejbFacadePersonal;

  public void checkSession() {

    FacesContext context = FacesContext.getCurrentInstance();
    
    Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("user");
    if(u == null){
      
      
      
      context.getExternalContext().getRequestMap().put("mensaje", "Permisos insuficientes, por favor inicie sesión");
      
      context.getExternalContext().getFlash().setKeepMessages(true);
      context.getExternalContext().getFlash().setRedirect(true);
      
//        Flash flash = context.getExternalContext().getFlash();
//        flash.setKeepMessages(true);
//        flash.setRedirect(true);
      
      RequestContext.getCurrentInstance().update("growl");
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Permisos insuficientes, por favor inicie sesión"));
      
      //mensaje = "Permisos insuficientes, por favor inicie sesión";
      
      logger.info("exito -- seguridad last");
      //String contextPath = FacesContext.getCurrentInstance().getExternalContext().getContextName();
      //context.getExternalContext().redirect(contextPath + "/faces/login.xhtml");
      
      context.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login?faces-redirect=true");
      
    } else {
      logger.info("Sesion de usuario existente!");
      //FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
      
    }

  }

  public String validar() {

    Usuario u = null;
    try{
      u = ejbFacade.validar(usuario.getUsuario(), usuario.getClave());
    } catch (Exception e){
      mensajeError = e.getMessage();
    }
    if (u != null) {
      setUsuario(u);
      personal = u.getIdPersonal();//ejbFacadePersonal.find(u.getIdPersonal());

      //creamos una sesion jsf usuario
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
      
      RequestContext.getCurrentInstance().update("growl");

      //FacesContext context = FacesContext.getCurrentInstance();
      //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",personal.getNombres()));
      return "index?faces-redirect=true"; // Pagina a Redireccionar
    } else {
      RequestContext.getCurrentInstance().update("growl");
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Usuario o contraseña es invalido", "Usuario o contraseña es invalido"));
      return "";

      //return "main?faces-redirect=true"; // Pagina a Redireccionar
    }

  }

  /**
   * Listen for logout button clicks on the #{loginController.logout} action and
   * navigates to login screen.
   */
  public void logout() {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    //logger.log(Level.INFO, "User ({0}) loging out #" , request.getUserPrincipal().getName());
    if (session != null) {
      session.invalidate();
    }
    FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login?faces-redirect=true");
  }

  public String logoutButton() {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    //logger.log(Level.INFO, "User ({0}) loging out #" , request.getUserPrincipal().getName());
    if (session != null) {
      session.invalidate();
    }
    return "/login?faces-redirect=true";
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Personal getPersonal() {
    return personal;
  }

  public void setPersonal(Personal personal) {
    this.personal = personal;
  }

  public String getMensaje() {
    return mensaje;
  }

  public String getMensajeError() {
    return mensajeError;
  }

  public void setMensajeError(String mensajeError) {
    this.mensajeError = mensajeError;
  }
  
  
  

}
