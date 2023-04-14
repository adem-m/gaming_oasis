package org.esgi.domain;

import org.esgi.domain.jeu.Genre;
import org.esgi.domain.jeu.Jeu;

import java.util.List;
import java.util.Set;

public interface Jeux {
    Set<Genre> genresLesPlusJoues(int nbGenreMax);

    List<Jeu> recupererMeilleursJeux(Genre genre, int nbJeuxRecommandesParGenre, int page);
}
