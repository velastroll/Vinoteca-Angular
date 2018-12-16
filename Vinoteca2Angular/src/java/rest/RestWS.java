/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import dominio.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
    private PedidoFacadeLocal pedidoFacade;
    @EJB
    private EstadoPedidoFacadeLocal estadoPedidoFacade;
    @EJB
    private ReferenciaFacadeLocal referenciaFacade;
    @EJB
    private VinoFacadeLocal vinoFacade;
    @EJB
    private AbonadoFacadeLocal abonadoFacade;
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;
    @EJB
    private PreferenciaFacadeLocal preferenciaFacade;
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
        
        Abonado[] ab = new Abonado[1];
        ab[0] = a;
        respuesta.entity(ab);
        System.out.println("> getAbonadoByID(" + id + ") : Encontrado " + a.getAbLogin());
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

    @GET
    @Path("/preferencias/vinos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVinosDisponibles(@PathParam("id") String id) {
        
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        
        List<Vino> vinos = vinoFacade.findAll();
        List<Preferencia> preferencias = preferenciaFacade.findAll();
        Abonado a = abonadoFacade.find(id);
        List<Vino> disponibles = new ArrayList();
        /* creamos una lista de preferencias de un determinado usuario */
        for(Preferencia p: preferencias){
            if ( p.getNifabonado().getAbNif().equals(a.getAbNif()) ) {
                // Creamos una lista de los vinos disponibles
                for(Vino v: vinos){
                    if (p.getCategoria().equals(v.getCategoria()) && p.getIddenominacion().equals(v.getIddenominacion()) ){
                        disponibles.add(v);
                    }
                }
            }
        }
        
        /* creamos un array de vinos disponibles */
        Vino[] arrayVino = new Vino[disponibles.size()];
        for(int i = 0; i < disponibles.size() ; i++){
            arrayVino[i] = disponibles.get(i);
        }
        
        // Generamos la respuesta
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        if (vinos == null || preferencias == null || arrayVino.length==0) {
            respuesta.status(Response.Status.NOT_FOUND);
        }
        respuesta.entity(arrayVino);
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
    
    /**
     * Hay que hacer que coja el Vino de la BD y lo meta en cesta de un EJB de sesión.
     * @param userid
     * @param vinoid
     * @return 
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/abonado/{userid}/vinos/{vinoid}")
    public Response add2cart(@PathParam("userid") String userid, @PathParam("vinoid") String vinoid) {
        
        System.out.println("Usuario: " + userid + "\nVino: " + vinoid + "\n");
        
        
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        /**
        List<Vino> vinos = vinoFacade.findAll();
        List<Preferencia> preferencias = preferenciaFacade.findAll();
        Abonado a = abonadoFacade.find(userid);
        List<Vino> disponibles = new ArrayList();
        // creamos una lista de preferencias de un determinado usuario /
        for(Preferencia p: preferencias){
            if ( p.getNifabonado().getAbNif().equals(a.getAbNif()) ) {
                // Creamos una lista de los vinos disponibles
                for(Vino v: vinos){
                    if (p.getCategoria().equals(v.getCategoria()) && p.getIddenominacion().equals(v.getIddenominacion()) ){
                        disponibles.add(v);
                    }
                }
            }
        }
        
        // creamos un array de vinos disponibles 
        Vino[] arrayVino = new Vino[disponibles.size()];
        for(int i = 0; i < disponibles.size() ; i++){
            arrayVino[i] = disponibles.get(i);
        }
        
        // Generamos la respuesta
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        if (vinos == null || preferencias == null || arrayVino.length==0) {
            respuesta.status(Response.Status.NOT_FOUND);
        }
        respuesta.entity(arrayVino);**/
        return respuesta.build();
    }
    
    /**
     * Hay que hacer que coja el vino de la cesta EJB de sesion y lo meta a la BD
     * como un pedido nuevo.
     * 
     * @param userid
     * @param vinoid
     * @return 
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{userid}/{vinoid}")
    public Response buyVino(@PathParam("userid") String userid, @PathParam("vinoid") String vinoid) {
        
        System.out.println("[BUY] Usuario: " + userid + "\nVino: " + vinoid + "\n");
        
        
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        
        // Añadimos el vino como pedido:
        
        Vino vino = vinoFacade.find(Integer.parseInt(vinoid));
        Abonado a = abonadoFacade.find(userid);
        
        if(vino != null) 
            System.out.println("\n> Encontrado vino que se quiere comprar: " + vinoid);
        
        
        List<Referencia> rfs = referenciaFacade.findAll();
        Referencia ref = null;
        
        for(Referencia r : rfs) {
            if (r.getVinoid().getId() == Integer.parseInt(vinoid)) {
                ref = r;
            }
        }
        
        if (ref != null ) System.out.println("> Encontrada referencia del vino: " + ref.toString());
        
        Pedido p = new Pedido();
        p.setPeNif(a);
        p.setPeReferencia(ref);
        p.setFecha("18-12-2018");
        p.setImporte(ref.getPrecio());
        p.setPeEstado(estadoPedidoFacade.find("p"));
        pedidoFacade.create(p);
        
        System.out.println("> Pedido de vino creado.\n");
        
        // Generamos la respuesta
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        if (a == null || ref == null || vino == null) {
            respuesta.status(Response.Status.NOT_FOUND);
        }
        return respuesta.build();
    }
    
    
    
  
// String vinoJsonStr = vino.toString();


}
