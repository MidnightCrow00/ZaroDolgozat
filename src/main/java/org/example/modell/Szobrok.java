package org.example.modell;

import java.text.Collator;
import java.util.Comparator;
import java.util.UUID;

public class Szobrok extends Gyujtemeny implements Comparator<Szobrok>{
    private String anyag;
    private int szazad;

    public Szobrok(String alkoto, String cim, Kategoriak kategoria, String anyag, int szazad) {
        super(alkoto, cim, kategoria);
        this.anyag = anyag;
        this.szazad = szazad;
    }

    public void setAnyag(String anyag) {
        this.anyag = anyag;
    }

    public void setSzazad(int szazad) {
        if (szazad > 21) {
            throw new NagyobbMintHuszonEgyException("Nem lehet jövőbeli");
        } else {
            this.szazad = 20;
        }
    }
     public static SzazadComparator rendezSzazad(){
        return new SzazadComparator();
    }

    public static AnyagComparator rendezAnyag(){
        return new AnyagComparator();
    }

    @Override
    public int compare(Szobrok o1, Szobrok masik) {
        Collator coll = Collator.getInstance();
        return coll.compare(this.getAlkoto(), masik.getAlkoto());
    }

    private static class AnyagComparator implements Comparator<Szobrok> {
        @Override
        public int compare(Szobrok egyik, Szobrok masik) {
            Collator coll = Collator.getInstance();
            return coll.compare(egyik.anyag, masik.anyag);
        }
    }

    private static class SzazadComparator implements Comparator<Szobrok> {
        @Override
        public int compare(Szobrok egyik, Szobrok masik) {
            return egyik.szazad - masik.szazad;
        }
    }

}
