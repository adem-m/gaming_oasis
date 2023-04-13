package org.esgi.use_cases.mocks;

import org.esgi.models.Genre;
import org.esgi.models.UtilisateurRepository;

import java.util.Set;

public class MockUtilisateurRepository implements UtilisateurRepository {
    @Override
    public Set<Genre> genresLesPlusJoues(String idUtilisateur) {
        return Set.of(
                Genre.ARCADE,
                Genre.ACTION
        );
    }

    @Override
    public boolean jeuDejaJoue(String idUtilisateur, String id) {
        return false;
    }
}
