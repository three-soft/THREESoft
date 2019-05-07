/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcalitimer.dao.EspacioDao;
import com.threesoft.amoxcalitimer.models.Espacio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author damri
 */
@ManagedBean
@SessionScoped
public class EspaciosController {

    private List<Espacio> espacios;

    public EspaciosController() {
        this.espacios = new ArrayList<>();
    }

    public List<Espacio> getEspacios() {
        EspacioDao espacioDao = new EspacioDao();
        this.espacios = espacioDao.getAll();
        return this.espacios;
    }
    
    
    
    
    
}
