/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entites.Client;
import javax.ejb.Local;
import outils.CustomException;

@Local
public interface GestionClientLocal {

    public Client createClient(String nom, String prenom, String email, String motDepasse) throws CustomException;
    
}
