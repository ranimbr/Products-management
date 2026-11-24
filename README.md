# 📦 Products Management

Application web de **gestion de produits** développée en **Java EE** (Servlet + JSP) avec une base de données **MySQL** via JDBC. Elle permet d'ajouter un produit grâce à un formulaire et d'afficher la liste de tous les produits enregistrés.

---

## 📑 Sommaire

- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Architecture du projet](#-architecture-du-projet)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Lancer l'application](#-lancer-lapplication)
- [Utilisation](#-utilisation)
---

## ✨ Fonctionnalités

- ➕ **Ajouter un produit** (nom, description, prix) via un formulaire
- 📋 **Afficher la liste des produits** dans un tableau (ID, nom, description, prix)
- 🗄️ Enregistrement des données dans une base **MySQL**
- 🔒 Requêtes SQL **préparées** (`PreparedStatement`) pour l'insertion et la lecture

---

## 🧰 Technologies utilisées

| Domaine | Technologie |
| --- | --- |
| Langage | Java 22 |
| Web | Servlet 4.0 (`javax.servlet`) et JSP |
| Serveur d'application | Apache Tomcat 9 |
| Base de données | MySQL |
| Accès aux données | JDBC, MySQL Connector/J 9.4.0 |
| IDE | Eclipse (projet Dynamic Web Project) |

---

## 🏗️ Architecture du projet

Le projet suit une organisation de type **MVC** :

```
Products-management/
├── src/main/java/
│   ├── connexion/
│   │   └── Connect.java          # Connexion JDBC à la base MySQL
│   ├── model/
│   │   ├── Produit.java          # Entité Produit (id, nom, description, prix)
│   │   └── Operation.java        # Accès aux données : ajouterProduit(), getProduits()
│   └── controller/
│       └── ProduitServlet.java   # Servlet /produit (doGet : liste, doPost : ajout)
└── src/main/webapp/
    ├── index.jsp                 # Vue : formulaire d'ajout + tableau des produits
    └── WEB-INF/lib/
        └── mysql-connector-j-9.4.0.jar
```

**Fonctionnement :**

1. `GET /produit` : le servlet récupère la liste via `Operation.getProduits()`, la place dans la requête (`listeProduits`) puis la transmet à `index.jsp`.
2. `POST /produit` : le servlet lit les champs du formulaire, appelle `Operation.ajouterProduit()` puis redirige vers `GET /produit` pour rafraîchir la liste.

---

## ✅ Prérequis

- [JDK](https://www.oracle.com/java/technologies/downloads/) 22 (ou une version compatible avec la configuration de ton Eclipse)
- [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/)
- [Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi)
- [XAMPP](https://www.apachefriends.org/) (fournit **MySQL** et **phpMyAdmin**)

> ⚠️ Le projet utilise `javax.servlet` : il faut **Tomcat 9** (Servlet 4.0). Tomcat 10 et supérieur utilisent `jakarta.servlet` et ne sont pas compatibles sans migration du code.

---

## 🚀 Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/ranimbr/Products-management.git
```

### 2. Démarrer MySQL avec XAMPP et créer la base de données

1. Ouvre le **XAMPP Control Panel**.
2. Clique sur **Start** à côté de **MySQL** (le module Apache n'est pas nécessaire pour lancer le projet, mais il faut qu'il soit démarré pour utiliser phpMyAdmin).
3. Ouvre **phpMyAdmin** : [http://localhost/phpmyadmin](http://localhost/phpmyadmin)
4. Clique sur l'onglet **SQL**, colle le script ci-dessous, puis clique sur **Exécuter** :

```

> 💡 Par défaut, XAMPP utilise l'utilisateur `root` **sans mot de passe** sur le port `3306`, ce qui correspond à la configuration de `Connect.java`.

### 3. Configurer la connexion

Les paramètres de connexion se trouvent dans `src/main/java/connexion/Connect.java` :

```java
private static final String URL = "jdbc:mysql://localhost:3306/produitdb";
private static final String USER = "root";
private static final String PASSWORD = "";
```

Avec XAMPP par défaut, tu n'as rien à modifier. Adapte `USER`, `PASSWORD` ou le port seulement si tu as changé la configuration de MySQL.

### 4. Importer le projet dans Eclipse

1. `File` → `Import` → `Existing Projects into Workspace`
2. Sélectionne le dossier cloné `Products-management`
3. Vérifie que le runtime **Apache Tomcat v9.0** est bien associé au projet
   (`Clic droit sur le projet` → `Properties` → `Targeted Runtimes`)
4. Le driver MySQL est déjà présent dans `src/main/webapp/WEB-INF/lib/mysql-connector-j-9.4.0.jar`

---

## ▶️ Lancer l'application

1. Démarre **MySQL** depuis le **XAMPP Control Panel**.
2. Dans Eclipse : `Clic droit sur le projet` → `Run As` → `Run on Server` → choisis **Tomcat 9** (le Tomcat installé séparément, pas celui de XAMPP).
3. Ouvre ton navigateur sur :

```
http://localhost:8080/produittp/produit
```

> 💡 Passe bien par l'URL `/produit` (et non directement par `index.jsp`) : c'est le servlet qui charge la liste des produits avant d'afficher la page.
> Le nom du contexte (`produittp`) correspond au nom du projet Eclipse ; adapte l'URL si ton projet porte un autre nom.

### 🛠️ Problèmes fréquents

| Problème | Solution |
| --- | --- |
| La liste reste vide ou une erreur de connexion apparaît dans la console | Vérifie que **MySQL est bien démarré** dans XAMPP et que la base `produitdb` et la table `produit` existent |
| MySQL ne démarre pas dans XAMPP | Le port `3306` est probablement déjà utilisé par un autre service MySQL : arrête-le ou change le port dans XAMPP (et dans `Connect.java`) |
| Erreur `Access denied for user 'root'` | Mets à jour `USER` / `PASSWORD` dans `Connect.java` avec les identifiants de ton MySQL |
| Erreur `ClassNotFoundException: com.mysql.cj.jdbc.Driver` | Vérifie que `mysql-connector-j-9.4.0.jar` est bien dans `src/main/webapp/WEB-INF/lib/` |

---

## 🖱️ Utilisation

1. Renseigne le **nom**, la **description** et le **prix** du produit.
2. Clique sur **Ajouter**.
3. Le produit apparaît dans le tableau « Liste des Produits ».

---

