package org.esgi.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Utilisateur(
        String nom,
        UtilisateurRepository utilisateurs
) {
    public Set<Genre> recupererGenres(Set<Genre> genresLesPlusJouesParLUtilisateur,
                                      Set<Genre> genresLesPlusJouesDuCatalogue) {
        if (genresLesPlusJouesParLUtilisateur.isEmpty()) {
            return genresLesPlusJouesDuCatalogue;
        }
        return genresLesPlusJouesParLUtilisateur;
    }

    private boolean aDejaJoueAuJeu(IdJeu idJeu) {
        return utilisateurs.jeuDejaJoue(nom, idJeu.valeur());
    }

    public List<Jeu> recupererJeuxAuquelIlNAPasJoue(List<Jeu> jeux, int limit) {
        return jeux.stream()
                .filter(jeu -> !aDejaJoueAuJeu(jeu.id()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
