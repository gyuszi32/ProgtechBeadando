package hu.unideb.inf.pkg.progtechbeadando.Database;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Database{
    private static Document konkatenal(String kifejezesek,File... fajlok) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression compiledExpression = xpath.compile(kifejezesek);
            return konkatenal(compiledExpression, fajlok);
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
    private static Document konkatenal(XPathExpression kifejezesek,File... fajlok) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document base = docBuilder.parse(fajlok[0]);
            Node results = (Node) kifejezesek.evaluate(base,XPathConstants.NODE);
            if (results == null) {
                throw new IOException(fajlok[0] + ": nincs csomópont");
            }
            for (int i = 1; i < fajlok.length; i++) {
                Document konkatenal = docBuilder.parse(fajlok[i]);
                Node kovetkezoEredmeny = (Node) kifejezesek.evaluate(konkatenal,XPathConstants.NODE);
                while (kovetkezoEredmeny.hasChildNodes()) {
                    Node gyerek = kovetkezoEredmeny.getFirstChild();
                    kovetkezoEredmeny.removeChild(gyerek);
                    gyerek = base.importNode(gyerek, true);
                    results.appendChild(gyerek);
                }
            }
            return base;
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    private static void ir(Document doc) {
        try {
            File fájl = new File("src/main/resources/xml/database.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource forras = new DOMSource(doc);
            Result eredmeny = new StreamResult(fájl);
            transformer.transform(forras, eredmeny);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    public static void konkatenalXML() {
        File fajl1 = new File("src/main/resources/xml/seged.xml");
        File fajl2 = new File("src/main/resources/xml/database.xml");
        Document doc = konkatenal("/Projekt/Adatok/Felhasznalok", fajl1, fajl2);
        ir(doc);
    }    
}
