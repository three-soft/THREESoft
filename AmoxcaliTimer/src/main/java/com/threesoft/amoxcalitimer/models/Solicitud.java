/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author damianri
 */
@Entity
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByIdAcademico", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idAcademico = :idAcademico")
    , @NamedQuery(name = "Solicitud.findByIdEspacio", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idEspacio = :idEspacio")
    , @NamedQuery(name = "Solicitud.findByFechaSolicitud", query = "SELECT s FROM Solicitud s WHERE s.fechaSolicitud = :fechaSolicitud")
    , @NamedQuery(name = "Solicitud.findByHoraInicio", query = "SELECT s FROM Solicitud s WHERE s.horaInicio = :horaInicio")
    , @NamedQuery(name = "Solicitud.findByHoraFin", query = "SELECT s FROM Solicitud s WHERE s.horaFin = :horaFin")
    , @NamedQuery(name = "Solicitud.findByFechaAprobacion", query = "SELECT s FROM Solicitud s WHERE s.fechaAprobacion = :fechaAprobacion")
    , @NamedQuery(name = "Solicitud.findByNombre", query = "SELECT s FROM Solicitud s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Solicitud.findByDescripcion", query = "SELECT s FROM Solicitud s WHERE s.descripcion = :descripcion")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudPK solicitudPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.DATE)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin")
    @Temporal(TemporalType.DATE)
    private Date horaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_academico", referencedColumnName = "id_academico", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Academico academico;
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Espacio espacio;

    public Solicitud() {
    }

    public Solicitud(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public Solicitud(SolicitudPK solicitudPK, Date fechaSolicitud, Date horaInicio, Date horaFin, Date fechaAprobacion, String nombre, String descripcion) {
        this.solicitudPK = solicitudPK;
        this.fechaSolicitud = fechaSolicitud;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaAprobacion = fechaAprobacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Solicitud(long idSolicitud, long idAcademico, long idEspacio) {
        this.solicitudPK = new SolicitudPK(idSolicitud, idAcademico, idEspacio);
    }

    public SolicitudPK getSolicitudPK() {
        return solicitudPK;
    }

    public void setSolicitudPK(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudPK != null ? solicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solicitudPK == null && other.solicitudPK != null) || (this.solicitudPK != null && !this.solicitudPK.equals(other.solicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.threesoft.amoxcalitimer.models.Solicitud[ solicitudPK=" + solicitudPK + " ]";
    }
    
}
