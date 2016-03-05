/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "tutorado")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tutorado.findAll", query = "SELECT t FROM Tutorado t"),
  @NamedQuery(name = "Tutorado.findByIdTutorado", query = "SELECT t FROM Tutorado t WHERE t.idTutorado = :idTutorado"),
  @NamedQuery(name = "Tutorado.findByNombre", query = "SELECT t FROM Tutorado t WHERE t.nombre = :nombre"),
  @NamedQuery(name = "Tutorado.findByDireccion", query = "SELECT t FROM Tutorado t WHERE t.direccion = :direccion"),
  @NamedQuery(name = "Tutorado.findByApellido", query = "SELECT t FROM Tutorado t WHERE t.apellido = :apellido"),
  @NamedQuery(name = "Tutorado.findByFoto", query = "SELECT t FROM Tutorado t WHERE t.foto = :foto"),
  @NamedQuery(name = "Tutorado.findByFechaNacimiento", query = "SELECT t FROM Tutorado t WHERE t.fechaNacimiento = :fechaNacimiento"),
  @NamedQuery(name = "Tutorado.findByTelefono", query = "SELECT t FROM Tutorado t WHERE t.telefono = :telefono"),
  @NamedQuery(name = "Tutorado.findByCelular", query = "SELECT t FROM Tutorado t WHERE t.celular = :celular"),
  @NamedQuery(name = "Tutorado.findByRpm", query = "SELECT t FROM Tutorado t WHERE t.rpm = :rpm"),
  @NamedQuery(name = "Tutorado.findByTelefonoEmergencia", query = "SELECT t FROM Tutorado t WHERE t.telefonoEmergencia = :telefonoEmergencia"),
  @NamedQuery(name = "Tutorado.findByEmail", query = "SELECT t FROM Tutorado t WHERE t.email = :email"),
  @NamedQuery(name = "Tutorado.findByCaracteristicaParticular", query = "SELECT t FROM Tutorado t WHERE t.caracteristicaParticular = :caracteristicaParticular"),
  @NamedQuery(name = "Tutorado.findByAreasProblema", query = "SELECT t FROM Tutorado t WHERE t.areasProblema = :areasProblema"),
  @NamedQuery(name = "Tutorado.findByObservaciones", query = "SELECT t FROM Tutorado t WHERE t.observaciones = :observaciones"),
  @NamedQuery(name = "Tutorado.findByEstado", query = "SELECT t FROM Tutorado t WHERE t.estado = :estado"),
  @NamedQuery(name = "Tutorado.findByNivelTutoria", query = "SELECT t FROM Tutorado t WHERE t.nivelTutoria = :nivelTutoria")})
public class Tutorado implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idTutorado")
  private Integer idTutorado;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "direccion")
  private String direccion;
  @Size(max = 200)
  @Column(name = "apellido")
  private String apellido;
  @Size(max = 200)
  @Column(name = "foto")
  private String foto;
  @Column(name = "fechaNacimiento")
  @Temporal(TemporalType.DATE)
  private Date fechaNacimiento;
  @Size(max = 20)
  @Column(name = "telefono")
  private String telefono;
  @Size(max = 20)
  @Column(name = "celular")
  private String celular;
  @Size(max = 20)
  @Column(name = "RPM")
  private String rpm;
  @Size(max = 20)
  @Column(name = "telefonoEmergencia")
  private String telefonoEmergencia;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 60)
  @Column(name = "Email")
  private String email;
  @Size(max = 200)
  @Column(name = "caracteristicaParticular")
  private String caracteristicaParticular;
  @Size(max = 300)
  @Column(name = "areasProblema")
  private String areasProblema;
  @Size(max = 400)
  @Column(name = "observaciones")
  private String observaciones;
  @Size(max = 8)
  @Column(name = "estado")
  private String estado;
  @Size(max = 20)
  @Column(name = "nivelTutoria")
  private String nivelTutoria;
  @JoinColumn(name = "idCicloAcademico", referencedColumnName = "idCicloAcademico")
  @ManyToOne(optional = false)
  private Cicloacademico idCicloAcademico;
  @JoinColumn(name = "idEscuelaProfesional", referencedColumnName = "idEscuelaProfesional")
  @ManyToOne(optional = false)
  private Escuelaprofesional idEscuelaProfesional;

  public Tutorado() {
  }

  public Tutorado(Integer idTutorado) {
    this.idTutorado = idTutorado;
  }

  public Tutorado(Integer idTutorado, String nombre, String direccion) {
    this.idTutorado = idTutorado;
    this.nombre = nombre;
    this.direccion = direccion;
  }

  public Integer getIdTutorado() {
    return idTutorado;
  }

  public void setIdTutorado(Integer idTutorado) {
    this.idTutorado = idTutorado;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getRpm() {
    return rpm;
  }

  public void setRpm(String rpm) {
    this.rpm = rpm;
  }

  public String getTelefonoEmergencia() {
    return telefonoEmergencia;
  }

  public void setTelefonoEmergencia(String telefonoEmergencia) {
    this.telefonoEmergencia = telefonoEmergencia;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCaracteristicaParticular() {
    return caracteristicaParticular;
  }

  public void setCaracteristicaParticular(String caracteristicaParticular) {
    this.caracteristicaParticular = caracteristicaParticular;
  }

  public String getAreasProblema() {
    return areasProblema;
  }

  public void setAreasProblema(String areasProblema) {
    this.areasProblema = areasProblema;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getNivelTutoria() {
    return nivelTutoria;
  }

  public void setNivelTutoria(String nivelTutoria) {
    this.nivelTutoria = nivelTutoria;
  }

  public Cicloacademico getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Cicloacademico idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public Escuelaprofesional getIdEscuelaProfesional() {
    return idEscuelaProfesional;
  }

  public void setIdEscuelaProfesional(Escuelaprofesional idEscuelaProfesional) {
    this.idEscuelaProfesional = idEscuelaProfesional;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idTutorado != null ? idTutorado.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tutorado)) {
      return false;
    }
    Tutorado other = (Tutorado) object;
    if ((this.idTutorado == null && other.idTutorado != null) || (this.idTutorado != null && !this.idTutorado.equals(other.idTutorado))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Tutorado[ idTutorado=" + idTutorado + " ]";
  }
  
}
