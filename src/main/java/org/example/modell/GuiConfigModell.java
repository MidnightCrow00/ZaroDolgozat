package org.example.modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuiConfigModell  implements Serializable {
    private List<String> comboSzovegek;
    private List<String> listSzovegek;
    private boolean chbMozgat;

    public GuiConfigModell() {
        comboSzovegek=new ArrayList<>();
        listSzovegek=new ArrayList<>();
        chbMozgat=false;
    }

    public List<String> getComboSzovegek() {
        return new ArrayList<>(comboSzovegek);
    }

    public void setComboSzovegek(List<String> comboSzovegek) {
        this.comboSzovegek = comboSzovegek;
    }

    public List<String> getListSzovegek() {
        return new ArrayList<>(listSzovegek);
    }

    public void setListSzovegek(List<String> listSzovegek) {
        this.listSzovegek = listSzovegek;
    }

    public boolean isChbMozgat() {
        return chbMozgat;
    }

    public void setChbMozgat(boolean chbMozgat) {
        this.chbMozgat = chbMozgat;
    }
}
