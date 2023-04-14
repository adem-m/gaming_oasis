package org.esgi.domain;

import java.util.ArrayList;
import java.util.List;

public class Recommandation {
    private final static int NB_JEUX_RECOMMANDES = 10;

    private final List<Jeu> jeux;

    private Recommandation(List<Jeu> jeux) {
        this.jeux = jeux;
    }

    public static Recommandation vide() {
        return new Recommandation(new ArrayList<>());
    }

    public List<Jeu> jeux() {
        return jeux;
    }

    public boolean estIncomplete() {
        return jeux.size() < NB_JEUX_RECOMMANDES;
    }

    public void ajouter(List<Jeu> jeux) {
        this.jeux.addAll(jeux);
        if (this.jeux.size() > NB_JEUX_RECOMMANDES) {
            this.jeux.subList(NB_JEUX_RECOMMANDES, this.jeux.size()).clear();
        }
    }

}
