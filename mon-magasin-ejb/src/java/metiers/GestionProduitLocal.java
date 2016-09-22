/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entites.Produit;
import entites.Taxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pham
 */
@Local
public interface GestionProduitLocal {

    public Produit creerProduit(String reference, String nom, Integer stock, Float prix, Taxe tva);

    public Taxe creerTaxe(String description, Float taux);

    public List<Produit> selectAllProduits();

    public Produit selectProduitByReference(String reference);
    
}
