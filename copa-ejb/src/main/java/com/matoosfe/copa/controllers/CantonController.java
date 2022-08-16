/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Canton;
import com.matoosfe.copa.entities.Provincia;
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
public class CantonController extends AbstractController<Canton> {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;
    
    public CantonController(){
        super(Canton.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
      return em;
    }

    /**
     * Método para consultar cantones por provincia
     * @param provincia provincia seleccionada
     * @return 
     */
    public List<Canton> consultarPorProvincia(Provincia provincia) {
        TypedQuery<Canton> conCanPro = em.createQuery("Select can from Canton can "
                + " where can.proId =:provincia", Canton.class);
        conCanPro.setParameter("provincia", provincia);
        return conCanPro.getResultList();
    }

}
