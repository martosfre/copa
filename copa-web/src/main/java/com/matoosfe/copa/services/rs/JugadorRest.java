/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.services.rs;

import com.matoosfe.copa.controllers.JugadorController;
import com.matoosfe.copa.entities.Jugador;
import com.matoosfe.copa.services.rs.util.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author martosfre
 */
@Path("jugador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JugadorRest {
    @Inject
    private JugadorController adminJugador;
    
    @POST
    public String guardar(Jugador jugador){
        adminJugador.guardar(jugador);
        return "Jugador guardado correctamente";
    }
    
     @GET
    public List<Jugador> consultar(){
        return adminJugador.consultarTodos();
    }
    
    @GET
    @Path("/consultarTodos")
    public List<JugadorDTO> consultarTodos(){
        List<JugadorDTO> listaJugador = new ArrayList<>();
        adminJugador.consultarTodos().forEach(jug -> {
            JugadorDTO jugDto = new JugadorDTO();
            jugDto.setJugId(jug.getJugId());
            jugDto.setJugNombre(jug.getJugNombre());
            jugDto.setJugApellido(jug.getJugApellidoPaterno());
            listaJugador.add(jugDto);
        });
        return listaJugador;
    }
    
    @GET
    @Path("/sumar/{paramA}/{paramB}")
    public String sumar(@PathParam("paramA") int a, @PathParam("paramB") int b){
        return "El valor total es:" + (a + b);
    }
    
     @GET
    @Path("/restar")
    public String restar(@QueryParam("paramA") int a, @QueryParam("paramB") int b){
        return "El valor total es:" + (a - b);
    }
    
    
}
