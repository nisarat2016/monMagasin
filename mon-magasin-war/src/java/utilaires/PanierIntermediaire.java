package utilaires;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import metiers.GestionPanierLocal;


public class PanierIntermediaire implements Serializable {
    
    private GestionPanierLocal gestionPanier;

    public PanierIntermediaire() {
        gestionPanier = lookupGestionPanierLocal();
    }

    public GestionPanierLocal getGestionPanier() {
        return gestionPanier;
    }

    public void setGestionPanier(GestionPanierLocal gestionPanier) {
        this.gestionPanier = gestionPanier;
    } 
    private GestionPanierLocal lookupGestionPanierLocal() {
        try {
            Context c = new InitialContext();
            return (GestionPanierLocal) c.lookup("java:global/mon-magasin/mon-magasin-ejb/GestionPanier!metiers.GestionPanierLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
