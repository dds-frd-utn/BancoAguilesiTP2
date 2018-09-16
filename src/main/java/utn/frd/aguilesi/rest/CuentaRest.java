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
import utn.frd.aguilesi.entities.Cuenta;
import utn.frd.aguilesi.sessions.CuentaFacade;

@Path("/cuenta")
public class CuentaRest {

    @EJB
    private CuentaFacade ejbCuentaFacade;

    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuenta> findAll() {
        return ejbCuentaFacade.findAll();
    }

    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Cuenta cuenta) {
        ejbCuentaFacade.create(cuenta);
    }

    //actualizar entidades
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id") int id, Cuenta cuenta) {
        ejbCuentaFacade.edit(cuenta);
    }

    //eliminar entidades
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public void remove(@PathParam("id") int id) {
        ejbCuentaFacade.remove(ejbCuentaFacade.find(id));
    }

    //obtener una entidad por id
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cuenta findById(@PathParam("id") long id) {
        return (Cuenta) ejbCuentaFacade.find(id);
    }

//obtener una entidad por id
    @GET
    @Path("/deCliente/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuenta> findByClienteId(@PathParam("id") long id) {
        return ejbCuentaFacade.findAllAccountsByClientId(id);
    }

    @GET
    @Path("/saldo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public double findSaldo(@PathParam("id") long id) {
        return ejbCuentaFacade.saldoDeCuenta(id);
    }

}
