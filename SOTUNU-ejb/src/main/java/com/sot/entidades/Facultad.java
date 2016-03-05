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
@Table(name = "facultad")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
  @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad"),
  @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre")})
public class Facultad implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idFacultad")
  private Integer idFacultad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultad")
  private List<Escuelaprofesional> escuelaprofesionalList;

  public Facultad() {
  }

  public Facultad(Integer idFacultad) {
    this.idFacultad = idFacultad;
  }

  public Facultad(Integer idFacultad, String nombre) {
    this.idFacultad = idFacultad;
    this.nombre = nombre;
  }

  public Integer getIdFacultad() {
    return idFacultad;
  }

  public void setIdFacultad(Integer idFacultad) {
    this.idFacultad = idFacultad;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @XmlTransient
  public List<Escuelaprofesional> getEscuelaprofesionalList() {
    return escuelaprofesionalList;
  }

  public void setEscuelaprofesionalList(List<Escuelaprofesional> escuelaprofesionalList) {
    this.escuelaprofesionalList = escuelaprofesionalList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idFacultad != null ? idFacultad.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Facultad)) {
      return false;
    }
    Facultad other = (Facultad) object;
    if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Facultad[ idFacultad=" + idFacultad + " ]";
  }
  
}
