package org.esgi.domain;

import java.util.List;
import java.util.Set;

public interface Jeux {
    Set<Genre> genresLesPlusJoues(int nbGenreMax);

    List<Jeu> jeuxLesMieuxNotesParGenre(Genre genre, int nbJeuxRecommandesParGenre, int page);
}
