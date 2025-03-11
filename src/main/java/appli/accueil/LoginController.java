package appli.accueil;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button connexion;

    @FXML
    private TextField email;

    @FXML
    private Label erreur;

    @FXML
    private Button inscription;

    @FXML
    private PasswordField mdp;

    @FXML
    private Button mdpOublier;

    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText1;

    @FXML
    void connexionButtonClick(ActionEvent event) {

    }

    @FXML
    void emailTextField(ActionEvent event) {

    }

    @FXML
    void inscriptionButtonClick(ActionEvent event) {
        Application.changeScene;
    }

    @FXML
    void mdpOublierButtonClick(ActionEvent event) {

    }

    @FXML
    void mdpTextField(ActionEvent event) {

    }

}
