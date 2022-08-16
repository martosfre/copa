/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.controllers;

import com.matoosfe.copa.entities.Localidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author martosfre
 */
@Stateless
public class LocalidadController {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;

    public List<Localidad> consultarLocalidades() {
        Query conLoca = em.createNativeQuery("select row_number() over(order by p.pro_nombre) as id, p.pro_nombre as provincia,"
                + " c.can_nombre as canton, pa.parr_nombre as parroquia  from admin.provincia p, admin.canton c, admin.parroquia pa\n"
                + "where p.pro_id  = c.pro_id and c.can_id = pa.can_id; ", Localidad.class);
        return conLoca.getResultList();
    }
}
