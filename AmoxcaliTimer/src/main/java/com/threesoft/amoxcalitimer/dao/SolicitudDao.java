/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.dao;

/**
 *
 * @author damianri
 */
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.threesoft.amoxcalitimer.models.Solicitud;
import com.threesoft.amoxcalitimer.models.SolicitudPK;
import java.util.ArrayList;

public class SolicitudDao extends AbstractDao<SolicitudPK , Solicitud> {
    
    public List<Solicitud> getSolicitudes() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Solicitud> crit = createCriteriaQuery(cb);
        Root<Solicitud> r = createRoot(crit);
        return this.findAll(crit, r, cb.asc(r.get("solicitudPK")));
    }    
    
    
        /**
     * Método que busca a un Espacio en la base por correo o por email.
     *
     * @param userOrEmail Es el correo o el email del Espacio.
     * @return El Espacio que se encontró en la base de datos o, en caso de no
     * encontrarse, regresa un null.
     */
    public Solicitud searchByUserNameOrEmail(String evento) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Solicitud> crit = createCriteriaQuery(cb);
        Root<Solicitud> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("nombreEvento"), evento),
                cb.equal(r.get("nombreEvento"), evento)));
    }

    public boolean userExist(String event) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Solicitud> crit = createCriteriaQuery(cb);
        Root<Solicitud> r = createRoot(crit);
        return this.count(cb.equal(r.get("nombreEvento"), event)) > 0;
    }

    /**
     * Método que regresa todos los Espacios en el sistema.
     *
     * @return La lista que contiene a todos los Espacios encontrados.
     */
    public List<Solicitud> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Solicitud> crit = createCriteriaQuery(cb);
        Root<Solicitud> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("nombreEvento")));
    }

    
}