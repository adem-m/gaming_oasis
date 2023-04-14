package org.esgi.domain;

import org.esgi.domain.jeu.Genre;
import org.esgi.domain.jeu.Jeu;

import java.util.List;
import java.util.Map;

public record Recommendations(
        Map<Genre, List<Jeu>> jeux
) {
}
