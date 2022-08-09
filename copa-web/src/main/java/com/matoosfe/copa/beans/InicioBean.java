/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author martosfre
 */
@ManagedBean
@SessionScoped
public class InicioBean {

    private String nombreUsuario;
    private String claveUsuario;

    public InicioBean() {
        this.nombreUsuario = "admin";
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    /**
     * Método para validar el usuario
     * @return 
     */
    public String validarUsuario() {
        FacesMessage mensajeJSF;
        if (!nombreUsuario.equals("admin")) {
            mensajeJSF = new FacesMessage("Credenciales Incorrectas");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else {
            mensajeJSF = new FacesMessage("Bienvenido");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
        return null; //Misma Página
    }

}
