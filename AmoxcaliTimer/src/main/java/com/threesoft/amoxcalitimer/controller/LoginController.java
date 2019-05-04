/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

/**
 *
 * @author dams_
 */
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.dao.AdministradorDao;
import com.threesoft.amoxcalitimer.models.Academico;
import com.threesoft.amoxcalitimer.models.Administrador;

/**
 * Controlador que implmenta las acciones relacionadas con el inicio de sesión
 * del Academico.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

    /**
     * Atributo donde almacenaremos el nombre o correo del Academico.
     */
    private String userName;
    /**
     * Atributo donde almacenamos la contrqaseña sin encriptar.
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Contructor del controlador.
     */
    public LoginController() {
    }

    /**
     * Método que inicia sesión a un Academico.
     *
     */
    public void loginUser() {
        try {
            AcademicoDao academicoDao = new AcademicoDao();
            Academico aca = academicoDao.searchByUserNameOrEmail(userName);
            if (aca != null) {
                if (!aca.getPassword().equals(password)) {
                    FacesContext.getCurrentInstance().addMessage("messages",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña incorrectos.", ""));
                } else {
                    if (aca.getFechaActivacion() != null) {//cambiar línea de comparación por "!="
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.getExternalContext().getSessionMap().put("Academico", aca);
                        ExternalContext eContext = context.getExternalContext();
                        eContext.redirect(eContext.getRequestContextPath() + "/solicitar_espacio.xhtml");
                    }
                    FacesContext.getCurrentInstance().addMessage("messages",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Tu cuenta aun no ha sido verificada, intenta en otro momento", ""));
                }
            } else {
                AdministradorDao administradorDao = new AdministradorDao();
                Administrador admin = administradorDao.searchByUserNameOrEmail(userName);
                if (admin == null || !admin.getPassword().equals(password)) {
                    FacesContext.getCurrentInstance().addMessage("messages",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña incorrectos.", ""));

                } else if (admin != null || admin.getPassword().equals(password)) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getSessionMap().put("Administrador", admin);
                    ExternalContext eContext = context.getExternalContext();
                    eContext.redirect(eContext.getRequestContextPath() + "/historial.xhtml");
                }
            }
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Por el momento no se puede iniciar sesiòn en el sistema, intèntelo màs tarde.", ""));
        }
    }

    /**
     * Método que cierra la sesión de un Academico.
     */
    public void logoutUser() {
        try {
            System.out.println("Cerrando Sesión");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha cerrado la sesión con éxito, vuelva pronto.",
                            "Se ha cerrado la sesión con éxito, vuelva pronto."));
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath());
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde.",
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde."));
        }
    }

    public void clear() {
        this.password = null;
        this.userName = null;
    }

}
