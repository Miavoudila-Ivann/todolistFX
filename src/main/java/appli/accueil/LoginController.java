package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label yourmail;

    @FXML
    private Label yourmdp;

    @FXML
    private Label titre;

    @FXML
    private Label error;

    @FXML
    private TextField entermail;

    @FXML
    private PasswordField entermdp;

    UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    @FXML
    protected void onConnexionButtonClick() {

        System.out.println(entermail.getText());
        System.out.println(entermdp.getText());

        if (!entermail.getText().isEmpty() && !entermdp.getText().isEmpty()) {

            System.out.println("Connexion !");
            error.setText("Vous vous êtes conncté");
        }else{
            System.out.println("Connexion refusé");
            error.setText("Erreur . Votre email ou mot de passe est incorrecte !");
        }
    }


    @FXML
    protected void onInscriptionButtonClick() throws IOException {
        StartApplication.changeScene("Accueil/Inscription");
        error.setText("Redirection....");
    }

    @FXML
    protected void onmdpoublieButtonClick() {

        error.setText("tu changera pas dsl ;)");
    }


}