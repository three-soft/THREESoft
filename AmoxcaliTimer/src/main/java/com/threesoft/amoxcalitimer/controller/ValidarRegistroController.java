/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcalitimer.Correo;
import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.models.Academico;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author damianri
 */
@ManagedBean
@ViewScoped
public class ValidarRegistroController implements Serializable {

    private List<Academico> academicos;
    private Academico academico;
    private boolean aceptar;

    public ValidarRegistroController() {
        this.academicos = new ArrayList<>();
        this.aceptar = false;
    }

    public List<Academico> getAcademicos() {
        AcademicoDao listaAcademicos;
        listaAcademicos = new AcademicoDao();
        List<Academico> todos = listaAcademicos.getAll();
        List<Academico> academicosPorValidar = new ArrayList<>();
        for (Academico aca : todos) {
            if (aca.getFechaActivacion() == null) {
                academicosPorValidar.add(aca);
            }
        }
        return academicosPorValidar;
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }
    
    public void validarRegistro(ActionEvent event) throws Exception {
        System.out.println("Revisando registro");
        Academico academico = (Academico) event.getComponent().getAttributes().get("academico");
        academico.setFechaActivacion(new Date());
        AcademicoDao academicoDao = new AcademicoDao();
        academicoDao.getEntityManager().getTransaction().begin();
        academicoDao.update(academico);
        academicoDao.getEntityManager().getTransaction().commit();
        Correo.correoDeActivacion(academico.getCorreoAca(), academico.getNombreCompleto());
        System.out.println("Academico:" + academico.getNombreCompleto() + " FechaAct: " + academico.getFechaActivacion().toString());
        FacesContext.getCurrentInstance().addMessage("mensaje-aviso",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro aceptado",
                        "Se le hará envío de un correo de confirmación al académico " + academico.getNombreCompleto()));
    }

    public void denegarRegistro() throws Exception {
        System.out.println("Revisando registro");
        System.out.println("Academico:" + academico.getNombreCompleto());
        AcademicoDao academicoDao = new AcademicoDao();
        academicoDao.getEntityManager().getTransaction().begin();
        academicoDao.delete(academico);
        academicoDao.getEntityManager().getTransaction().commit();
        Correo.correoDeRechazo(academico.getCorreoAca(), academico.getNombreCompleto());
        FacesContext.getCurrentInstance().addMessage("mensaje-aviso",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registro denegado",
                        "Se le hará envío de un correo de aviso al académico " + academico.getNombreCompleto()));
    }

    public void mensajeAviso(CloseEvent event) {
        if (this.aceptar) {
            this.aceptar = false;
            FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(),
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro denegado",
                            "Se le hará envío de un correo de aviso al académico " + academico.getNombreCompleto()));
        }
    }

}
