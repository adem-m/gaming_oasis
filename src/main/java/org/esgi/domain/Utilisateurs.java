package org.esgi.domain;

import org.esgi.domain.jeu.Genre;

import java.util.Set;

public interface Utilisateurs {
    Utilisateur recuperer(String id);

    Set<Genre> genresLesPlusJoues(String idUtilisateur);

    boolean jeuDejaJoue(String idUtilisateur, String id);
}
