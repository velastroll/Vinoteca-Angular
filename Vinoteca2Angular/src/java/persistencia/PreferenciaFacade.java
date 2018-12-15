/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import dominio.Preferencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mario Torbado
 */
@Stateless
public class PreferenciaFacade extends AbstractFacade<Preferencia> implements PreferenciaFacadeLocal {
    @PersistenceContext(unitName = "Vinoteca2AngularPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreferenciaFacade() {
        super(Preferencia.class);
    }
    
}
