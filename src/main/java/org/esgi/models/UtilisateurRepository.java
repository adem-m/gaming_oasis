package org.esgi.models;

import java.util.Set;

public interface UtilisateurRepository {
    Set<Genre> genresLesPlusJoues(String idUtilisateur);

    boolean jeuDejaJoue(String idUtilisateur, String id);
}
