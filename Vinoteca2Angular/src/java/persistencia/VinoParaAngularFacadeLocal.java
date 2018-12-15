/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import dominio.VinoParaAngular;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mario Torbado
 */
@Local
public interface VinoParaAngularFacadeLocal {

    void create(VinoParaAngular vinoParaAngular);

    void edit(VinoParaAngular vinoParaAngular);

    void remove(VinoParaAngular vinoParaAngular);

    VinoParaAngular find(Object id);

    List<VinoParaAngular> findAll();

    List<VinoParaAngular> findRange(int[] range);

    int count();
    
}
