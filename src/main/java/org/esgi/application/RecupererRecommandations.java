package org.esgi.application;

import org.esgi.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecupererRecommandations {
    private final static int NB_GENRE_MAX = 5;

    private final UtilisateurRepository utilisateurs;
    private final Jeux jeux;

    public RecupererRecommandations(UtilisateurRepository utilisateurs, Jeux jeux) {
        this.utilisateurs = utilisateurs;
        this.jeux = jeux;
    }


    public Recommendations recuperer(String idUtilisateur) {
        Utilisateur utilisateur = utilisateurs.recuperer(idUtilisateur);
        Set<Genre> genresLesPlusJouesParLUtilisateur = utilisateurs.genresLesPlusJoues(idUtilisateur);
        Set<Genre> genresLesPlusJouesDuCatalogue = jeux.genresLesPlusJoues(NB_GENRE_MAX);
        Catalogue catalogue = new Catalogue(jeux);

        Set<Genre> genresLesPlusJoues =
                utilisateur.recupererGenresLesPlusJoues(genresLesPlusJouesParLUtilisateur, genresLesPlusJouesDuCatalogue);
        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre =
                catalogue.recupererJeuxLesMieuxNotesParGenre(genresLesPlusJoues, utilisateur);

        return new Recommendations(jeuxLesMieuxNotesParGenre);
    }

}
