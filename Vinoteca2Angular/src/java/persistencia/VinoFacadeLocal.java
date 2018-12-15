/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import dominio.Vino;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mario Torbado
 */
@Local
public interface VinoFacadeLocal {

    void create(Vino vino);

    void edit(Vino vino);

    void remove(Vino vino);

    Vino find(Object id);

    List<Vino> findAll();

    List<Vino> findRange(int[] range);

    int count();
    
}
