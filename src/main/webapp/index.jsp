<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Produit" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion de Produits</title>
</head>
<body>

<h2>Ajouter un Produit</h2>

<!-- Formulaire POST → envoie vers ProduitServlet -->
<form action="produit" method="post">
    Nom: <input type="text" name="nom"><br><br>
    Description: <input type="text" name="description"><br><br>
    Prix: <input type="text" name="prix"><br><br>
    <input type="submit" value="Ajouter">
</form>

<hr>

<h2>Liste des Produits</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Prix</th>
    </tr>

<%
    // Récupérer la liste envoyée par le Servlet
    List<Produit> liste = (List<Produit>) request.getAttribute("listeProduits");

    // Boucle pour afficher chaque produit
    if (liste != null) {
        for (Produit p : liste) {
%>
<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getNom() %></td>
    <td><%= p.getDescription() %></td>
    <td><%= p.getPrix() %></td>
</tr>
<%
        }
    }
%>

</table>
</body>
</html>
