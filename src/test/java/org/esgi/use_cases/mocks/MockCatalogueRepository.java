package org.esgi.use_cases.mocks;

import org.esgi.models.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockCatalogueRepository implements CatalogueRepository {
    @Override
    public Set<Genre> genresLesPlusJoues(int nbGenreMax) {
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.AVENTURE);
        genres.add(Genre.FPS);
        genres.add(Genre.RPG);
        genres.add(Genre.STRATEGIE);
        genres.add(Genre.GESTION);
        genres.add(Genre.SPORT);
        genres.add(Genre.RACING);
        genres.add(Genre.SIMULATION);
        genres.add(Genre.BATTLE_ROYALE);
        genres.add(Genre.MMORPG);
        genres.add(Genre.MOBA);

        return genres.stream().limit(nbGenreMax).collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    @Override
    public List<Jeu> jeuxLesMieuxNotesParGenre(Genre genre, int nbJeuxRecommandesParGenre, int page) {
        Utilisateur utilisateur = new Utilisateur("Adem");
        return List.of(
                new Jeu(
                        "1",
                        "Jeu 1 " + genre,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(genre),
                        Note.de(9)
                ),
                new Jeu(
                        "2",
                        "Jeu 2 " + genre,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(genre),
                        Note.de(9)
                ),
                new Jeu(
                        "3",
                        "Jeu 3 " + genre,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(genre),
                        Note.de(9)
                )
        );
    }
}
