package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connect;

public class Operation {

    // Ajouter un produit dans la base
    public void ajouterProduit(Produit p) {

        try (Connection conn = Connect.getConnection()) { // récupérer connexion

            // SQL paramétré pour insérer le produit
            String sql = "INSERT INTO produit(nom, description, prix) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql); // préparer la requête
            ps.setString(1, p.getNom());         // remplacer ? par nom
            ps.setString(2, p.getDescription()); // remplacer ? par description
            ps.setDouble(3, p.getPrix());        // remplacer ? par prix

            ps.executeUpdate(); // exécuter la requête (INSERT)

        } catch (SQLException e) {
            e.printStackTrace(); // afficher l'erreur si problème
        }
    }

    // Récupérer tous les produits depuis la base
    public List<Produit> getProduits() {
        List<Produit> liste = new ArrayList<>(); // liste vide pour stocker les produits

        try (Connection conn = Connect.getConnection()) {

            String sql = "SELECT * FROM produit"; // requête SQL pour tout sélectionner
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(); // exécuter SELECT

            while (rs.next()) { // parcourir toutes les lignes du résultat
                // créer un objet Produit pour chaque ligne
                Produit p = new Produit(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDouble("prix")
                );
                liste.add(p); // ajouter à la liste
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste; // renvoyer la liste complète
    }
}
