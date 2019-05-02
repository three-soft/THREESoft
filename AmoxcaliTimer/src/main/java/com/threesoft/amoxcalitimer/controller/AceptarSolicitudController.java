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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author damianri
 */
@ManagedBean
@ViewScoped
public class AceptarSolicitudController implements Serializable {

    private List<Solicitud> solicitudes;
    private List<Solicitud> solicitudesAprobadas;
    private List<Solicitud> historial;

    public AceptarSolicitudController() {
        this.solicitudes = new ArrayList<>();
    }

    public List<Solicitud> getSolicitudes() {
        SolicitudDao solicitudDao = new SolicitudDao();
        List<Solicitud> todos = solicitudDao.getSolicitudes();
        List<Solicitud> solictudesPorValidar = new ArrayList<>();
        for (Solicitud solicitud : todos) {
            if (solicitud.getFechaResolucion() == null && !solicitud.getEstatus()) {
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
            if (solicitud.getEstatus()) {
                solictudesAprobadas.add(solicitud);
            }
        }
        return solictudesAprobadas;
    }

    public List<Solicitud> getHistorial() {
        SolicitudDao solicitudDao = new SolicitudDao();
        List<Solicitud> historial = solicitudDao.getSolicitudes();
        return historial;
    }

    public void setHistorial(List<Solicitud> historial) {
        this.historial = historial;
    }
    
    public void setSolicitudesAprobadas(List<Solicitud> solicitudesAprobadas) {
        this.solicitudesAprobadas = solicitudesAprobadas;
    }

    public void validarSolicitud(ActionEvent event) throws Exception {
        System.out.println("Solicitud");
        //Actualizamos la fecha y el estatus de la solicitud
        Solicitud solicitud = (Solicitud) event.getComponent().getAttributes().get("solicitud");
        solicitud.setFechaResolucion(new Date());
        solicitud.setEstatus(Boolean.TRUE);
        //Obtenemos el academico a quien enviaremos el correo de aceptación.
        AcademicoDao academicoDao = new AcademicoDao();
        academicoDao.getEntityManager().getTransaction().begin();
        Academico academico = academicoDao.getByKey(solicitud.getSolicitudPK().getIdAcademico());
        academicoDao.getEntityManager().getTransaction().commit();
        //Actualizamos el valor del registro en la base de datos.
        SolicitudDao solicitudDao = new SolicitudDao();
        solicitudDao.getEntityManager().getTransaction().begin();
        solicitudDao.update(solicitud);
        solicitudDao.getEntityManager().getTransaction().commit();
        //enviamos el correo para notificar al usuario.
        Correo.correoEventoAceptado(academico);
        System.out.println("Solicitud:" + solicitud.getNombreEvento() + " FechaAct: " + solicitud.getFechaResolucion());
        FacesContext.getCurrentInstance().addMessage("mensaje-aviso",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Solicitud Aceptada",
                        "Se le hará envío de un correo de confirmación de su evento al académico " + solicitud.getAcademico().getNombreCompleto()));
    }
    /*
     */

}
