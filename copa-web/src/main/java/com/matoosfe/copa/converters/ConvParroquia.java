/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.converters;

import com.matoosfe.copa.controllers.ParroquiaController;
import com.matoosfe.copa.entities.Parroquia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author martosfre
 */
@FacesConverter("convParr")
public class ConvParroquia implements Converter<Parroquia>{

    @Inject
    private ParroquiaController adminParroquia;
    
    //Viene de la pantalla a la bdd (2)
    @Override
    public Parroquia getAsObject(FacesContext fc, UIComponent uic, String codigoParr) {
        Parroquia parroquia = null;
        if(codigoParr != null){
            parroquia = adminParroquia.consultarPorId(Integer.parseInt(codigoParr));
        }
        return parroquia;
    }

    //Viene de la bdd a la pantalla (1)
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Parroquia parroquia) {
        String codigoParr = null;
        if(parroquia != null){
           codigoParr = parroquia.getParrId().toString();
       }
        return codigoParr;
    }
    
}
