/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.models.Academico;
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
public class ModificarPropiedadesController implements Serializable {

    private String nombreCompleto;
    private String userName;
    private String password;
    private String confirmPassword;
    private String correo;
    private String noTrabajador;
    private String departamento;
    private String tipoProfesor;
    private Academico academico = (Academico) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Academico");
    private boolean cambioPass;
    private boolean passConfirmada;
    private String newPassword;

    public boolean isPassConfirmada() {
        return passConfirmada;
    }

    public void setPassConfirmada(boolean passConfirmada) {
        this.passConfirmada = passConfirmada;
    }
    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Academico getAcademico() {
        return academico;
    }

    public boolean isCambioPass() {
        return cambioPass;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public void setCambioPass(boolean cambioPass) {
        System.out.print("Cambiando desicion de contraseña");
        this.cambioPass = cambioPass;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setTipoProfesor(String tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getUserName() {
        return userName;
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

    public String getDepartamento() {
        return departamento;
    }

    public String getTipoProfesor() {
        return tipoProfesor;
    }

    public void cargarInfo() throws Exception {
        this.nombreCompleto = academico.getNombreCompleto();
        this.correo = academico.getCorreoAca();
        this.departamento = academico.getDepartamento();
        this.noTrabajador = academico.getNoTrabajador();
        this.tipoProfesor = academico.getTipo();
        this.userName = academico.getUserName();
        this.password = academico.getPassword();
    }

    public void validatepOldPassword(FacesContext context, UIComponent component, Object value) {
        if (this.academico.getPassword().equals(value.toString())) {
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
                academico.setPassword(this.newPassword);
            }
            academico.setCorreoAca(this.correo);
            academico.setUserName(this.userName);
            AcademicoDao academicoDao = new AcademicoDao();
            academicoDao.getEntityManager().getTransaction().begin();
            academicoDao.update(academico);
            academicoDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha actualizado tu información correctamente.",
                            "Se ha actualizado tu información correctamente.."));
        }
        this.cambioPass = false;
        this.passConfirmada = false;

    }

    public ModificarPropiedadesController(String nombreCompleto, String userName, String password, String correo, String noTrabajador, String departamento, String tipoProfesor) {
        this.academico = (Academico) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Academico");
        this.nombreCompleto = nombreCompleto;
        this.userName = userName;
        this.password = password;
        this.correo = correo;
        this.noTrabajador = noTrabajador;
        this.departamento = departamento;
        this.tipoProfesor = tipoProfesor;
    }

    public ModificarPropiedadesController() {
        this.nombreCompleto = academico.getNombreCompleto();
        this.userName = academico.getUserName();
        this.password = academico.getPassword();
        this.correo = academico.getCorreoAca();
        this.noTrabajador = academico.getNoTrabajador();
        this.departamento = academico.getDepartamento();
        this.tipoProfesor = academico.getTipo();
        this.cambioPass = false;
        this.passConfirmada = false;
    }

    public void validarNuevoCorreoAcademico(FacesContext context, UIComponent component, Object value) {
        if (!value.toString().equals(this.correo)) {
            System.out.println("Los valores son diferentes" + value.toString() + "-" + this.academico.getCorreoAca());
            AcademicoDao academicoDao = new AcademicoDao();
            if (academicoDao.mailExist((String) value)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El correo que intenta dar ya está registrado, escriba otro.",
                        "El correo que intenta dar ya está registrado, escriba otro.");
                throw new ValidatorException(msg);
            }
        }
    }

    public void validarNuevoUsernameAcademico(FacesContext context, UIComponent component, Object value) {
        if (!value.toString().equals(this.userName)) {
            System.out.println("Los valores son diferentes" + value.toString() + "-" + this.academico.getUserName());
            AcademicoDao academicoDao = new AcademicoDao();
            if (academicoDao.userNameExist(value.toString())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El academico ya existe, escriba otro.",
                        "El academico ya existe, escriba otro.");
                throw new ValidatorException(msg);
            }
        }
    }

}
