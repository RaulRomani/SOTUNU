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
@Table(name = "programaciontutor")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Programaciontutor.findAll", query = "SELECT p FROM Programaciontutor p"),
  @NamedQuery(name = "Programaciontutor.findByIdProgramacionTutor", query = "SELECT p FROM Programaciontutor p WHERE p.idProgramacionTutor = :idProgramacionTutor"),
  @NamedQuery(name = "Programaciontutor.findByCiclo", query = "SELECT p FROM Programaciontutor p WHERE p.ciclo = :ciclo"),
  @NamedQuery(name = "Programaciontutor.findByAula", query = "SELECT p FROM Programaciontutor p WHERE p.aula = :aula"),
  @NamedQuery(name = "Programaciontutor.findByPabellon", query = "SELECT p FROM Programaciontutor p WHERE p.pabellon = :pabellon"),
  @NamedQuery(name = "Programaciontutor.findByNroEstudiantes", query = "SELECT p FROM Programaciontutor p WHERE p.nroEstudiantes = :nroEstudiantes"),
  @NamedQuery(name = "Programaciontutor.findByDelegado", query = "SELECT p FROM Programaciontutor p WHERE p.delegado = :delegado"),
  @NamedQuery(name = "Programaciontutor.findByIdProgramacion", query = "SELECT p FROM Programaciontutor p WHERE p.idProgramacion = :idProgramacion")})
public class Programaciontutor implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idProgramacionTutor")
  private Integer idProgramacionTutor;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "ciclo")
  private String ciclo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 3)
  @Column(name = "aula")
  private String aula;
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramacionTutor")
  private List<Tutoria> tutoriaList;
  @JoinColumn(name = "idProgramacion", referencedColumnName = "idProgramacion")
  @ManyToOne(optional = false)
  private Programacion idProgramacion;
  @JoinColumn(name = "idPersonal", referencedColumnName = "idPersonal")
  @ManyToOne(optional = false)
  private Personal idPersonal;

  public Programaciontutor() {
  }

  public Programaciontutor(Integer idProgramacionTutor) {
    this.idProgramacionTutor = idProgramacionTutor;
  }

  public Programaciontutor(Integer idProgramacionTutor, String ciclo, String aula, String pabellon, int nroEstudiantes, String delegado) {
    this.idProgramacionTutor = idProgramacionTutor;
    this.ciclo = ciclo;
    this.aula = aula;
    this.pabellon = pabellon;
    this.nroEstudiantes = nroEstudiantes;
    this.delegado = delegado;
  }

  public Integer getIdProgramacionTutor() {
    return idProgramacionTutor;
  }

  public void setIdProgramacionTutor(Integer idProgramacionTutor) {
    this.idProgramacionTutor = idProgramacionTutor;
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  public String getAula() {
    return aula;
  }

  public void setAula(String aula) {
    this.aula = aula;
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

  public Programacion getIdProgramacion() {
    return idProgramacion;
  }

  public void setIdProgramacion(Programacion idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  public Personal getIdPersonal() {
    return idPersonal;
  }

  public void setIdPersonal(Personal idPersonal) {
    this.idPersonal = idPersonal;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idProgramacionTutor != null ? idProgramacionTutor.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Programaciontutor)) {
      return false;
    }
    Programaciontutor other = (Programaciontutor) object;
    if ((this.idProgramacionTutor == null && other.idProgramacionTutor != null) || (this.idProgramacionTutor != null && !this.idProgramacionTutor.equals(other.idProgramacionTutor))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Programaciontutor[ idProgramacionTutor=" + idProgramacionTutor + " ]";
  }
  
}
