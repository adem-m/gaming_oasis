package org.esgi.models;

import java.util.List;
import java.util.Map;

public record Recommendations(
        Map<Genre, List<Jeu>> jeux
) {
}
