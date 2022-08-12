/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Parroquia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * no-view interface
 * @author martosfre
 */
@Stateless
public class ParroquiaController extends AbstractController<Parroquia> {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;
    
    public ParroquiaController(){
        super(Parroquia.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
      return em;
    }

}
