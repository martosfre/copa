/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import com.matoosfe.copa.beans.util.AbstractManagedBean;
import com.matoosfe.copa.controllers.CantonController;
import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.controllers.ParroquiaController;
import com.matoosfe.copa.controllers.ProvinciaController;
import com.matoosfe.copa.entities.Canton;
import com.matoosfe.copa.entities.Equipo;
import com.matoosfe.copa.entities.Jugador;
import com.matoosfe.copa.entities.Parroquia;
import com.matoosfe.copa.entities.Provincia;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    @Getter
    @Setter
    private StreamedContent fotoJugador;
    @Getter
    @Setter
    private String pathImagen;

    @Inject
    private EquipoController adminEquipo;
    @Inject
    private ProvinciaController adminProvincia;
    @Inject
    private CantonController adminCanton;
    @Inject
    private ParroquiaController adminParroquia;

    public JugadorBean() {
        this.jugador = new Jugador();
        this.listaJugadores = new ArrayList<>();
        this.listaEquipos = new ArrayList<>();
        this.listaProvincias = new ArrayList<>();
        this.listaCantones = new ArrayList<>();
        this.listaParroquias = new ArrayList<>();
        this.pathImagen = "/resources/img/igcognito.png";
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
        try {
            this.listaProvincias = adminProvincia.consultarTodos();
        } catch (Exception e) {
            anadirError("No se pudo cargar las provincias:" + e.getMessage());
        }
    }

    /**
     * Método para cargar cantones por provincia
     */
    public void cargarCantonesPorProvincia() {
        try {
            this.listaCantones = adminCanton.consultarPorProvincia(provincia);
        } catch (Exception e) {
            anadirError("No se pudo cargar los cantones por provincia:" + e.getMessage());
        }
    }

    /**
     * Método para cargar parroquias por cantón
     */
    public void cargarParroquiasPorCanton() {
        try {
            this.listaParroquias = adminParroquia.consultarPorCanton(canton);
        } catch (Exception e) {
            anadirError("No se pudo cargar los cantones por provincia:" + e.getMessage());
        }
    }

    /**
     * Método para cargar la foto del jugador
     * @param file 
     */
    public void cargarFoto(FileUploadEvent foto){
        InputStream fis = new ByteArrayInputStream(foto.getFile().getContent());
        this.fotoJugador = DefaultStreamedContent.builder()
                .stream(() -> fis).build();
        this.jugador.setJugFoto(foto.getFile().getContent());
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
