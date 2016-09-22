/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entites.LigneCommande;
import entites.Produit;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author pham
 */
@Local
public interface GestionPanierLocal {

    public void ajouterProduit(Produit produit);

    public Collection<LigneCommande> getListeProduits();

    public int getNombreProduit();

    public void enleverProduit(Produit produit);

    public void supprimerProduit(Produit produit);
    
}
