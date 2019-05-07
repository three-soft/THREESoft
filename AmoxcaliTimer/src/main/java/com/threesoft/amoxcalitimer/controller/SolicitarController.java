/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

/**
 *
 * @author sergio.ravelo
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.models.Academico;
import com.threesoft.amoxcalitimer.dao.AdministradorDao;
import com.threesoft.amoxcalitimer.models.Administrador;
import com.threesoft.amoxcalitimer.dao.EspacioDao;
import com.threesoft.amoxcalitimer.models.Solicitud;
import com.threesoft.amoxcalitimer.dao.SolicitudDao;
import com.threesoft.amoxcalitimer.models.Espacio;
import com.threesoft.amoxcalitimer.models.SolicitudPK;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class SolicitarController implements Serializable {
    
    protected SolicitudPK solicitudPK;
    private Date horaInicio;
    private Date horaFin;
    private Date fechaSolicitud;
    private Date fechaResolucion;
    private String nombreEvento;
    private String descripcionEvento;
    private boolean estatus;
    private Academico academico;
    private Espacio espacio;
    private long idAcademico;
    private long idEspacio;
    private long idSolicitud;

    public SolicitarController() {
    this.solicitudPK = null;
    this.horaInicio = null;
    this.horaFin = null;
    this.fechaSolicitud = null;
    this.fechaResolucion = null;
    this.nombreEvento = null;
    this.descripcionEvento = null;
    this.estatus = false;
    this.academico = (Academico) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Academico");
    this.espacio = null;
    this.idSolicitud = 0;
    this.idAcademico = this.academico.getIdAcademico();
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

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
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

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
    public void registrarSolicitud() throws Exception {
        try {
            System.out.println("Registrando");
            SolicitudPK nuevaSolicitudPK = new SolicitudPK();
            nuevaSolicitudPK.setIdAcademico(idAcademico);
            nuevaSolicitudPK.setIdEspacio(idEspacio);
            nuevaSolicitudPK.setIdSolicitud(Long.MIN_VALUE);
            Solicitud nuevaSolicitud = new Solicitud();
            nuevaSolicitud.setSolicitudPK(nuevaSolicitudPK);
            nuevaSolicitud.setHoraInicio(horaInicio);
            nuevaSolicitud.setHoraFin(horaFin);
            nuevaSolicitud.setNombreEvento(nombreEvento);
            nuevaSolicitud.setDescripcionEvento(descripcionEvento);
            nuevaSolicitud.setFechaSolicitud(fechaSolicitud);
            nuevaSolicitud.setFechaResolucion(fechaResolucion);
            nuevaSolicitud.setEstatus(estatus);
            
            SolicitudDao solicitudDao = new SolicitudDao();
            solicitudDao.getEntityManager().getTransaction().begin();
            solicitudDao.save(nuevaSolicitud);
            solicitudDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Tu Solicitud se generó correctamente.",
                            "Tu Solicitud se generó correctamente."));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath() + "/solicitar_espacio.xhtml");
            
            
        
            
        
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde."));
        }
    }
    
    public void clear(){
        this.solicitudPK = null;
        this.horaInicio = null;
        this.horaFin = null;
        this.fechaSolicitud = null;
        this.fechaResolucion = null;
        this.nombreEvento = null;
        this.descripcionEvento = null;
        this.estatus = false;
        this.academico = null;
        this.espacio = null;
        this.idSolicitud = 0;
    }

    
    
    
    
    
}
