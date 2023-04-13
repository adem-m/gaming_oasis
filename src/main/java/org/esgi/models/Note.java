package org.esgi.models;

public class Note {
    private final float valeur;

    private Note(float valeur) {
        this.valeur = valeur;
    }

    public static Note de(float valeur) {
        if (valeur < 0 || valeur > 10) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 10");
        }
        return new Note(valeur);
    }

    public float getValeur() {
        return valeur;
    }
}
