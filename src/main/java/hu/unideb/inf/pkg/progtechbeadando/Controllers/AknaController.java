 package hu.unideb.inf.pkg.progtechbeadando.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hu.unideb.inf.pkg.progtechbeadando.Model.Fields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.konfiguracio;
import static hu.unideb.inf.pkg.progtechbeadando.Model.Operation_Fields.indexeles;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;



public class AknaController implements Initializable {
    @FXML
    private  List<Label> labellista;
    @FXML
    private List<Button> gomblista;
    @FXML
    private Label aknak_szama;
    
    Fields[][] tabla;
    
    int talaltbombak=0;
   
    private static final org.slf4j.Logger logom = LoggerFactory.getLogger(GameController.class);
    
    @FXML   
    private void Check(int i,int j){
	if(i>=0 && i<8 && j>=0 && j<8)
		if(!tabla[i][j].checked){
			tabla[i][j].checked = true;
                        tabla[i][j].zaszlo = false;
			if(tabla[i][j].akna==-1){
                                for(int k=0;k<64;k++){
                                     if(labellista.get(k).getText().equals("*")){
                                         gomblista.get(k).setVisible(false);
                                    }
                                }
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/loseend.fxml"));
                                    Scene scene = new Scene(root);
                                    Stage stage = (Stage) aknak_szama.getScene().getWindow();
                                    stage.setScene(scene);
                                    stage.setTitle("Ön vesztett!");
                                    stage.setResizable(false);
                                    stage.show();
                                    logom.info("Játék vége! Ön most vesztett.");
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                        } 
			else if(tabla[i][j].akna==0){
				Check(i,j+1);
				Check(i,j-1);
				Check(i+1,j+1);
				Check(i+1,j-1);
				Check(i-1,j+1);
				Check(i-1,j-1);
				Check(i+1,j);
				Check(i-1,j);
                            }
                int But_index = j + i * 8;
                gomblista.get(But_index).setVisible(false);
                }
    }
    @FXML
    private void katt(MouseEvent e){
        if(e.getButton()==MouseButton.SECONDARY){
            for(int i=0;i<64;i++){
                if(e.getSource()==gomblista.get(i)){
                    Zaszlozas(i/8,i%8);
                    if(labellista.get(i).getText().equals("*") && talaltbombak==10){
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/end.fxml"));
                                    Scene scene = new Scene(root);
                                    Stage stage = (Stage) aknak_szama.getScene().getWindow();
                                    stage.setScene(scene);
                                    stage.setTitle("Ön nyert!");
                                    stage.setResizable(false);
                                    stage.show();
                                    logom.info("Játék vége! Ön nyert!");
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                    }
                }
            }
        }else if(e.getButton()==MouseButton.PRIMARY){
                for(int i=0;i<64;i++){
                if(e.getSource()==gomblista.get(i)){
                    Check(i/8,i%8);
                }
            }
        }
        updateLabel();
    }
    
    private void Zaszlozas(int i,int j){
        int But_index = j + i * 8;
        Button gomb = gomblista.get(But_index);
       
        if(tabla[i][j].zaszlo){
            gomb.setText("");
            tabla[i][j].zaszlo=false;
            if(tabla[i][j].akna==-1){
                talaltbombak--;
            }
        }else{
            gomb.setText("Z");
            tabla[i][j].zaszlo=true;
            if(tabla[i][j].akna==-1){
                talaltbombak++;
            }
        }
    }
    
    private void updateLabel(){
        int zaszlok=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tabla[i][j].zaszlo){
                    zaszlok++;
                }else if(tabla[i][j].checked && tabla[i][j].akna==-1){
                    zaszlok++;                
                }
            }
        }
        aknak_szama.setText(Integer.toString(zaszlok>10 ? 0 : 10-zaszlok));
    }
    
    private void beallitKezdo(){
        int[][] aktualisMatrix = konfiguracio("kezdő");
        indexeles(aktualisMatrix);
        tabla=new Fields[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                tabla[i][j]=new Fields();
                if(aktualisMatrix[i][j]==9){
                    tabla[i][j].akna=-1;
                }else{
                    tabla[i][j].akna=aktualisMatrix[i][j];
                }
            }
        }
        int k=0;
        while(k<64){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
            labellista.get(k).setText("" + aktualisMatrix[i][j] + "");
            k++;}
            }
        }
    }
    
    private void szin(){
        for(int i=0;i<64;i++){
            if(null != labellista.get(i).getText())switch (labellista.get(i).getText()) {
                case "9":
                    labellista.get(i).setStyle("-fx-background-color: #abcdff");
                    labellista.get(i).setText("*");
                    break;
                case "1":
                    labellista.get(i).setStyle("-fx-background-color: #ff0000");
                    break;
                case "2":
                    labellista.get(i).setStyle("-fx-background-color: #c104f0");
                    break;
                case "3":
                    labellista.get(i).setStyle("-fx-background-color: #9ACD32");
                    break;
                case "4":
                    labellista.get(i).setStyle("-fx-background-color: #efff20");
                    break;
                case "0":
                    labellista.get(i).setStyle("-fx-background-color: #afff20");
                    labellista.get(i).setText("");
                default:
                    break;
                }
            }
        }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beallitKezdo();
        szin();
        
        
    }    
}
