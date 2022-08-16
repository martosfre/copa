/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.converters;

import com.matoosfe.copa.controllers.CantonController;
import com.matoosfe.copa.entities.Canton;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

/**
 *
 * @author martosfre
 */
@FacesConverter("convCan")
public class ConvCanton implements Converter<Canton> {

    private CantonController adminCanton;

    //Viene de la pantalla a la bdd (2)
    @Override
    public Canton getAsObject(FacesContext fc, UIComponent uic, String codigoCan) {
        Canton canton = null;
        try {
            InitialContext ic = new InitialContext();
            adminCanton = (CantonController) ic.lookup("java:global/copa-ear-1.0/copa-web-1.0/CantonController!com.matoosfe.copa.controllers.CantonController");

            if (codigoCan != null) {
                canton = adminCanton.consultarPorId(Integer.parseInt(codigoCan));
            }
        } catch (Exception e) {

        }
        return canton;
    }

    //Viene de la bdd a la pantalla (1)
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Canton canton) {
        String codigoCan = null;
        if (canton != null) {
            codigoCan = canton.getCanId().toString();
        }
        return codigoCan;
    }

}
