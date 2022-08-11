/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author martosfre
 */
public abstract class AbstractManagedBean {

    public void anadirInfo(String mensaje) {
        generarMensaje(mensaje, FacesMessage.SEVERITY_INFO);
    }

    public void anadirError(String mensaje) {
        generarMensaje(mensaje, FacesMessage.SEVERITY_ERROR);
    }

    private void generarMensaje(String mensaje, FacesMessage.Severity severity) {
        FacesMessage mensajeJSF = new FacesMessage();
        mensajeJSF.setSummary(mensaje);
        mensajeJSF.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
    }
}
