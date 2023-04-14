package org.esgi.domain;

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
            Recommandation recommandation = recupererMeilleursJeuxParPage(genre, utilisateur, Recommandation.vide(), 0);
            if (!recommandation.jeux().isEmpty()) {
                recommandations.put(genre, recommandation.jeux());
            }
        });
        return recommandations;
    }

    private Recommandation recupererMeilleursJeuxParPage(Genre genre, Utilisateur utilisateur, Recommandation recommandation, int page) {
        List<Jeu> meilleursJeux = jeux.recupererMeilleursJeux(genre, NB_JEUX_RECOMMANDES_PAR_GENRE, page);
        if (meilleursJeux.isEmpty())
            return recommandation;

        List<Jeu> meilleursJeuxNonJoues =
                utilisateur.recupererJeuxAuquelIlNAPasJoue(meilleursJeux);

        recommandation.ajouter(meilleursJeuxNonJoues);

        if (recommandation.estIncomplete()) {
            return recupererMeilleursJeuxParPage(genre, utilisateur, recommandation, page + 1);
        }
        return recommandation;
    }
}
