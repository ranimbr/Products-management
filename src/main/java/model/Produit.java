package model;

public class Produit {

    private int id;             // identifiant unique du produit (auto-incrémenté dans MySQL)
    private String nom;         // nom du produit
    private String description; // description du produit
    private double prix;        // prix du produit

    // Constructeur vide nécessaire pour créer des objets dynamiquement
    public Produit() {}

    // Constructeur complet pour créer un produit avec toutes les infos
    public Produit(int id, String nom, String description, double prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    // Getters et Setters → pour accéder ou modifier les attributs
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
}
