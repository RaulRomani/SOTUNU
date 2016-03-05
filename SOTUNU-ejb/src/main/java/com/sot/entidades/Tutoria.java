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
@Table(name = "tutoria")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Tutoria.findAll", query = "SELECT t FROM Tutoria t"),
  @NamedQuery(name = "Tutoria.findByIdTutoria", query = "SELECT t FROM Tutoria t WHERE t.idTutoria = :idTutoria"),
  @NamedQuery(name = "Tutoria.findByFecha", query = "SELECT t FROM Tutoria t WHERE t.fecha = :fecha"),
  @NamedQuery(name = "Tutoria.findByHoraInicio", query = "SELECT t FROM Tutoria t WHERE t.horaInicio = :horaInicio"),
  @NamedQuery(name = "Tutoria.findByHoraFin", query = "SELECT t FROM Tutoria t WHERE t.horaFin = :horaFin"),
  @NamedQuery(name = "Tutoria.findByTema", query = "SELECT t FROM Tutoria t WHERE t.tema = :tema"),
  @NamedQuery(name = "Tutoria.findByAtencion", query = "SELECT t FROM Tutoria t WHERE t.atencion = :atencion"),
  @NamedQuery(name = "Tutoria.findByRefDocente", query = "SELECT t FROM Tutoria t WHERE t.refDocente = :refDocente"),
  @NamedQuery(name = "Tutoria.findByRefAsignatura", query = "SELECT t FROM Tutoria t WHERE t.refAsignatura = :refAsignatura"),
  @NamedQuery(name = "Tutoria.findByRefDato", query = "SELECT t FROM Tutoria t WHERE t.refDato = :refDato"),
  @NamedQuery(name = "Tutoria.findByNotas", query = "SELECT t FROM Tutoria t WHERE t.notas = :notas"),
  @NamedQuery(name = "Tutoria.findByAsistencia", query = "SELECT t FROM Tutoria t WHERE t.asistencia = :asistencia"),
  @NamedQuery(name = "Tutoria.findByObservaciones", query = "SELECT t FROM Tutoria t WHERE t.observaciones = :observaciones")})
public class Tutoria implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idTutoria")
  private Integer idTutoria;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha")
  @Temporal(TemporalType.DATE)
  private Date fecha;
  @Basic(optional = false)
  @NotNull
  @Column(name = "horaInicio")
  @Temporal(TemporalType.TIME)
  private Date horaInicio;
  @Basic(optional = false)
  @NotNull
  @Column(name = "horaFin")
  @Temporal(TemporalType.TIME)
  private Date horaFin;
  @Size(max = 50)
  @Column(name = "tema")
  private String tema;
  @Size(max = 10)
  @Column(name = "atencion")
  private String atencion;
  @Size(max = 80)
  @Column(name = "refDocente")
  private String refDocente;
  @Size(max = 30)
  @Column(name = "refAsignatura")
  private String refAsignatura;
  @Size(max = 30)
  @Column(name = "refDato")
  private String refDato;
  @Size(max = 250)
  @Column(name = "notas")
  private String notas;
  @Size(max = 250)
  @Column(name = "asistencia")
  private String asistencia;
  @Size(max = 250)
  @Column(name = "observaciones")
  private String observaciones;
  @JoinColumn(name = "idProgramacion", referencedColumnName = "idProgramacion")
  @ManyToOne(optional = false)
  private Programacion idProgramacion;
  @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
  @ManyToOne(optional = false)
  private Usuario idUsuario;

  public Tutoria() {
  }

  public Tutoria(Integer idTutoria) {
    this.idTutoria = idTutoria;
  }

  public Tutoria(Integer idTutoria, Date fecha, Date horaInicio, Date horaFin) {
    this.idTutoria = idTutoria;
    this.fecha = fecha;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
  }

  public Integer getIdTutoria() {
    return idTutoria;
  }

  public void setIdTutoria(Integer idTutoria) {
    this.idTutoria = idTutoria;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(Date horaInicio) {
    this.horaInicio = horaInicio;
  }

  public Date getHoraFin() {
    return horaFin;
  }

  public void setHoraFin(Date horaFin) {
    this.horaFin = horaFin;
  }

  public String getTema() {
    return tema;
  }

  public void setTema(String tema) {
    this.tema = tema;
  }

  public String getAtencion() {
    return atencion;
  }

  public void setAtencion(String atencion) {
    this.atencion = atencion;
  }

  public String getRefDocente() {
    return refDocente;
  }

  public void setRefDocente(String refDocente) {
    this.refDocente = refDocente;
  }

  public String getRefAsignatura() {
    return refAsignatura;
  }

  public void setRefAsignatura(String refAsignatura) {
    this.refAsignatura = refAsignatura;
  }

  public String getRefDato() {
    return refDato;
  }

  public void setRefDato(String refDato) {
    this.refDato = refDato;
  }

  public String getNotas() {
    return notas;
  }

  public void setNotas(String notas) {
    this.notas = notas;
  }

  public String getAsistencia() {
    return asistencia;
  }

  public void setAsistencia(String asistencia) {
    this.asistencia = asistencia;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public Programacion getIdProgramacion() {
    return idProgramacion;
  }

  public void setIdProgramacion(Programacion idProgramacion) {
    this.idProgramacion = idProgramacion;
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
    hash += (idTutoria != null ? idTutoria.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Tutoria)) {
      return false;
    }
    Tutoria other = (Tutoria) object;
    if ((this.idTutoria == null && other.idTutoria != null) || (this.idTutoria != null && !this.idTutoria.equals(other.idTutoria))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.sot.entidades.Tutoria[ idTutoria=" + idTutoria + " ]";
  }
  
}
