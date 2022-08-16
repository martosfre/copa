/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Canton;
import com.matoosfe.copa.entities.Parroquia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<Parroquia> consultarPorCanton(Canton canton) {
        Query conParCan = em.createNativeQuery("select p.* from admin.parroquia p"
                + " where p.can_id = ?1 ", Parroquia.class);
        conParCan.setParameter(1, canton.getCanId());
        return conParCan.getResultList();
    }

}
