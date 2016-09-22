package metiers;

import entites.Client;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import outils.CustomException;

/**
 *
 * @author pham
 */
@Stateless
public class GestionClient implements GestionClientLocal {
    @PersistenceContext(unitName = "mon-magasin-ejbPU")
    private EntityManager em;
    
    @Override
    public Client createClient(String nom, String prenom, String email, String motDepasse) throws CustomException{
        HashMap<String, String> mp = new HashMap<>();
        if(nom == null || nom.trim().isEmpty()){
            mp.put("errNom", "nom obligatoire.");
        }else{
            nom = nom.trim();
            // verifier ensuite qu'un nom est bien un nom
        }
        
        if(prenom == null || prenom.trim().isEmpty()){
            mp.put("errPrenom", "prénom obligatoire.");
        }else{
            prenom = prenom.trim();
            // verifier ensuite qu'un prenom est bien un nom
        }
        
        if(email == null || email.trim().isEmpty()){
            mp.put("errMail", "email obligatoire.");
        }else{
            email = email.trim();
            if(!email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,}")){
               mp.put("errMail", "email invalide."); 
            }
        }
        
        if(motDepasse == null || motDepasse.isEmpty()){
            mp.put("errMdp", "mot de passe obligatoire");
        }else {
            if(motDepasse.length() < 8){
                mp.put("errMdp", "le mot de passe doit avoir au moins 8 caractères");
            }
        }
        
        if(!mp.isEmpty()){
            throw new CustomException(mp, "echec de creation du compte.");
        }
        
        Client c = new Client(nom, prenom, email, motDepasse);
        em.persist(c);
        return c;
    }

    
    
}
