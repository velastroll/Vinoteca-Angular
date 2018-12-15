/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import dominio.Preferencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mario Torbado
 */
@Local
public interface PreferenciaFacadeLocal {

    void create(Preferencia preferencia);

    void edit(Preferencia preferencia);

    void remove(Preferencia preferencia);

    Preferencia find(Object id);

    List<Preferencia> findAll();

    List<Preferencia> findRange(int[] range);

    int count();
    
}
