/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcalitimer.Correo;
import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.dao.SolicitudDao;
import com.threesoft.amoxcalitimer.models.Academico;
import com.threesoft.amoxcalitimer.models.Solicitud;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author damianri
 */
@ManagedBean
@SessionScoped
public class AceptarSolicitudController implements Serializable {

    private List<Solicitud> solicitudes;
    private List<Solicitud> solicitudesAprobadas;

    public AceptarSolicitudController() {
        this.solicitudes = new ArrayList<>();
    }

    public List<Solicitud> getSolicitudes() {
        SolicitudDao solicitudDao = new SolicitudDao();
        List<Solicitud> todos = solicitudDao.getSolicitudes();
        List<Solicitud> solictudesPorValidar = new ArrayList<>();
        for (Solicitud solicitud : todos) {
            if (solicitud.getFechaAprobacion() == null) {
                solictudesPorValidar.add(solicitud);
            }
        }
        return solictudesPorValidar;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Solicitud> getSolicitudesAprobadas() {
        SolicitudDao solicitudDao = new SolicitudDao();
        List<Solicitud> todos = solicitudDao.getSolicitudes();
        List<Solicitud> solictudesAprobadas = new ArrayList<>();
        for (Solicitud solicitud : todos) {
            if (solicitud.getFechaAprobacion() != null) {
                solictudesAprobadas.add(solicitud);
            }
        }
        return solictudesAprobadas;
    }

    public void setSolicitudesAprobadas(List<Solicitud> solicitudesAprobadas) {
        this.solicitudesAprobadas = solicitudesAprobadas;
    }

    public void validarSolicitud(ActionEvent event) throws Exception {
        System.out.println("Solicitud");
        Solicitud solicitud = (Solicitud) event.getComponent().getAttributes().get("solicitud");
        solicitud.setFechaAprobacion(new Date());
        SolicitudDao solicitudDao = new SolicitudDao();
        solicitudDao.getEntityManager().getTransaction().begin();
        solicitudDao.update(solicitud);
        solicitudDao.getEntityManager().getTransaction().commit();
        /*
        
        Correo.correoDeActivacion(solicitud.getCorreoAca(), solicitud.getNombreCompleto());
        */
        System.out.println("Solicitud:" + solicitud.getNombre() + " FechaAct: " + solicitud.getFechaAprobacion());
        FacesContext.getCurrentInstance().addMessage("mensaje-aviso",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Solicitud Aceptada",
                        "Se le hará envío de un correo de confirmación del evento al académico " + solicitud.getAcademico().getNombreCompleto()));
    }
    /*
     */

}
