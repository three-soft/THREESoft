/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author damianri
 */
@Embeddable
public class SolicitudPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_solicitud")
    private long idSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_academico")
    private long idAcademico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_espacio")
    private long idEspacio;

    public SolicitudPK() {
    }

    public SolicitudPK(long idSolicitud, long idAcademico, long idEspacio) {
        this.idSolicitud = idSolicitud;
        this.idAcademico = idAcademico;
        this.idEspacio = idEspacio;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public long getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(long idAcademico) {
        this.idAcademico = idAcademico;
    }

    public long getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(long idEspacio) {
        this.idEspacio = idEspacio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSolicitud;
        hash += (int) idAcademico;
        hash += (int) idEspacio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudPK)) {
            return false;
        }
        SolicitudPK other = (SolicitudPK) object;
        if (this.idSolicitud != other.idSolicitud) {
            return false;
        }
        if (this.idAcademico != other.idAcademico) {
            return false;
        }
        if (this.idEspacio != other.idEspacio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.threesoft.amoxcalitimer.models.SolicitudPK[ idSolicitud=" + idSolicitud + ", idAcademico=" + idAcademico + ", idEspacio=" + idEspacio + " ]";
    }
    
}
