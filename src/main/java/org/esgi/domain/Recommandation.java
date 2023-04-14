package org.esgi.domain;

import org.esgi.domain.jeu.Jeu;

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

    public void ajouter(List<Jeu> jeux, Utilisateur utilisateur) {
        this.jeux.addAll(jeux);
        retirerJeuxDejaJoues(utilisateur);
    }

    private void retirerJeuxDejaJoues(Utilisateur utilisateur) {
        List<Jeu> jeuxNonJoues = utilisateur.recupererJeuxAuquelIlNAPasJoue(jeux, NB_JEUX_RECOMMANDES);
        jeux.clear();
        jeux.addAll(jeuxNonJoues);
    }
}
