/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.pkg.progtechbeadando.Database;


import hu.unideb.inf.pkg.progtechbeadando.Controllers.LoginController;
import hu.unideb.inf.pkg.progtechbeadando.Controllers.RegistrationController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Juhász Gyula
 */
public class TempXML {
  
    public static regUser adat;
    private boolean hibaVan=false;
    private boolean hibaVan2=false;
    List<String> felhasznalo_nevek=new ArrayList<>();
    public Validation valid= Validation.getPeldany();

    public void setHibaVan(boolean hibaVan) {
        this.hibaVan = hibaVan;
    }
    
    public boolean isHibaVan() {
        return hibaVan;
    }

    public void setAdat(regUser adat) {
        this.adat = adat;
    }
    
      
    public void tempXML(){            
            try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc= db.newDocument();
            Element Projekt = doc.createElement("Projekt");
            doc.appendChild(Projekt);
            
            Element adatok = doc.createElement("Adatok");
            Projekt.appendChild(adatok);
            
            Element felhasznalok = doc.createElement("Felhasznalok");
            adatok.appendChild(felhasznalok);
          
            Element felhasznaloelem = doc.createElement("Felhasznalo");
            Attr id =doc.createAttribute("id");
            id.setValue(adat.getUserfield());
            felhasznaloelem.setAttributeNode(id);
            felhasznalok.appendChild(felhasznaloelem);
            
            Element jelszoelem = doc.createElement("Jelszo");
            jelszoelem.appendChild(doc.createTextNode(adat.getPasswordfield()));
            felhasznaloelem.appendChild(jelszoelem);
            
            Element jelszo2elem = doc.createElement("Jelszo2");
            jelszo2elem.appendChild(doc.createTextNode(adat.getPasswordfield2()));
            felhasznaloelem.appendChild(jelszo2elem);
            
            Element Kar = doc.createElement("Kar");
            Kar.appendChild(doc.createTextNode(adat.getKarfield()));
            felhasznaloelem.appendChild(Kar);
            
            Element Szak = doc.createElement("Szak");
            Szak.appendChild(doc.createTextNode(adat.getSzakfield()));
            felhasznaloelem.appendChild(Szak);
            
            Element Ev = doc.createElement("Evfolyam");
            Ev.appendChild(doc.createTextNode(adat.getEvfield()));
            felhasznaloelem.appendChild(Ev);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource forras = new DOMSource(doc);
            StreamResult eredmeny = new StreamResult(new File("src/main/resources/xml/seged.xml"));
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
            t.transform(forras, eredmeny);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readXML(){
        try {
            hibaVan=false;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            File bemenet= new File("src/main/resources/xml/database.xml");
            Document doc= db.parse(bemenet);
            doc.getDocumentElement().normalize();
            NodeList n1 = doc.getElementsByTagName("Felhasznalo");
            valid.valid_reg(n1);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readLoginXML(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            File bemenet= new File("src/main/resources/xml/database.xml");
            Document doc= db.parse(bemenet);
            doc.getDocumentElement().normalize();
            NodeList n1 = doc.getElementsByTagName("Jelszo");
            NodeList n2 = doc.getElementsByTagName("Felhasznalo");
            valid.valid_log(n1,n2);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}


