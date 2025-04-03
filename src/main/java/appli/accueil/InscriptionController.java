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

        System.out.println(getemail.getText());
        System.out.println(getmdp.getText());

        if (!getemail.getText().isEmpty() && !getmdp.getText().isEmpty() && !getconfirm.getText().isEmpty() && !getnom.getText()
                .isEmpty() && !getprenom.getText().isEmpty()) {
            Utilisateur utilisateur =  new Utilisateur(getnom.getText(),getprenom.getText(),getemail.getText(),getmdp.getText());
            utilisateurRepository.ajouterUtilisateur(utilisateur);
            System.out.println("Inscription OK !");
            erreur.setText("Inscription réussie !");
        }else{
            System.out.println("Inscription refusé");
            erreur.setText("Erreur veuillez replir tous les champs !");
        }
    }

    @FXML
    protected void onBretourButtonClick() throws IOException {
        StartApplication.changeScene("Accueil/Login");
        System.out.println("redirection vers page de connexion...");
        erreur.setText("Redirection reussi");
    }
}
