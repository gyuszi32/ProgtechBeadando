package hu.unideb.inf.pkg.progtechbeadando.Database;

import hu.unideb.inf.pkg.progtechbeadando.Controllers.LoginController;
import static hu.unideb.inf.pkg.progtechbeadando.Database.Database.konkatenalXML;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NodeList;
import static hu.unideb.inf.pkg.progtechbeadando.Controllers.RegistrationController._xml;
import static hu.unideb.inf.pkg.progtechbeadando.Database.TempXML.adat;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Juhász Gyula
 */
public class Validation {

    List<String> felhasznalo_nevek = new ArrayList<>();
    List<String> jelszavak = new ArrayList();
    List<String> user_nevek = new ArrayList();
    public static loginUser logadatok;
    private static final org.slf4j.Logger logom = LoggerFactory.getLogger(LoginController.class);

    private Validation() {
    }
    private static Validation peldany;

    public static Validation getPeldany() {
        if (peldany == null) {
            peldany = new Validation();
        }
        return peldany;
    }

    public void setLogadatok(loginUser logadatok) {
        this.logadatok = logadatok;
    }

    public boolean hibaVan2 = false;

    public boolean isHibaVan2() {
        return hibaVan2;
    }

    public void setHibaVan2(boolean hibaVan2) {
        this.hibaVan2 = hibaVan2;
    }

    public void valid_reg(NodeList n1) {
        for (int i = 0; i < n1.getLength(); i++) {
            String felhasznalo_id = n1.item(i).getAttributes().getNamedItem("id").getNodeValue();
            felhasznalo_nevek.add(felhasznalo_id);
            if (felhasznalo_nevek.contains(adat.getUserfield())) {
                _xml.setHibaVan(true);
            }
        }
        if (adat.getUserfield().length() < 5 || adat.getPasswordfield().length() < 5) {
            _xml.setHibaVan(true);
        } else {
            if (adat.getPasswordfield().equals(adat.getPasswordfield2()) && _xml.isHibaVan() == false) {
                _xml.tempXML();
                konkatenalXML();
            } else {
                _xml.setHibaVan(true);
            }
        }
    }

    public boolean valid_reg_felh(NodeList n1) {
        for (int i = 0; i < n1.getLength(); i++) {
            String felhasznalo_id = n1.item(i).getAttributes().getNamedItem("id").getNodeValue();
            felhasznalo_nevek.add(felhasznalo_id);
            if (felhasznalo_nevek.get(i).equals(adat.getUserfield())) {
                return true;
            }
        }
        return false;
    }
    
    

    public void valid_log(NodeList n1, NodeList n2) {
        for (int i = 0; i < n1.getLength(); i++) {
            jelszavak.add(n1.item(i).getTextContent());
            String fel_id = n2.item(i).getAttributes().getNamedItem("id").getNodeValue();
            user_nevek.add(fel_id);
            if (user_nevek.get(i).equals(logadatok.getLogUser()) && jelszavak.get(i).equals(logadatok.getLogPassword())) {
                hibaVan2 = false;
                logom.info("Bejelentkezés folyamatban!");
                break;
            } else {
                hibaVan2 = true;
            }

        }
    }

}
