package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;

public class InscriptionController {
    @FXML
    private Label inscription;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label mdp;
    @FXML
    private Label confirm;
    @FXML
    private PasswordField getmdp;
    @FXML
    private PasswordField getconfirm;
    @FXML
    private TextField getemail;
    @FXML
    private TextField getnom;
    @FXML
    private TextField getprenom;
    @FXML
    private Label erreur;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    private  BCryptPasswordEncoder;

    @FXML
    public void onInscriptionButtonClick(ActionEvent event) throws IOException {
        // Récupérer les informations du formulaire
        String email = getemail.getText();
        String mdp = getmdp.getText();
        String confirmMdp = getconfirm.getText();
        String nom = getnom.getText();
        String prenom = getprenom.getText();

        // Vérification que tous les champs sont remplis
        if (!email.isEmpty() && !mdp.isEmpty() && !confirmMdp.isEmpty() && !nom.isEmpty() && !prenom.isEmpty()) {

            // Vérifier si les deux mots de passe correspondent
            if (!mdp.equals(confirmMdp)) {
                System.out.println("Les mots de passe ne correspondent pas");
                erreur.setText("Les mots de passe ne correspondent pas !");
                return;
            }

            // Vérifier si un utilisateur existe déjà avec ce courriel
            if (utilisateurRepository.utilisateurExistant(email)) {
                System.out.println("Un utilisateur avec cet email existe déjà");
                erreur.setText("Un utilisateur avec cet email existe déjà !");
                return;
            }

            // Hacher le mot de passe avant de l'enregistrer
            String motDePasseHache = hashPassword(mdp);

            // Créer un nouvel utilisateur avec les informations fournies
            Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasseHache);

            // Appeler la méthode d'enregistrement de l'utilisateur dans le repository
            utilisateurRepository.ajouterUtilisateur(utilisateur);

            System.out.println("Inscription OK !");
            erreur.setText("Inscription réussie !");

        } else {
            // Si un champ est vide, afficher une erreur
            System.out.println("Inscription refusée : champs vides");
            erreur.setText("Erreur : veuillez remplir tous les champs !");
        }
    }

    // Fonction pour hacher le mot de passe avec SHA-256
    private String hashPassword(String motDePasse) {
        try {
            // Utiliser l'algorithme SHA-256 pour le hachage
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(motDePasse.getBytes());

            // Convertir le tableau de bytes en une chaîne hexadécimale
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();  // Retourner le mot de passe haché
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;  // Si l'algorithme est introuvable, retourner null
        }
    }

    @FXML
    protected void onBretourButtonClick() throws IOException {
        StartApplication.changeScene("Accueil/Login");
        System.out.println("redirection vers page de connexion...");
        erreur.setText("Redirection reussi");
    }
}
