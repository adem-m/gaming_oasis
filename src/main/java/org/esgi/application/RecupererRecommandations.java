package org.esgi.application;

import org.esgi.domain.*;
import org.esgi.domain.jeu.Genre;
import org.esgi.domain.jeu.Jeu;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecupererRecommandations {
    private final Utilisateurs utilisateurs;
    private final Jeux jeux;

    public RecupererRecommandations(Utilisateurs utilisateurs, Jeux jeux) {
        this.utilisateurs = utilisateurs;
        this.jeux = jeux;
    }


    public Recommendations recuperer(String idUtilisateur) {
        Utilisateur utilisateur = utilisateurs.recuperer(idUtilisateur);
        Set<Genre> genresLesPlusJouesParLUtilisateur = utilisateurs.genresLesPlusJoues(idUtilisateur);
        Set<Genre> genresLesPlusJouesDuCatalogue = jeux.genresLesPlusJoues();
        Catalogue catalogue = new Catalogue(jeux);

        Set<Genre> genres =
                utilisateur.recupererGenres(genresLesPlusJouesParLUtilisateur, genresLesPlusJouesDuCatalogue);
        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre =
                catalogue.recupererRecommandations(genres, utilisateur);

        return new Recommendations(jeuxLesMieuxNotesParGenre);
    }

}
