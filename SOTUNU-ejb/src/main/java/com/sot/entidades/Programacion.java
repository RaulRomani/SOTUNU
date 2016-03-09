/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "programacion")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Programacion.findAll", query = "SELECT p FROM Programacion p"),
  @NamedQuery(name = "Programacion.findByIdProgramacion", query = "SELECT p FROM Programacion p WHERE p.idProgramacion = :idProgramacion"),
  @NamedQuery(name = "Programacion.findByFechaHora", query = "SELECT p FROM Programacion p WHERE p.fechaHora = :fechaHora"),
  @NamedQuery(name = "Programacion.findProgramacionDirector", query = "SELECT p FROM Programacion p WHERE p.idUsuario = :idUsuario AND  p.idCicloAcademico = :idCicloAcademico")})
public class Programacion implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idProgramacion")
  private Integer idProgramacion;
  @Basic(optional = false)
  @Column(name = "fechaHora")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaHora;
  @JoinColumn(name = "idEscuelaProfesional", referencedColumnName = "idEscuelaProfesional")
  @ManyToOne(optional = false)
  private Escuelaprofesional idEscuelaProfesional;
  @JoinColumn(name = "idCicloAcademico", referencedColumnName = "idCicloAcademico")
  @ManyToOne(optional = false)
  private Cicloacademico idCicloAcademico;
  @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario idUsuario;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramacion")
  private List<Programaciontutor> programaciontutorList;

  public Programacion() {
  }

  public Programacion(Integer idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  public Programacion(Integer idProgramacion, Date fechaHora) {
    this.idProgramacion = idProgramacion;
    this.fechaHora = fechaHora;
  }

  public Integer getIdProgramacion() {
    return idProgramacion;
  }

  public void setIdProgramacion(Integer idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  public Date getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(Date fechaHora) {
    this.fechaHora = fechaHora;
  }

  public Escuelaprofesional getIdEscuelaProfesional() {
    return idEscuelaProfesional;
  }

  public void setIdEscuelaProfesional(Escuelaprofesional idEscuelaProfesional) {
    this.idEscuelaProfesional = idEscuelaProfesional;
  }

  public Cicloacademico getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Cicloacademico idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public Usuario getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Usuario idUsuario) {
    this.idUsuario = idUsuario;
  }

  @XmlTransient
  public List<Programaciontutor> getProgramaciontutorList() {
    if(programaciontutorList == null) 
      programaciontutorList = new ArrayList<>();
    return programaciontutorList;
  }

  public void setProgramaciontutorList(List<Programaciontutor> programaciontutorList) {
    this.programaciontutorList = programaciontutorList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idProgramacion != null ? idProgramacion.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Programacion)) {
      return false;
    }
    Programacion other = (Programacion) object;
    if ((this.idProgramacion == null && other.idProgramacion != null) || (this.idProgramacion != null && !this.idProgramacion.equals(other.idProgramacion))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Programacion[ idProgramacion=" + idProgramacion + " ]";
  }
  
}
