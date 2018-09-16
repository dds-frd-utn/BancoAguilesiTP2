/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.aguilesi.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utn.frd.aguilesi.sessions.MovimientoFacade;
import utn.frd.aguilesi.entities.Movimiento;


/**
*
* @author Sergio
*/
@Path("/movimiento")
public class MovimientoRest {
@EJB
private MovimientoFacade ejbMovimientoFacade;
//obtener todas las entidades
@GET
@Produces({MediaType.APPLICATION_JSON})
public List<Movimiento> findAll(){
return ejbMovimientoFacade.findAll();
}
//crear entidades
@POST
@Consumes({MediaType.APPLICATION_JSON})
public void create(Movimiento movimiento){
ejbMovimientoFacade.create(movimiento);
}
//actualizar entidades
@PUT
@Consumes({MediaType.APPLICATION_JSON})
@Path("/{id}")
public void edit(@PathParam("id")long id, Movimiento movimiento){
ejbMovimientoFacade.edit(movimiento);
}

//eliminar entidades
@DELETE
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Path("/{id}")
public void remove(@PathParam("id")int id){
ejbMovimientoFacade.remove( ejbMovimientoFacade.find(id) );
}
//obtener una entidad por id
@GET
@Path("/{id}")
@Produces({MediaType.APPLICATION_JSON})
public Movimiento findById(@PathParam("id")long id){
return (Movimiento) ejbMovimientoFacade.find(id);
}

//obtener una entidad por id
@GET
@Path("/deCuenta/{id}")
@Produces({MediaType.APPLICATION_JSON})
public List<Movimiento> findByCuentaId(@PathParam("id")long id){
return ejbMovimientoFacade.find10MovimientosByCuentaId(id);
}
@GET
@Path("/{id}/{descripcion}")
@Produces({MediaType.APPLICATION_JSON})
public List<Movimiento> findByDescripcion(@PathParam("id")long id,
    @PathParam("descripcion")String descripcion){
return ejbMovimientoFacade.findAllMovimientosByDescripcion(descripcion,id);
}

@GET
@Path("/{id}/estado/{id2}")
@Produces({MediaType.APPLICATION_JSON})
public List<Movimiento> findByEstadoId(@PathParam("id")long id,
        @PathParam("id2")long id2){
return ejbMovimientoFacade.findAllMovimientosByEstado(id,id2);

}
}   
