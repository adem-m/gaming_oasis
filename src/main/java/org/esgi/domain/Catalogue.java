package org.esgi.domain;

import org.esgi.domain.jeu.Genre;
import org.esgi.domain.jeu.Jeu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Catalogue {
    private final static int NB_JEUX_RECOMMANDES_PAR_GENRE = 10;

    private final Jeux jeux;

    public Catalogue(Jeux jeux) {
        this.jeux = jeux;
    }

    public Map<Genre, List<Jeu>> recupererRecommandations(Set<Genre> genres, Utilisateur utilisateur) {
        Map<Genre, List<Jeu>> recommandations = new HashMap<>();
        genres.forEach(genre -> {
            Recommandation recommandation = recupererMeilleursJeux(genre, utilisateur);
            if (!recommandation.jeux().isEmpty()) {
                recommandations.put(genre, recommandation.jeux());
            }
        });
        return recommandations;
    }

    private Recommandation recupererMeilleursJeux(Genre genre, Utilisateur utilisateur) {
        Recommandation recommandation = Recommandation.vide();
        int page = 0;
        while (recommandation.estIncomplete()) {
            List<Jeu> meilleursJeux = jeux.recupererMeilleursJeux(genre, NB_JEUX_RECOMMANDES_PAR_GENRE, page);
            if (meilleursJeux.isEmpty())
                break;
            recommandation.ajouter(meilleursJeux, utilisateur);
            page++;
        }
        return recommandation;
    }
}
