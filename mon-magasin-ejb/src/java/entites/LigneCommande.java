
package entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LigneCommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qte;
    
    @ManyToOne
    private Produit produit;
    
    public LigneCommande() {
    }

    public LigneCommande(Produit produit) {
        this.produit = produit;
        qte = 1;
    }

    public LigneCommande(int qte, Produit produit) {
        this.qte = qte;
        this.produit = produit;
    }
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    
    public float getPrixLigneHT(){
        return produit.getPrixHT() * qte;
    }
    
    public  float getPrixLigneTTC(){
        return produit.getPrixTTC() * qte ;
    }
    
    
    public String getReference() {
        return produit.getReference();
    }

    public String getNom() {
        return produit.getNom();
    }

    public Integer getStock() {
        return produit.getStock();
    }

    

    public Float getPrixUnitaireHT() {
        return produit.getPrixHT();
    }

   public Float getPrixUnitaireTTC() {
        return produit.getPrixTTC();
    }

    public Float getTva() {
        return produit.getTva().getTaux();
    }

    

    @Override
    public String toString() {
        return produit+" qte = "+ qte;
    }
    
}
