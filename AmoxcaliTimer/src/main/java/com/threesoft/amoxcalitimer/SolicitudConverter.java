/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer;

import com.threesoft.amoxcalitimer.dao.SolicitudDao;
import com.threesoft.amoxcalitimer.models.Solicitud;
import com.threesoft.amoxcalitimer.models.SolicitudPK;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author damianri
 */
@FacesConverter("solicitudConverter")
public class SolicitudConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                SolicitudDao solicitudDao=new SolicitudDao();
                SolicitudPK key = new SolicitudPK();
                return solicitudDao.getByKey(key);// getByKey(Long.parseLong(value));//getByKey(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Solicitud) object).getSolicitudPK());
        }
        else {
            return null;
        }
    }
}