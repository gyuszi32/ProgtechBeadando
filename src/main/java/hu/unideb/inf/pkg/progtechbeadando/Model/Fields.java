/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.pkg.progtechbeadando.Model;

import java.util.logging.Logger;


public class Fields {
    public int akna=0;
    public boolean checked=false;
    public boolean zaszlo=false;

    public Fields() {
    }

    public int getAkna() {
        return akna;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isZaszlo() {
        return zaszlo;
    }

    public void setAkna(int akna) {
        this.akna = akna;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setZaszlo(boolean zaszlo) {
        this.zaszlo = zaszlo;
    }
    private static final Logger LOG = Logger.getLogger(Fields.class.getName());
    
}
