/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Equipo;
import com.matoosfe.copa.entities.Jugador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * no-view interface
 * @author martosfre
 */
@Stateless
public class EquipoController extends AbstractController<Equipo> {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;
    
    public EquipoController(){
        super(Equipo.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
      return em;
    }
    
    /**
     * Método para consultar los equipos
     * @return 
     */
    public List<Equipo> consultarEquipos(){
        return em.createNamedQuery("Equipo.findAll").getResultList();
    }

    /**
     * Método para guardar equipo con jugadores
     * @param equipo
     * @param listaJugadores
     * @return 
     */
    public String guardar(Equipo equipo, List<Jugador> listaJugadores) {
       em.persist(equipo);
       listaJugadores.forEach(jug -> {
           em.persist(jug);
       });
       return "Equipo con Jugaddores guardado correctamente";
    }
    
    /**
     * Método para guardar equipo con jugadores
     * @param equipo
     * @param listaJugadores
     * @return 
     */
    public String guardarJug(Equipo equipo, List<Jugador> listaJugadores) {
       em.persist(equipo);
       guardarJugadores(listaJugadores);
       return "Equipo con Jugaddores guardado correctamente";
    }
    
    /**
     * Método para guardar jugadores
     * @param listaJugadores 
     */
    public void guardarJugadores(List<Jugador> listaJugadores){
        listaJugadores.forEach(jug -> {
           em.persist(jug);
       });
    }

}
