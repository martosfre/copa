/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import com.matoosfe.copa.beans.util.AbstractManagedBean;
import com.matoosfe.copa.controllers.CantonController;
import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.controllers.JugadorController;
import com.matoosfe.copa.controllers.LocalidadController;
import com.matoosfe.copa.controllers.ParroquiaController;
import com.matoosfe.copa.controllers.ProvinciaController;
import com.matoosfe.copa.entities.Canton;
import com.matoosfe.copa.entities.Equipo;
import com.matoosfe.copa.entities.Jugador;
import com.matoosfe.copa.entities.Localidad;
import com.matoosfe.copa.entities.Parroquia;
import com.matoosfe.copa.entities.Provincia;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
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
    @Getter
    @Setter
    private List<Localidad> listaLocalidades;

    @Inject
    private EquipoController adminEquipo;
    @Inject
    private ProvinciaController adminProvincia;
    @Inject
    private CantonController adminCanton;
    @Inject
    private ParroquiaController adminParroquia;
    @Inject
    private JugadorController adminJugador;
    @Inject
    private LocalidadController adminLocalidad;

    public JugadorBean() {
        this.jugador = new Jugador();
        this.listaJugadores = new ArrayList<>();
        this.listaEquipos = new ArrayList<>();
        this.listaProvincias = new ArrayList<>();
        this.listaCantones = new ArrayList<>();
        this.listaParroquias = new ArrayList<>();
        this.listaLocalidades  = new ArrayList<>();
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
     *
     * @param file
     */
    public void cargarFoto(FileUploadEvent foto) {
        InputStream fis = new ByteArrayInputStream(foto.getFile().getContent());
        this.fotoJugador = DefaultStreamedContent.builder()
                .stream(() -> fis).build();
        this.jugador.setJugFoto(foto.getFile().getContent());
    }

    /**
     * Método para guardar o actualizar un jugador
     */
    public void guardar() {
        try {
            if (jugador.getJugId() == null) {
                adminJugador.guardar(jugador);
                anadirInfo("Jugador registrado  correctamente");
            } else {
                adminJugador.actualizar(jugador);
                anadirInfo("Jugador actualizado  correctamente");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Errror al guardar jugador:" + e.getMessage());
        }
    }

    /**
     * Método para editar un jugador
     */
    public void editar() {
        try {
            if (jugadorSel != null) {
                this.jugador = jugadorSel;
                this.provincia = this.jugador.getParrId().getCanId().getProId();
                cargarCantonesPorProvincia();
                this.canton = this.jugador.getParrId().getCanId();
                cargarParroquiasPorCanton();
                InputStream fis = new ByteArrayInputStream(jugadorSel.getJugFoto());
                this.fotoJugador = DefaultStreamedContent.builder()
                        .stream(() -> fis).build();
            } else {
                anadirError("Se debe seleccionar un jugador");
            }
        } catch (Exception e) {
            anadirError("No se pudo cargar la información del jugador para su edición");
        }
    }

    /**
     * Método para eliminar un jugador
     */
    public void eliminar() {
        try {
            if (jugadorSel != null) {
                adminJugador.eliminar(jugadorSel);
                anadirInfo("Jugador eliminado correctamente");
                resetearFormulario();
            } else {
                anadirError("Se debe seleccionar un jugador");
            }
        } catch (Exception e) {
            anadirError("No se pudo eliminar el jugador:" + e.getMessage());
        }
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
     * Método para buscar jugadores por equipo
     */
    public void buscarJugadores() {
        this.listaJugadores.clear();

        adminJugador.buscarPorEquipo(equipo).forEach(jug -> {
            String localidad = jug.getParrId().getCanId().getProId().getProNombre()
                    + "/" + jug.getParrId().getCanId().getCanNombre()
                    + "/" + jug.getParrId().getParrNombre();
            jug.setLocalidad(localidad);
            listaJugadores.add(jug);
        });
    }

    
    private void cargarLocalidades(){
        this.listaLocalidades = adminLocalidad.consultarLocalidades();
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
        this.fotoJugador = null;
        this.listaJugadores.clear();
        this.listaCantones.clear();
        this.listaParroquias.clear();
        cargarEquipos();
    }

    @PostConstruct
    public void inicializar() {
        cargarEquipos();
        cargarProvincias();
        cargarLocalidades();
    }
}
