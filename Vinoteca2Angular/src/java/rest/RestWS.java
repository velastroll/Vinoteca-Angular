/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import dominio.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import persistencia.*;
/**
 *
 * @author Mario Torbado
 */
@javax.ws.rs.ApplicationPath("webresources")
@Path("/paraAngular")
public class RestWS extends Application{
    @EJB
    private VinoFacadeLocal vinoFacade;
    @EJB
    private AbonadoFacadeLocal abonadoFacade;
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;
    //meter EJBs facade
    
    public RestWS() {
    }
    
    @GET
    @Path("/abonado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAbonadoByID(@PathParam("id") String id) {
        
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        Abonado a = abonadoFacade.find(id);
        if (a == null) {
            respuesta.status(Response.Status.NOT_FOUND).build();
        }
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        respuesta.entity(a);
        return respuesta.build();
    }
    
    @GET
    @Path("/empleado/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleadoByID(@PathParam("id") String id) {
        
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        Empleado e = empleadoFacade.find(id);
        if (e == null) {
            respuesta.status(Response.Status.NOT_FOUND).build();
        }
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        respuesta.entity(e);
        return respuesta.build();
    }
    
    
    /**
     * Login
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("vinos")
    public Vino[] getVinos() {
        List<Vino> vinos = vinoFacade.findAll();
        System.out.println(vinos.get(0).getNombrecomercial());
        Vino[] arrayVino = new Vino[vinos.size()];
        for (int i = 0; i < arrayVino.length; i++) {
            arrayVino[i] = vinos.get(i);
        }
        return arrayVino;
    }
    
  
// String vinoJsonStr = vino.toString();
}
