package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Operation;
import model.Produit;

// Servlet qui reçoit toutes les requêtes sur /produit
@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET = afficher la liste
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Operation op = new Operation(); // créer DAO
        request.setAttribute("listeProduits", op.getProduits()); // récupérer liste

        // envoyer la liste à la JSP pour affichage
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // POST = ajouter un produit depuis le formulaire
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // récupérer les valeurs envoyées par le formulaire
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        double prix = Double.parseDouble(request.getParameter("prix")); // convertir en double

        Produit p = new Produit();
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);

        // appeler DAO pour ajouter le produit
        Operation op = new Operation();
        op.ajouterProduit(p);

        // rediriger vers GET pour rafraîchir la liste
        response.sendRedirect("produit");
    }
}
