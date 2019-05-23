/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author river
 */
@Entity
@Table(name = "administrador", catalog = "postgres", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByIdAdministrador", query = "SELECT a FROM Administrador a WHERE a.idAdministrador = :idAdministrador")
    , @NamedQuery(name = "Administrador.findByNombreCompleto", query = "SELECT a FROM Administrador a WHERE a.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Administrador.findByCorreoAdmin", query = "SELECT a FROM Administrador a WHERE a.correoAdmin = :correoAdmin")
    , @NamedQuery(name = "Administrador.findByPassword", query = "SELECT a FROM Administrador a WHERE a.password = :password")
    , @NamedQuery(name = "Administrador.findByNoTrabajador", query = "SELECT a FROM Administrador a WHERE a.noTrabajador = :noTrabajador")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_administrador", nullable = false)
    private Long idAdministrador;
    @Basic(optional = false)
    @Column(name = "nombre_completo", nullable = false, length = 210)
    private String nombreCompleto;
    @Basic(optional = false)
    @Column(name = "correo_admin", nullable = false, length = 210)
    private String correoAdmin;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 210)
    private String password;
    @Basic(optional = false)
    @Column(name = "no_trabajador", nullable = false, length = 20)
    private String noTrabajador;

    public Administrador() {
    }

    public Administrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Administrador(Long idAdministrador, String nombreCompleto, String correoAdmin, String password, String noTrabajador) {
        this.idAdministrador = idAdministrador;
        this.nombreCompleto = nombreCompleto;
        this.correoAdmin = correoAdmin;
        this.password = password;
        this.noTrabajador = noTrabajador;
    }

    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTrabajador() {
        return noTrabajador;
    }

    public void setNoTrabajador(String noTrabajador) {
        this.noTrabajador = noTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdministrador != null ? idAdministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idAdministrador == null && other.idAdministrador != null) || (this.idAdministrador != null && !this.idAdministrador.equals(other.idAdministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.threesoft.amoxcalitimer.models.Administrador[ idAdministrador=" + idAdministrador + " ]";
    }
    
}
