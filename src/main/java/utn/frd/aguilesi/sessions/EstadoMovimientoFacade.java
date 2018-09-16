/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.aguilesi.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frd.aguilesi.entities.EstadoMovimiento;

/**
 *
 * @author neloo
 */
@Stateless
public class EstadoMovimientoFacade extends AbstractFacade<EstadoMovimiento> {

    @PersistenceContext(unitName = "utn.frd_Aguilesi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoMovimientoFacade() {
        super(EstadoMovimiento.class);
    }
    
}
