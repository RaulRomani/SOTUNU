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
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
  @NamedQuery(name = "Personal.findByIdPersonal", query = "SELECT p FROM Personal p WHERE p.idPersonal = :idPersonal"),
  @NamedQuery(name = "Personal.findByNombre", query = "SELECT p FROM Personal p WHERE p.nombre = :nombre"),
  @NamedQuery(name = "Personal.findByApellido", query = "SELECT p FROM Personal p WHERE p.apellido = :apellido"),
  @NamedQuery(name = "Personal.findByDireccion", query = "SELECT p FROM Personal p WHERE p.direccion = :direccion"),
  @NamedQuery(name = "Personal.findByEmail", query = "SELECT p FROM Personal p WHERE p.email = :email"),
  @NamedQuery(name = "Personal.findByTelefono", query = "SELECT p FROM Personal p WHERE p.telefono = :telefono"),
  @NamedQuery(name = "Personal.findByCelular", query = "SELECT p FROM Personal p WHERE p.celular = :celular"),
  @NamedQuery(name = "Personal.findByCargo", query = "SELECT p FROM Personal p WHERE p.cargo = :cargo"),
  @NamedQuery(name = "Personal.findByEscuelaProfesional", query = "SELECT p FROM Personal p WHERE p.idEscuelaProfesional = :idEscuelaProfesional AND p.cargo = :cargo")})
public class Personal implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idPersonal")
  private Integer idPersonal;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "apellido")
  private String apellido;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "direccion")
  private String direccion;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 50)
  @Column(name = "email")
  private String email;
  @Size(max = 20)
  @Column(name = "telefono")
  private String telefono;
  @Size(max = 20)
  @Column(name = "celular")
  private String celular;
  @Size(max = 13)
  @Column(name = "cargo")
  private String cargo;
  @JoinColumn(name = "idEscuelaProfesional", referencedColumnName = "idEscuelaProfesional")
  @ManyToOne
  private Escuelaprofesional idEscuelaProfesional;
  @OneToMany(mappedBy = "idPersonal")
  private List<Usuario> usuarioList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonal")
  private List<Programaciontutor> programaciontutorList;

  public Personal() {
  }

  public Personal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public Personal(Integer idPersonal, String nombre, String apellido, String direccion) {
    this.idPersonal = idPersonal;
    this.nombre = nombre;
    this.apellido = apellido;
    this.direccion = direccion;
  }

  public Integer getIdPersonal() {
    return idPersonal;
  }

  public void setIdPersonal(Integer idPersonal) {
    this.idPersonal = idPersonal;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public Escuelaprofesional getIdEscuelaProfesional() {
    return idEscuelaProfesional;
  }

  public void setIdEscuelaProfesional(Escuelaprofesional idEscuelaProfesional) {
    this.idEscuelaProfesional = idEscuelaProfesional;
  }

  @XmlTransient
  public List<Usuario> getUsuarioList() {
    return usuarioList;
  }

  public void setUsuarioList(List<Usuario> usuarioList) {
    this.usuarioList = usuarioList;
  }

  @XmlTransient
  public List<Programaciontutor> getProgramaciontutorList() {
    return programaciontutorList;
  }

  public void setProgramaciontutorList(List<Programaciontutor> programaciontutorList) {
    this.programaciontutorList = programaciontutorList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idPersonal != null ? idPersonal.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Personal)) {
      return false;
    }
    Personal other = (Personal) object;
    if ((this.idPersonal == null && other.idPersonal != null) || (this.idPersonal != null && !this.idPersonal.equals(other.idPersonal))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Personal[ idPersonal=" + idPersonal + " ]";
  }
  
}
