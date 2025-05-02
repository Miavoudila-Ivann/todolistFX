package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import model.Utilisateur;

public class UtilisateurRepository {
    private Connection cnx;

    public UtilisateurRepository() {
        this.cnx = Database.getConnection();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mdp) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }


    public void getUtilisateurParEmail(Utilisateur utilisateur) {
        String sql = "SELECT * FROM utilisateurs WHERE email = this.email";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            System.out.println("Utilisateur trouver avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
        }
    }






    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, mdp = ? WHERE email = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.executeUpdate();
            System.out.println("Utilisateur a ete modifier avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }

    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs"; // La requête pour récupérer tous les utilisateurs

        try {
            // Préparation de la requête SQL
            PreparedStatement stmt = cnx.prepareStatement(sql);

            // Exécution de la requête
            ResultSet rs = stmt.executeQuery();

            // Parcours des résultats pour créer des objets Utilisateur
            while (rs.next()) {
                // Création d'un utilisateur à partir des résultats
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("idUtilisateur"), // Récupération de l'ID
                        rs.getString("nom"),        // Récupération du nom
                        rs.getString("prenom"),     // Récupération du prénom
                        rs.getString("email"),      // Récupération de l'email
                        rs.getString("mdp")         // Récupération du mot de passe
                );

                // Ajout de l'utilisateur à la liste
                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }

        return utilisateurs; // Retourne la liste des utilisateurs
    }




    public void supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM `utilisateur` WHERE  email = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }



}