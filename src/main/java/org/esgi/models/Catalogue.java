package org.esgi.models;

import java.util.*;

public record Catalogue(
        CatalogueRepository catalogueRepository
) {
    private final static int NB_JEUX_RECOMMANDES_PAR_GENRE = 10;

    public Map<Genre, List<Jeu>> recupererJeuxLesMieuxNotesParGenre(Set<Genre> genres, Utilisateur utilisateur) {
        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre = new HashMap<>();
        genres.forEach(genre -> {
            List<Jeu> jeux = new ArrayList<>();
            int page = 0;
            while (jeux.size() < NB_JEUX_RECOMMANDES_PAR_GENRE) {
                List<Jeu> jeuxLesMieuxNotes = catalogueRepository.jeuxLesMieuxNotesParGenre(genre, NB_JEUX_RECOMMANDES_PAR_GENRE, page);
                if (jeuxLesMieuxNotes.isEmpty())
                    break;
                jeux.addAll(jeuxLesMieuxNotes);
                jeux = utilisateur.recupererJeuxAuquelIlNAPasJoue(jeux, NB_JEUX_RECOMMANDES_PAR_GENRE);
                page++;
            }
            if (!jeux.isEmpty()) {
                jeuxLesMieuxNotesParGenre.put(genre, jeux);
            }
        });
        return jeuxLesMieuxNotesParGenre;
    }
}
