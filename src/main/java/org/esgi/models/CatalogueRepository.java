package org.esgi.models;

import java.util.List;
import java.util.Set;

public interface CatalogueRepository {
    Set<Genre> genresLesPlusJoues(int nbGenreMax);

    List<Jeu> jeuxLesMieuxNotesParGenre(Genre genre, int nbJeuxRecommandesParGenre, int page);
}
