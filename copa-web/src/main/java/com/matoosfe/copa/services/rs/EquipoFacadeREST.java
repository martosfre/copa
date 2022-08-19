/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.services.rs;

import com.matoosfe.copa.controllers.AbstractController;
import com.matoosfe.copa.entities.Equipo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author martosfre
 */
@Stateless
@Path("equipo")
public class EquipoFacadeREST extends AbstractController<Equipo> {

    @PersistenceContext(unitName = "copaPU")
    private EntityManager em;

    public EquipoFacadeREST() {
        super(Equipo.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String guardarEquipo(Equipo entity) {
        super.guardar(entity);
        return "Registro guardado correctamente";
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void actualizar(Equipo entity) {
        super.actualizar(entity);
    }

    @DELETE
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        super.eliminar(super.consultarPorId(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Equipo consultarPorId(@PathParam("id") Integer id) {
        return super.consultarPorId(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Equipo> consultarEquipos() {
        return super.consultarTodos();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
