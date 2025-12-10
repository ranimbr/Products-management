package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    // Adresse de la base de données MySQL sur ton PC
    private static final String URL = "jdbc:mysql://localhost:3306/produitdb";
    
    // Identifiants MySQL (root par défaut sur XAMPP/WAMP)
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Méthode pour obtenir la connexion à la base
    public static Connection getConnection() {
        Connection conn = null; // objet Connection, initialisé à null
        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Créer la connexion avec l'URL + user + password
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            // Affiche l’erreur si la connexion échoue
            e.printStackTrace();
        }
        // Retourne la connexion (null si échec)
        return conn;
    }
}
