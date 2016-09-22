package metiers;

import entites.LigneCommande;
import entites.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;


@Stateful
public class GestionPanier implements GestionPanierLocal {
//    @EJB
//    private GestionProduitLocal gestionProduit;
    
    
    private HashMap<String, LigneCommande> panier;
       
    @PostConstruct
    public void init(){
        panier = new HashMap();
    }
    
//    public void ajouterProduit(String reference){
//        Produit p = gestionProduit.selectProduitByReference(reference);
//        ajouterProduit(p);
//    }
    
    @Override
    public void enleverProduit (Produit produit){
        if(produit == null){
            return;
        }
        String ref = produit.getReference();
        if(panier.containsKey(ref)){
            LigneCommande lg = panier.get(ref);
            int qte = lg.getQte() - 1;
            if(qte < 0){
                qte = 0;
            }
            lg.setQte(qte);
            if(qte == 0){
                panier.remove(ref, lg);
            }
        }
    }
    
    
    @Override
    public void supprimerProduit(Produit produit) {
        if (produit == null) {
            return;
        }
        String ref = produit.getReference();
        if (panier.containsKey(ref)) {
            LigneCommande lg = panier.get(ref);
            panier.remove(ref, lg);
        }
    }
    
    @Override
    public void ajouterProduit(Produit produit){
        if(produit == null){
            return ;
        }
        
        String ref = produit.getReference();
        if(panier.containsKey(ref)){
            LigneCommande lg = panier.get(ref);
            int qte = lg.getQte() + 1;
            if(qte > produit.getStock()){
                qte = produit.getStock();
            }
            lg.setQte(qte);
        } else {
            LigneCommande lg = new LigneCommande(produit);
            panier.put(ref, lg);
        }
    }
       
    @Override
    public Collection<LigneCommande> getListeProduits(){
        return panier.values();
    }
    
    @Override
    public int getNombreProduit(){
        int somme = 0;
        Collection<LigneCommande> lp = getListeProduits();
        for(LigneCommande l : lp){
            somme += l.getQte();
        }
        return somme;
    }
    
    
}
