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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author river
 */
@Entity
@Table(name = "solicitud", catalog = "postgres", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByIdAcademico", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idAcademico = :idAcademico")
    , @NamedQuery(name = "Solicitud.findByIdEspacio", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idEspacio = :idEspacio")
    , @NamedQuery(name = "Solicitud.findByHoraInicio", query = "SELECT s FROM Solicitud s WHERE s.horaInicio = :horaInicio")
    , @NamedQuery(name = "Solicitud.findByHoraFin", query = "SELECT s FROM Solicitud s WHERE s.horaFin = :horaFin")
    , @NamedQuery(name = "Solicitud.findByFechaSolicitud", query = "SELECT s FROM Solicitud s WHERE s.fechaSolicitud = :fechaSolicitud")
    , @NamedQuery(name = "Solicitud.findByFechaResolucion", query = "SELECT s FROM Solicitud s WHERE s.fechaResolucion = :fechaResolucion")
    , @NamedQuery(name = "Solicitud.findByNombreEvento", query = "SELECT s FROM Solicitud s WHERE s.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Solicitud.findByDescripcionEvento", query = "SELECT s FROM Solicitud s WHERE s.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "Solicitud.findByEstatus", query = "SELECT s FROM Solicitud s WHERE s.estatus = :estatus")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudPK solicitudPK;
    @Basic(optional = false)
    @Column(name = "hora_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "hora_fin", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Basic(optional = false)
    @Column(name = "fecha_solicitud", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaResolucion;
    @Basic(optional = false)
    @Column(name = "nombre_evento", nullable = false, length = 250)
    private String nombreEvento;
    @Basic(optional = false)
    @Column(name = "descripcion_evento", nullable = false, length = 500)
    private String descripcionEvento;
    @Column(name = "estatus")
    private Boolean estatus;
    @JoinColumn(name = "id_academico", referencedColumnName = "id_academico", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Academico academico;
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Espacio espacio;

    public Solicitud() {
    }

    public Solicitud(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public Solicitud(SolicitudPK solicitudPK, Date horaInicio, Date horaFin, Date fechaSolicitud, String nombreEvento, String descripcionEvento) {
        this.solicitudPK = solicitudPK;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaSolicitud = fechaSolicitud;
        this.nombreEvento = nombreEvento;
        this.descripcionEvento = descripcionEvento;
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
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
