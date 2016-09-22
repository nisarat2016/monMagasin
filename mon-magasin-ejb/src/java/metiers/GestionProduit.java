package metiers;

import entites.Produit;
import entites.Taxe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionProduit implements GestionProduitLocal {
    
    @PersistenceContext(unitName = "mon-magasin-ejbPU")
    private EntityManager em;

    @Override
    public Produit creerProduit(String reference, String nom, Integer stock,Float prix, Taxe tva){
        // faire le traitement des données
        tva = em.merge(tva);
        Produit p = new Produit(reference, nom, stock, prix);
        p.setTva(tva);
        em.persist(p);
        return p;
    }
    
    @Override
    public Taxe creerTaxe(String description, Float taux){
        // traiter les données
        Taxe t = new Taxe(taux, description);
        em.persist(t);
        return t;
    }
    
    @Override
    public List<Produit> selectAllProduits(){
        Query qr = em.createNamedQuery("entites.Produit.findAll");
        return qr.getResultList();     
    }
    
    @Override
    public  Produit selectProduitByReference(String reference){
//        Query qr = em.createNamedQuery("entites.Produit.findByReference");
//        qr.setParameter("paramReference", reference);
//        Produit p = (Produit) qr.getSingleResult();
//        return p;
        return em.find(Produit.class, reference);
    }
   
}
