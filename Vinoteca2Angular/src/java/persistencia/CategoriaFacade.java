/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import dominio.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mario Torbado
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {
    @PersistenceContext(unitName = "Vinoteca2AngularPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
}
