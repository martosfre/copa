/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.converters;

import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.entities.Equipo;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convEqu")
@RequestScoped
public class ConvEquipo implements Converter<Equipo>{

    @Inject
    private EquipoController adminEquipo;
    
    //Viene de la pantalla a la bdd (2)
    @Override
    public Equipo getAsObject(FacesContext fc, UIComponent uic, String codigoEqu) {
        Equipo equipo = null;
        if(codigoEqu != null){
            equipo = adminEquipo.consultarPorId(Integer.parseInt(codigoEqu));
        }
        return equipo;
    }

    //Viene de la bdd a la pantalla (1)
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Equipo equipo) {
        String codigoEqu = null;
        if(equipo != null){
           codigoEqu = equipo.getEquId().toString();
       }
        return codigoEqu;
    }
    
}
