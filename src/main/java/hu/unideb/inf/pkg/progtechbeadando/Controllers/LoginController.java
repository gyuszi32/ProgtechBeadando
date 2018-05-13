package hu.unideb.inf.pkg.progtechbeadando.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static hu.unideb.inf.pkg.progtechbeadando.Controllers.RegistrationController._xml;
import hu.unideb.inf.pkg.progtechbeadando.Database.Validation;
import hu.unideb.inf.pkg.progtechbeadando.Database.loginUser;
import hu.unideb.inf.pkg.progtechbeadando.Database.loginUserBuilder;
import hu.unideb.inf.pkg.progtechbeadando.Database.loginUserBuilderImpl;

public class LoginController implements Initializable {
    @FXML
    private TextField USER;
    @FXML
    private PasswordField PASSWORD;
    @FXML
    private Label hiba2;
    
    private Validation valid= Validation.getPeldany();    
    private static final org.slf4j.Logger logom = LoggerFactory.getLogger(LoginController.class);
    
    private loginUser construct(){
        loginUserBuilder builder=new loginUserBuilderImpl();
        return builder.setLogUser(USER.getText()).setLogPassword(PASSWORD.getText()).build();
    }
    
    @FXML
    private void kilepes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle("Kezdőlap");
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void bejelentkezes(ActionEvent event){
        try {
            valid.setLogadatok(construct());
            _xml.readLoginXML();
            if(valid.isHibaVan2()==false){
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/succes.fxml"));
                Scene scene = new Scene(root);
                Stage stage= new Stage(StageStyle.DECORATED);
                stage.setScene(scene);
                stage.setTitle("Gratulálunk");
                stage.setResizable(false);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                logom.info("Sikeres bejelentkezés!");
            }else{
                hiba2.setVisible(true);
                logom.warn("Nincs ilyen felhasználó, vagy nem érvényes jelszó!");
                valid.setHibaVan2(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
