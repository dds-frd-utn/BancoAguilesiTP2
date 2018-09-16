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
import utn.frd.aguilesi.entities.Cuenta;
import utn.frd.aguilesi.entities.Movimiento;

/**
 *
 * @author neloo
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "utn.frd_Aguilesi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    //public List<Cuenta> findAllAccounts( Long id){
    //  CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    //CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    // Root<Cuenta> cuenta = cq.from(Cuenta.class);
    // cq.select(cuenta).where(cb.equal(cuenta.get("idCliente"), id));
    //return getEntityManager().createQuery(cq).getResultList();
    // }
    public List<Cuenta> findAllAccountsByClientId(Long idCliente) {

        TypedQuery<Cuenta> consultarCuentasCliente = em.createNamedQuery("Cuenta.findByIdCliente", Cuenta.class);
        consultarCuentasCliente.setParameter("idCliente", idCliente);
        List<Cuenta> result = consultarCuentasCliente.getResultList();
        return result;
    }

    public double saldoDeCuenta(Long idCuenta) {
        TypedQuery<Movimiento> consultarSaldoCuenta = em.createNamedQuery("Movimiento.findByIdCuentaByEstado", Movimiento.class);
        consultarSaldoCuenta.setParameter("idCuenta", idCuenta);
        consultarSaldoCuenta.setParameter("estado", 3);
        List<Movimiento> result = consultarSaldoCuenta.getResultList();
        int i;
        double saldo;
        saldo = 0;
        for (i = 0; i < result.size(); i++) {
            if (result.get(i).getTipo() == 1) {
                saldo = saldo - result.get(i).getImporte();
            } else {
                saldo = saldo + result.get(i).getImporte();
            }
        }
//        TypedQuery<Cuenta> actualizaSaldo = em.createNamedQuery("Cuenta.actualizarSaldo", Cuenta.class);
//        actualizaSaldo.setParameter("id",idCuenta);}
        return saldo;
    }
}
