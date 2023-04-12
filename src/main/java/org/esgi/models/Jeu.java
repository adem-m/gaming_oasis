package org.esgi.models;

import java.util.List;

public record Jeu(
        String titre,
        String description,
        List<Avis> avis,
        Prix prix,
        List<Genre> genres
) {
}
