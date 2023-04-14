package org.esgi.domain.jeu;

import org.esgi.domain.Utilisateur;

public record Avis(
        String contenu,
        Utilisateur utilisateur
) {
}
