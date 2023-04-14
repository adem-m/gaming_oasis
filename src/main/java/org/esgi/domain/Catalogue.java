package org.esgi.domain;

import java.util.*;

public class Catalogue {
    private final static int NB_JEUX_RECOMMANDES_PAR_GENRE = 10;

    private final Jeux jeux;

    public Catalogue(Jeux jeux) {
        this.jeux = jeux;
    }

    public Map<Genre, List<Jeu>> recupererJeuxLesMieuxNotesParGenre(Set<Genre> genres, Utilisateur utilisateur) {
        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre = new HashMap<>();
        genres.forEach(genre -> {
            List<Jeu> jeuxDuGenre = new ArrayList<>();
            int page = 0;
            while (jeuxDuGenre.size() < NB_JEUX_RECOMMANDES_PAR_GENRE) {
                List<Jeu> jeuxLesMieuxNotes = jeux.jeuxLesMieuxNotesParGenre(genre, NB_JEUX_RECOMMANDES_PAR_GENRE, page);
                if (jeuxLesMieuxNotes.isEmpty())
                    break;
                jeuxDuGenre.addAll(jeuxLesMieuxNotes);
                jeuxDuGenre = utilisateur.recupererJeuxAuquelIlNAPasJoue(jeuxDuGenre, NB_JEUX_RECOMMANDES_PAR_GENRE);
                page++;
            }
            if (!jeuxDuGenre.isEmpty()) {
                jeuxLesMieuxNotesParGenre.put(genre, jeuxDuGenre);
            }
        });
        return jeuxLesMieuxNotesParGenre;
    }
}
