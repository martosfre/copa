/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.matoosfe.copa.beans.services;

import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.entities.Equipo;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author martosfre
 */
@WebService
public class EquipoWS {
    
    @Resource
    WebServiceContext wsctx;
    
    @Inject
    private EquipoController adminEquipo;

    /**
     * This is a sample web service operation
     */
    @WebMethod
    public List<Equipo> consultarEquipos() {
        return adminEquipo.consultarEquipos();
    }
    
    @WebMethod
    public String guardarEquipo(Equipo equipo) {
        adminEquipo.guardar(equipo);
        return "Equipo guardado correctamente";
    }
    
    @WebMethod
    public String actualizarEquipo(Equipo equipo) {
        adminEquipo.actualizar(equipo);
        return "Equipo guardado correctamente";
    }
    
    @WebMethod
    public String eliminarEquipo(@WebParam(name = "codigoEquipo") Integer id) {
        MessageContext mctx = wsctx.getMessageContext();
        
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> usuario = (List<String>) http_headers.get("username");
        List<String> password = (List<String>) http_headers.get("password");
        
        if (usuario != null && usuario.get(0).equals("mtoscano") && password != null && password.get(0).equals("1234")) {
            
            Equipo equipo = adminEquipo.consultarPorId(id);
            adminEquipo.eliminar(equipo);
            return "Equipo eliminado correctamente";
        } else {
            return "Usuario Incorrecto";
        }
    }
}
