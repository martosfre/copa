/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Equipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
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

}
