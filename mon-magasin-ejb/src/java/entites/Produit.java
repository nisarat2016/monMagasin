
package entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
    @NamedQuery(name = "entites.Produit.findByReference", query = "Select p from Produit p where p.reference= :paramReference"),
    @NamedQuery(name = "entites.Produit.findAll", query = "select p  from Produit p")
})
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String reference;
    
    private String nom;
    private Integer stock;
    private Float prixHT;
    
    @ManyToOne
    private Taxe tva;
    
    @OneToMany(mappedBy = "produit")
    private Collection<LigneCommande> lignesCommande;

    public Produit() {
        lignesCommande = new ArrayList<>();
    }

    public Produit(String reference, String nom, Integer stock, Float prixHT) {
        this();
        this.reference = reference;
        this.nom = nom;
        this.stock = stock;
        this.prixHT = prixHT;
    }

    public Produit(String reference, String nom, Integer stock, Float prixHT, Taxe tva) {
        this();
        this.reference = reference;
        this.nom = nom;
        this.stock = stock;
        this.prixHT = prixHT;
        this.tva = tva;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Taxe getTva() {
        return tva;
    }

    public void setTva(Taxe tva) {
        this.tva = tva;
    }
    
    public float getPrixTTC(){
        return prixHT*( 1 + tva.getTaux()/100);
    }

    public Collection<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(Collection<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }
    
    
    
    @Override
    public String toString() {
        return "reference=" + reference + ", nom=" + nom ;
    }  
}
