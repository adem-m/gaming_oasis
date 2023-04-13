package org.esgi.use_cases;

import org.esgi.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class RecupererRecommandations {
    private final static int NB_JEUX_RECOMMANDES_PAR_GENRE = 10;
    private final static int NB_GENRE_MAX = 5;

    private final UtilisateurRepository utilisateurs;
    private final CatalogueRepository catalogue;

    public RecupererRecommandations(UtilisateurRepository utilisateurs, CatalogueRepository catalogue) {
        this.utilisateurs = utilisateurs;
        this.catalogue = catalogue;
    }


    public Recommendations recuperer(String idUtilisateur) {
        Set<Genre> genresLesPlusJoues = utilisateurs.genresLesPlusJoues(idUtilisateur);
        if (genresLesPlusJoues.isEmpty()) {
            genresLesPlusJoues = catalogue.genresLesPlusJoues(NB_GENRE_MAX);
        }

        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre = new HashMap<>();
        genresLesPlusJoues.forEach(genre -> {
            List<Jeu> jeux = new ArrayList<>();
            int page = 0;
            while (jeux.size() < NB_JEUX_RECOMMANDES_PAR_GENRE) {
                jeux.addAll(catalogue.jeuxLesMieuxNotesParGenre(genre, NB_JEUX_RECOMMANDES_PAR_GENRE, page));
                jeux = jeux.stream()
                        .filter(jeu -> !utilisateurs.jeuDejaJoue(idUtilisateur, jeu.id()))
                        .limit(10)
                        .collect(Collectors.toList());
                page++;
            }

            jeuxLesMieuxNotesParGenre.put(genre, jeux);
        });

        return new Recommendations(jeuxLesMieuxNotesParGenre);
    }
}
