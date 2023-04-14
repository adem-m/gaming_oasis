package org.esgi.domain;

import java.util.Set;

public interface UtilisateurRepository {
    Utilisateur recuperer(String id);

    Set<Genre> genresLesPlusJoues(String idUtilisateur);

    boolean jeuDejaJoue(String idUtilisateur, String id);
}
