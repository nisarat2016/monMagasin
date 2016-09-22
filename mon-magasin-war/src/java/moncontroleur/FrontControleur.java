package moncontroleur;

import entites.Client;
import entites.Produit;
import entites.Taxe;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionClientLocal;
import metiers.GestionPanierLocal;
import metiers.GestionProduitLocal;
import outils.CustomException;
import utilaires.PanierIntermediaire;

@WebServlet(name = "FrontControleur", urlPatterns = {"/FrontControleur"})
public class FrontControleur extends HttpServlet {
    
    @EJB
    private GestionProduitLocal gestionProduit;
    
    @EJB
    private GestionClientLocal gestionClient;

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        String prefixe ="/WEB-INF/jsp/";
        String suffixe = ".jsp";        
        String page = "home";
        
        String section = request.getParameter("section");
        String action = request.getParameter("action");
        
        if(session.getAttribute("panierIntermediaire") == null){           
            session.setAttribute("panierIntermediaire", new PanierIntermediaire());
        }
        PanierIntermediaire pi = (PanierIntermediaire) session.getAttribute("panierIntermediaire");
        GestionPanierLocal gestionPanier = pi.getGestionPanier();
        
        if(session.getAttribute("panier") == null){
            session.setAttribute("panier", gestionPanier.getListeProduits());
        }
        
        
        if("menu-main".equalsIgnoreCase(section)){
            int nbProduit = gestionPanier.getNombreProduit();
            if(nbProduit <= 1){
                request.setAttribute("infoPanier", nbProduit + " article");
            } else {
                request.setAttribute("infoPanier", nbProduit + " articles");
            }
            page = "menus/menu-main";
        }
        
        if("vers-formulaire-client".equalsIgnoreCase(section)){
            page = "formulaire-client";
        }
        
        if("catalogue".equalsIgnoreCase(section)){
            page="catalogue";
        }
        
        
        if("operation-panier".equalsIgnoreCase(section)){
            if("ajouter".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                Produit p = gestionProduit.selectProduitByReference(ref);
                gestionPanier.ajouterProduit(p);
            }
            if("enlever".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                Produit p = gestionProduit.selectProduitByReference(ref);
                gestionPanier.enleverProduit(p);
            }
            if("supprimer".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                Produit p = gestionProduit.selectProduitByReference(ref);
                gestionPanier.supprimerProduit(p);
            }
            String referer = request.getHeader("Referer"); // page précédente
            if (referer.contains("catalogue")) {
                response.sendRedirect("FrontControleur?section=catalogue");
            } else {
                response.sendRedirect("FrontControleur?section=panier");
            }
            
            
            return;
        }
        
        
        if("operation-panier".equalsIgnoreCase(section)){
            if("ajouter".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                Produit p = gestionProduit.selectProduitByReference(ref);
                gestionPanier.ajouterProduit(p);
            }
            
            if("enlever".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                Produit p = gestionProduit.selectProduitByReference(ref);
                gestionPanier.enleverProduit(p);
            }
            
            response.sendRedirect("FrontControleur?section=catalogue");
            return;
        }
        
        
        if("afficher-panier".equalsIgnoreCase(section)){
            page = "includes/panier";
        }
        
        if("panier".equalsIgnoreCase(section)){
            page = "detail-panier";
        }
        
        if("liste-produit".equalsIgnoreCase(section)){
            List<Produit> lp = gestionProduit.selectAllProduits();
            request.setAttribute("produits", lp);
            page = "includes/liste-produits";
        }
        
        
        if("creer-donnees".equalsIgnoreCase(section)){
            // création d'un jeu de teste en dur
            Taxe t01 = gestionProduit.creerTaxe("taxe ordinaire", 20F);
            Produit p01 = gestionProduit.creerProduit("REF-001-AAA", "produit01", 5, 10.3F, t01);
            Produit p02 = gestionProduit.creerProduit("REF-002-BBB", "produit02", 4, 12.6F, t01);
            Produit p03 = gestionProduit.creerProduit("REF-003-CCC", "produit03", 8, 15.9F, t01);
            
            String msg = "Un jeu de test a été crée.";
            request.setAttribute("msg", msg);
            page = "home";
        }
        
        if("inscription".equalsIgnoreCase(section)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String mail = request.getParameter("mail");
            String mdp = request.getParameter("mdp");
            try {
                Client c = gestionClient.createClient(nom, prenom, mail, mdp);
                String msg = "L'utilisateur "+c+" a bien été créé !";
                request.setAttribute("msg", msg);
                page = "home";
            } catch (CustomException ex) {
                page = "formulaire-client";
                request.setAttribute("erreur", ex.getMessage());
                HashMap<String, String> mp = ex.getMessages();
                for(String cle : mp.keySet()){
                    request.setAttribute(cle, mp.get(cle));
                }
                request.setAttribute("nomF", nom);
                request.setAttribute("prenomF", prenom);
                request.setAttribute("emailF", mail);
            }            
        }
        
        page = response.encodeURL(prefixe+page+suffixe);
        getServletContext().getRequestDispatcher(page).include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
