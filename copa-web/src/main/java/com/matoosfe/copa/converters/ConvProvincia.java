/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.converters;

import com.matoosfe.copa.controllers.ProvinciaController;
import com.matoosfe.copa.entities.Provincia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author martosfre
 */
@FacesConverter("convPro")
public class ConvProvincia implements Converter<Provincia>{

    @Inject
    private ProvinciaController adminProvincia;
    
    //Viene de la pantalla a la bdd (2)
    @Override
    public Provincia getAsObject(FacesContext fc, UIComponent uic, String codigoPro) {
        Provincia provincia = null;
        if(codigoPro != null){
            provincia = adminProvincia.consultarPorId(Integer.parseInt(codigoPro));
        }
        return provincia;
    }

    //Viene de la bdd a la pantalla (1)
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Provincia provincia) {
        String codigoPro = null;
        if(provincia != null){
           codigoPro = provincia.getProId().toString();
       }
        return codigoPro;
    }
    
}
