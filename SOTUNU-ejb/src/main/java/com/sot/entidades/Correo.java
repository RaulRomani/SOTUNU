/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "correo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Correo.findAll", query = "SELECT c FROM Correo c"),
  @NamedQuery(name = "Correo.findByIdCorreo", query = "SELECT c FROM Correo c WHERE c.idCorreo = :idCorreo"),
  @NamedQuery(name = "Correo.findByEmail", query = "SELECT c FROM Correo c WHERE c.email = :email"),
  @NamedQuery(name = "Correo.findByNombre", query = "SELECT c FROM Correo c WHERE c.nombre = :nombre"),
  @NamedQuery(name = "Correo.findByApellido", query = "SELECT c FROM Correo c WHERE c.apellido = :apellido")})
public class Correo implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idCorreo")
  private Integer idCorreo;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "email")
  private String email;
  @Size(max = 40)
  @Column(name = "nombre")
  private String nombre;
  @Size(max = 40)
  @Column(name = "apellido")
  private String apellido;
  @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario idUsuario;

  public Correo() {
  }

  public Correo(Integer idCorreo) {
    this.idCorreo = idCorreo;
  }

  public Correo(Integer idCorreo, String email) {
    this.idCorreo = idCorreo;
    this.email = email;
  }

  public Integer getIdCorreo() {
    return idCorreo;
  }

  public void setIdCorreo(Integer idCorreo) {
    this.idCorreo = idCorreo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Usuario getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Usuario idUsuario) {
    this.idUsuario = idUsuario;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idCorreo != null ? idCorreo.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Correo)) {
      return false;
    }
    Correo other = (Correo) object;
    if ((this.idCorreo == null && other.idCorreo != null) || (this.idCorreo != null && !this.idCorreo.equals(other.idCorreo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Correo[ idCorreo=" + idCorreo + " ]";
  }
  
}
