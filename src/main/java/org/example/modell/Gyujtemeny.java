package org.example.modell;

import java.io.Serializable;
import java.util.UUID;

public class Gyujtemeny implements Serializable {
    private transient UUID id;
    private String alkoto, cim;
    private Kategoriak kategoria;

    public Gyujtemeny(String alkoto, String cim, Kategoriak kategoria) {
        this.alkoto = alkoto;
        this.cim = cim;
        this.kategoria = kategoria;
        ujIdGeneralas();
    }

    public void ujIdGeneralas() {
        id=UUID.randomUUID();
    }

    public String getAlkoto() {
        return alkoto;
    }

    public void setAlkoto(String alkoto) {
        this.alkoto = alkoto;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public Kategoriak getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoriak kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return "Mukincs{" +
                "id=" + id +
                ", alkoto='" + alkoto + '\'' +
                ", cim='" + cim + '\'' +
                ", kategoria=" + kategoria +
                '}';
    }
}
