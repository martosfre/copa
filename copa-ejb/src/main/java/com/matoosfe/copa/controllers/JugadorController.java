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
import javax.persistence.TypedQuery;

/**
 * no-view interface
 * @author martosfre
 */
@Stateless
public class JugadorController extends AbstractController<Jugador> {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;
    
    public JugadorController(){
        super(Jugador.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
      return em;
    }
    
    /**
     * Método para buscar jugadores por equipo
     * @param equipo
     * @return 
     */
    public List<Jugador> buscarPorEquipo(Equipo equipo){
        TypedQuery<Jugador> conJug = em.createQuery("Select jug from Jugador jug "
                + " where jug.equId =:equipo", Jugador.class);
        conJug.setParameter("equipo", equipo);
        return conJug.getResultList();
    }

}
