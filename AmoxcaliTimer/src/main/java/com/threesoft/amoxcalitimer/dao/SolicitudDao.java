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
    
}