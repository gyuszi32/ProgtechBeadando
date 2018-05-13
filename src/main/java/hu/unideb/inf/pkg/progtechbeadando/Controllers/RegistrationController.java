package hu.unideb.inf.pkg.progtechbeadando.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import hu.unideb.inf.pkg.progtechbeadando.Database.regUser;
import hu.unideb.inf.pkg.progtechbeadando.Database.regUserBuilderImpl;
import hu.unideb.inf.pkg.progtechbeadando.Database.TempXML;
import hu.unideb.inf.pkg.progtechbeadando.Database.regUserBuilder;

public class RegistrationController implements Initializable {
    @FXML
    public TextField felhasznalo;
    @FXML
    public PasswordField jelszo;
    @FXML
    public PasswordField jelszo2;
    @FXML
    public TextField szak;
    @FXML
    public TextField kar;
    @FXML
    public TextField ev;
    @FXML
    public Label hiba;
    
    boolean hibaVan=false;
    
    public static TempXML _xml = new TempXML();
    
    
    public regUser construct(){
                regUserBuilder builder= new regUserBuilderImpl();
      return builder.setUserfield(felhasznalo.getText()).setPasswordfield(jelszo.getText()).setPasswordfield2(jelszo2.getText()).setKarfield(kar.getText()).setSzakfield(szak.getText()).setEvfield(ev.getText()).build();
      /* alap seged=new alap();
       seged.setUserfield(felhasznalo.getText());
       seged.setPasswordfield(jelszo.getText());
       seged.setPasswordfield2(jelszo2.getText());
       seged.setSzakfield(szak.getText());
       seged.setKarfield(kar.getText());
       seged.setEvfield(ev.getText());
       return seged;*/
    }
    
    private static final org.slf4j.Logger logom = LoggerFactory.getLogger(RegistrationController.class);
    
    @FXML
    private void elfogad(ActionEvent event){
        try {
           _xml.setAdat(construct());
           _xml.readXML();
            if(!_xml.isHibaVan()){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/registrationOK.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage= new Stage(StageStyle.DECORATED);
                    stage.setScene(scene);
                    stage.setTitle("Gratulálunk!");
                    stage.setResizable(false);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    logom.info("Sikeres regisztráció!");
                } catch (IOException ex) {
                    Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                hiba.setVisible(true);
                hiba.setText("Valami nem stimmel!");
                logom.warn("Nem megegyező jelszavak, vagy nem megfelelő felhasználónév!!!");
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    @FXML
    private void vissza(ActionEvent event) {
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
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

}
