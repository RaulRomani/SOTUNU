/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
  @NamedQuery(name = "Programacion.findByCiclo", query = "SELECT p FROM Programacion p WHERE p.ciclo = :ciclo"),
  @NamedQuery(name = "Programacion.findBySalon", query = "SELECT p FROM Programacion p WHERE p.salon = :salon"),
  @NamedQuery(name = "Programacion.findByPabellon", query = "SELECT p FROM Programacion p WHERE p.pabellon = :pabellon"),
  @NamedQuery(name = "Programacion.findByNroEstudiantes", query = "SELECT p FROM Programacion p WHERE p.nroEstudiantes = :nroEstudiantes"),
  @NamedQuery(name = "Programacion.findByDelegado", query = "SELECT p FROM Programacion p WHERE p.delegado = :delegado"),
  @NamedQuery(name = "Programacion.findProgramacionDirector", query = "SELECT p FROM Programacion p WHERE p.idUsuario = :idUsuario AND  p.idCicloAcademico = :idCicloAcademico")
  })
public class Programacion implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idProgramacion")
  private Integer idProgramacion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "ciclo")
  private String ciclo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "salon")
  private String salon;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "pabellon")
  private String pabellon;
  @Basic(optional = false)
  @NotNull
  @Column(name = "nroEstudiantes")
  private int nroEstudiantes;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 80)
  @Column(name = "delegado")
  private String delegado;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramacion")
  private List<Tutoria> tutoriaList;
  @JoinColumn(name = "idEscuelaProfesional", referencedColumnName = "idEscuelaProfesional")
  @ManyToOne(optional = false)
  private Escuelaprofesional idEscuelaProfesional;
  @JoinColumn(name = "idCicloAcademico", referencedColumnName = "idCicloAcademico")
  @ManyToOne(optional = false)
  private Cicloacademico idCicloAcademico;
  @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario idUsuario;

  public Programacion() {
  }

  public Programacion(Integer idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  public Programacion(Integer idProgramacion, String ciclo, String salon, String pabellon, int nroEstudiantes, String delegado) {
    this.idProgramacion = idProgramacion;
    this.ciclo = ciclo;
    this.salon = salon;
    this.pabellon = pabellon;
    this.nroEstudiantes = nroEstudiantes;
    this.delegado = delegado;
  }

  public Integer getIdProgramacion() {
    return idProgramacion;
  }

  public void setIdProgramacion(Integer idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  public String getSalon() {
    return salon;
  }

  public void setSalon(String salon) {
    this.salon = salon;
  }

  public String getPabellon() {
    return pabellon;
  }

  public void setPabellon(String pabellon) {
    this.pabellon = pabellon;
  }

  public int getNroEstudiantes() {
    return nroEstudiantes;
  }

  public void setNroEstudiantes(int nroEstudiantes) {
    this.nroEstudiantes = nroEstudiantes;
  }

  public String getDelegado() {
    return delegado;
  }

  public void setDelegado(String delegado) {
    this.delegado = delegado;
  }

  @XmlTransient
  public List<Tutoria> getTutoriaList() {
    return tutoriaList;
  }

  public void setTutoriaList(List<Tutoria> tutoriaList) {
    this.tutoriaList = tutoriaList;
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
