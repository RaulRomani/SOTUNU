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
@Table(name = "cicloacademico")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Cicloacademico.findAll", query = "SELECT c FROM Cicloacademico c"),
  @NamedQuery(name = "Cicloacademico.findByIdCicloAcademico", query = "SELECT c FROM Cicloacademico c WHERE c.idCicloAcademico = :idCicloAcademico"),
  @NamedQuery(name = "Cicloacademico.findByA\u00f1o", query = "SELECT c FROM Cicloacademico c WHERE c.a\u00f1o = :a\u00f1o"),
  @NamedQuery(name = "Cicloacademico.findByPeriodo", query = "SELECT c FROM Cicloacademico c WHERE c.periodo = :periodo")})
public class Cicloacademico implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idCicloAcademico")
  private Integer idCicloAcademico;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 4)
  @Column(name = "a\u00f1o")
  private String año;
  @Basic(optional = false)
  @NotNull
  @Column(name = "periodo")
  private Character periodo;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCicloAcademico")
  private List<Tutorado> tutoradoList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCicloAcademico")
  private List<Programacion> programacionList;

  public Cicloacademico() {
  }

  public Cicloacademico(Integer idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public Cicloacademico(Integer idCicloAcademico, String año, Character periodo) {
    this.idCicloAcademico = idCicloAcademico;
    this.año = año;
    this.periodo = periodo;
  }

  public Integer getIdCicloAcademico() {
    return idCicloAcademico;
  }

  public void setIdCicloAcademico(Integer idCicloAcademico) {
    this.idCicloAcademico = idCicloAcademico;
  }

  public String getAño() {
    return año;
  }

  public void setAño(String año) {
    this.año = año;
  }

  public Character getPeriodo() {
    return periodo;
  }

  public void setPeriodo(Character periodo) {
    this.periodo = periodo;
  }

  @XmlTransient
  public List<Tutorado> getTutoradoList() {
    return tutoradoList;
  }

  public void setTutoradoList(List<Tutorado> tutoradoList) {
    this.tutoradoList = tutoradoList;
  }

  @XmlTransient
  public List<Programacion> getProgramacionList() {
    return programacionList;
  }

  public void setProgramacionList(List<Programacion> programacionList) {
    this.programacionList = programacionList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idCicloAcademico != null ? idCicloAcademico.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Cicloacademico)) {
      return false;
    }
    Cicloacademico other = (Cicloacademico) object;
    if ((this.idCicloAcademico == null && other.idCicloAcademico != null) || (this.idCicloAcademico != null && !this.idCicloAcademico.equals(other.idCicloAcademico))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Cicloacademico[ idCicloAcademico=" + idCicloAcademico + " ]";
  }
  
}
