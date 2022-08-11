/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import com.matoosfe.copa.beans.util.AbstractManagedBean;
import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.entities.Equipo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author martosfre
 *///CDI
@Named
@ViewScoped
public class EquipoBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Equipo equipo;
    @Getter
    @Setter
    private Equipo equipoSel;
    @Getter
    @Setter
    private List<Equipo> listaEquipos;

    @Inject
    private EquipoController adminEquipo;

    public EquipoBean() {
        this.equipo = new Equipo();
        this.listaEquipos = new ArrayList<>();
    }

    /**
     * Método para cargar los equipos
     */
    private void cargarEquipos() {
        try {
            this.listaEquipos = adminEquipo.consultarEquipos();
        } catch (Exception e) {
            anadirError("Error al cargar los equipos:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un equipo
     */
    public void guardar() {
        try {
            if (equipo.getEquId() == null) {
                adminEquipo.guardar(equipo);
                anadirInfo("Equipo guardado correctamente");
            } else {
                adminEquipo.actualizar(equipo);
                anadirInfo("Equipo actualizado correctamente");
            }
            cargarEquipos();
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error:" + e.getMessage());
        }
    }

    /**
     * Selecccionar una fila
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Equipo> ev) {
        this.equipoSel = ev.getObject();
        this.equipo = equipoSel;
    }

    /**
     * Método para eliminar
     */
    public void eliminar() {
        if (equipoSel != null) {
            adminEquipo.eliminar(equipoSel);
            anadirInfo("Registro eliminado correctamente");
            cargarEquipos();
            resetearFormulario();
        }else{
            anadirError("Se debe seleccionar un equipo!!!");
        }
    }

    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.equipo = new Equipo();
        this.equipoSel = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarEquipos();
    }

}
