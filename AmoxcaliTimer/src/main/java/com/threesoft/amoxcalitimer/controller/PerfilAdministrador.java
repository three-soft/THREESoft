/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcalitimer.dao.AdministradorDao;
import com.threesoft.amoxcalitimer.models.Administrador;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author damri
 */
@ManagedBean
@ViewScoped

public class PerfilAdministrador implements Serializable{
        private String nombreCompleto;
    private String password;
    private String confirmPassword;
    private String correo;
    private String noTrabajador;
    private Administrador administrador = (Administrador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Administrador");
    private boolean cambioPass;
    private boolean passConfirmada;
    private String newPassword;

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNoTrabajador(String noTrabajador) {
        this.noTrabajador = noTrabajador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setCambioPass(boolean cambioPass) {
        this.cambioPass = cambioPass;
    }

    public void setPassConfirmada(boolean passConfirmada) {
        this.passConfirmada = passConfirmada;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNoTrabajador() {
        return noTrabajador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public boolean isCambioPass() {
        return cambioPass;
    }

    public boolean isPassConfirmada() {
        return passConfirmada;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    public void cargarInfo() throws Exception {
        this.nombreCompleto = administrador.getNombreCompleto();
        this.correo = administrador.getCorreoAdmin();
        this.noTrabajador = administrador.getNoTrabajador();
        this.password = administrador.getPassword();
    }

    public void validatepOldPassword(FacesContext context, UIComponent component, Object value) {
        if (this.administrador.getPassword().equals(value.toString())) {
            this.passConfirmada = true;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Correcto.",
                    "Puedes actulizar la contraseña.");
            throw new ValidatorException(msg);
        }
    }

    public void cambiarInfo() throws Exception {
        if (this.passConfirmada) {
            if (this.newPassword != null) {
                administrador.setPassword(this.newPassword);
            }
            administrador.setCorreoAdmin(this.correo);
            AdministradorDao administradorDao = new AdministradorDao();
            administradorDao.getEntityManager().getTransaction().begin();
            administradorDao.update(administrador);
            administradorDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha actualizado tu información correctamente.",
                            "Se ha actualizado tu información correctamente.."));
        }
        this.cambioPass = false;
        this.passConfirmada = false;

    }

    public PerfilAdministrador(String nombreCompleto, String userName, String password, String correo, String noTrabajador, String departamento, String tipoProfesor) {
        this.administrador = (Administrador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador");
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.correo = correo;
        this.noTrabajador = noTrabajador;
    }

    public PerfilAdministrador() {
        this.nombreCompleto = administrador.getNombreCompleto();
        this.password = administrador.getPassword();
        this.correo = administrador.getCorreoAdmin();
        this.noTrabajador = administrador.getNoTrabajador();
        this.cambioPass = false;
        this.passConfirmada = false;
    }

    public void validarNuevoCorreoAdmin(FacesContext context, UIComponent component, Object value) {
        if (!value.toString().equals(this.correo)) {
            System.out.println("Los valores son diferentes" + value.toString() + "-" + this.administrador.getCorreoAdmin());
            AdministradorDao administradorDao = new AdministradorDao();
            if (administradorDao.mailExist((String) value)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El correo que intenta dar ya está registrado, escriba otro.",
                        "El correo que intenta dar ya está registrado, escriba otro.");
                throw new ValidatorException(msg);
            }
        }
    }
    

}
