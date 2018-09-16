/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.aguilesi.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utn.frd.aguilesi.entities.Movimiento;

/**
 *
 * @author neloo
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "utn.frd_Aguilesi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
    
    public List<Movimiento> findAllMovimientosByCuentaId(Long idCuenta){
    
     TypedQuery<Movimiento> consultarMovimientosCuenta = em.createNamedQuery("Movimiento.findByIdCuenta", Movimiento.class);
     consultarMovimientosCuenta.setParameter("idCuenta", idCuenta);
     List<Movimiento> result = consultarMovimientosCuenta.getResultList();
     return result;
    
             }
    
    public List<Movimiento> find10MovimientosByCuentaId(Long idCuenta){
    
     TypedQuery<Movimiento> consultarMovimientosCuenta = em.createNamedQuery("Movimiento.findByIdCuenta", Movimiento.class);
     consultarMovimientosCuenta.setParameter("idCuenta", idCuenta);
     List<Movimiento> result = consultarMovimientosCuenta.getResultList();
     return result.subList(0, Math.min(9, result.size()));
    
             }
  
    public List<Movimiento> findAllMovimientosByEstado(Long idCuenta, Long estado){
    
     TypedQuery<Movimiento> consultarMovimientosCuentaEstado = em.createNamedQuery("Movimiento.findByIdCuentaByEstado", Movimiento.class);
     consultarMovimientosCuentaEstado.setParameter("idCuenta",idCuenta);
     consultarMovimientosCuentaEstado.setParameter("estado",estado);
     List<Movimiento> result = consultarMovimientosCuentaEstado.getResultList();
     return result;
    }
    public List<Movimiento> findAllMovimientosByDescripcion(String descripcion, Long idCuenta){
    
     TypedQuery<Movimiento> consultarMovimientosDescripcion = em.createNamedQuery("Movimiento.findByCuentaByDescripcion", Movimiento.class);
     consultarMovimientosDescripcion.setParameter("descripcion",descripcion);
     consultarMovimientosDescripcion.setParameter("idCuenta",idCuenta);
     List<Movimiento> result = consultarMovimientosDescripcion.getResultList();
     return result;
    }
}