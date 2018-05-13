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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class rulesController implements Initializable {

    @FXML
    private Label jatek2048;
    @FXML 
    private Label jatekakna;
    
    @FXML
    private void vissza(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/succes.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle("Szabály");
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(SuccesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        jatek2048.setText("A játék célja az, hogy a 2-es számból előállítjuk a 2048-t, úgy hogy csak azonos számokat adhatunk össze. Azonos számot csak azonos számmal húzhatsz össze.");
        jatekakna.setText("A játék célja az, megpróbáljuk a logikánkat használva kiszámolni, hogy a 64 mező melyikén található bomba. Vigyázat: 10 bombát rejtettünk el a mezőkön. Sok sikert hozzá!");
    }    
}
