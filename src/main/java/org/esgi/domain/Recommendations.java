package org.esgi.domain;

import java.util.List;
import java.util.Map;

public record Recommendations(
        Map<Genre, List<Jeu>> jeux
) {
}
