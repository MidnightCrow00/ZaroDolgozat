package org.example.modell;

import java.util.UUID;

public class Festmenyek extends Gyujtemeny{
    private String stilus, technika;

    public Festmenyek(String alkoto, String cim, Kategoriak kategoria, String stilus, String technika) {
        super(alkoto, cim, kategoria);
        this.stilus = stilus;
        this.technika = technika;
    }
}
