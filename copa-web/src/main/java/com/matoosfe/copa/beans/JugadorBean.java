/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import com.matoosfe.copa.beans.util.AbstractManagedBean;
import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.entities.Canton;
import com.matoosfe.copa.entities.Equipo;
import com.matoosfe.copa.entities.Jugador;
import com.matoosfe.copa.entities.Parroquia;
import com.matoosfe.copa.entities.Provincia;
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
 */
@Named
@ViewScoped
public class JugadorBean extends AbstractManagedBean implements Serializable {
    
    @Getter
    @Setter
    private Jugador jugador;
    @Getter
    @Setter
    private Jugador jugadorSel;
    @Getter
    @Setter
    private List<Jugador> listaJugadores;
    @Getter
    @Setter
    private List<Equipo> listaEquipos;
    @Getter
    @Setter
    private Equipo equipo;
    @Getter
    @Setter
    private List<Provincia> listaProvincias;
    @Getter
    @Setter
    private Provincia provincia;
    @Getter
    @Setter
    private List<Canton> listaCantones;
    @Getter
    @Setter
    private Canton canton;
    @Getter
    @Setter
    private List<Parroquia> listaParroquias;
    
    @Inject
    private EquipoController adminEquipo;
    
    public JugadorBean() {
        this.jugador = new Jugador();
        this.listaJugadores = new ArrayList<>();
        this.listaEquipos = new ArrayList<>();
        this.listaProvincias = new ArrayList<>();
        this.listaCantones = new ArrayList<>();
        this.listaParroquias = new ArrayList<>();
    }

    /**
     * Método para cargar equipos
     */
    private void cargarEquipos() {
        try {
            this.listaEquipos = adminEquipo.consultarTodos();
        } catch (Exception e) {
            anadirError("Error al cargar equipos:" + e.getMessage());
        }
    }

    /**
     * Método para cargar provincias
     */
    private void cargarProvincias() {
        
    }

    /**
     * Método para guardar o actualizar un jugador
     */
    public void guardar() {
        
    }

    /**
     * Método para editar un jugador
     */
    public void editar() {
        
    }

    /**
     * Método para eliminar un jugador
     */
    public void eliminar() {
        
    }

    /**
     * Método para seleccionar una fila
     *
     * @param evt
     */
    public void seleccionarFila(SelectEvent<Jugador> evt) {
        this.jugadorSel = evt.getObject();
    }

    /**
     * Método para resetear formulario
     */
    public void resetearFormulario() {
        this.jugador = new Jugador();
        this.jugadorSel = null;
        this.provincia = null;
        this.canton = null;
        this.equipo = null;
        this.listaJugadores.clear();
    }
    
    @PostConstruct
    public void inicializar() {
        cargarEquipos();
        cargarProvincias();
    }
}
