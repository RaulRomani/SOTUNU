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
@Table(name = "escuelaprofesional")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Escuelaprofesional.findAll", query = "SELECT e FROM Escuelaprofesional e"),
  @NamedQuery(name = "Escuelaprofesional.findByIdEscuelaProfesional", query = "SELECT e FROM Escuelaprofesional e WHERE e.idEscuelaProfesional = :idEscuelaProfesional"),
  @NamedQuery(name = "Escuelaprofesional.findByNombre", query = "SELECT e FROM Escuelaprofesional e WHERE e.nombre = :nombre"),
  @NamedQuery(name = "Escuelaprofesional.findByEspecialidad", query = "SELECT e FROM Escuelaprofesional e WHERE e.especialidad = :especialidad")})
public class Escuelaprofesional implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idEscuelaProfesional")
  private Integer idEscuelaProfesional;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "nombre")
  private String nombre;
  @Size(max = 50)
  @Column(name = "especialidad")
  private String especialidad;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEscuelaProfesional")
  private List<Tutorado> tutoradoList;
  @OneToMany(mappedBy = "idEscuelaProfesional")
  private List<Personal> personalList;
  @JoinColumn(name = "idFacultad", referencedColumnName = "idFacultad")
  @ManyToOne(optional = false)
  private Facultad idFacultad;

  public Escuelaprofesional() {
  }

  public Escuelaprofesional(Integer idEscuelaProfesional) {
    this.idEscuelaProfesional = idEscuelaProfesional;
  }

  public Escuelaprofesional(Integer idEscuelaProfesional, String nombre) {
    this.idEscuelaProfesional = idEscuelaProfesional;
    this.nombre = nombre;
  }

  public Integer getIdEscuelaProfesional() {
    return idEscuelaProfesional;
  }

  public void setIdEscuelaProfesional(Integer idEscuelaProfesional) {
    this.idEscuelaProfesional = idEscuelaProfesional;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  @XmlTransient
  public List<Tutorado> getTutoradoList() {
    return tutoradoList;
  }

  public void setTutoradoList(List<Tutorado> tutoradoList) {
    this.tutoradoList = tutoradoList;
  }

  @XmlTransient
  public List<Personal> getPersonalList() {
    return personalList;
  }

  public void setPersonalList(List<Personal> personalList) {
    this.personalList = personalList;
  }

  public Facultad getIdFacultad() {
    return idFacultad;
  }

  public void setIdFacultad(Facultad idFacultad) {
    this.idFacultad = idFacultad;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idEscuelaProfesional != null ? idEscuelaProfesional.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Escuelaprofesional)) {
      return false;
    }
    Escuelaprofesional other = (Escuelaprofesional) object;
    if ((this.idEscuelaProfesional == null && other.idEscuelaProfesional != null) || (this.idEscuelaProfesional != null && !this.idEscuelaProfesional.equals(other.idEscuelaProfesional))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Escuelaprofesional[ idEscuelaProfesional=" + idEscuelaProfesional + " ]";
  }
  
}
