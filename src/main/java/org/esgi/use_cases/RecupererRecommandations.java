package org.esgi.use_cases;

import org.esgi.models.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecupererRecommandations {
    private final static int NB_GENRE_MAX = 5;

    private final UtilisateurRepository utilisateurs;
    private final CatalogueRepository catalogueRepository;

    public RecupererRecommandations(UtilisateurRepository utilisateurs, CatalogueRepository catalogueRepository) {
        this.utilisateurs = utilisateurs;
        this.catalogueRepository = catalogueRepository;
    }


    public Recommendations recuperer(String idUtilisateur) {
        Utilisateur utilisateur = utilisateurs.recuperer(idUtilisateur);
        Set<Genre> genresLesPlusJouesParLUtilisateur = utilisateurs.genresLesPlusJoues(idUtilisateur);
        Set<Genre> genresLesPlusJouesDuCatalogue = catalogueRepository.genresLesPlusJoues(NB_GENRE_MAX);
        Catalogue catalogue = new Catalogue(catalogueRepository);

        Set<Genre> genresLesPlusJoues =
                utilisateur.recupererGenresLesPlusJoues(genresLesPlusJouesParLUtilisateur, genresLesPlusJouesDuCatalogue);
        Map<Genre, List<Jeu>> jeuxLesMieuxNotesParGenre =
                catalogue.recupererJeuxLesMieuxNotesParGenre(genresLesPlusJoues, utilisateur);

        return new Recommendations(jeuxLesMieuxNotesParGenre);
    }

}
