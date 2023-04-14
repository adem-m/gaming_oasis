package org.esgi.application;

import org.esgi.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecupererRecommandationsTest {
    @Mock
    private UtilisateurRepository utilisateurs;

    @Mock
    private Jeux catalogue;

    private Utilisateur utilisateur;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur("Adem", utilisateurs);
        when(utilisateurs.recuperer(any(String.class))).thenReturn(utilisateur);
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiNAJamaisJoue() {
        when(catalogue.jeuxLesMieuxNotesParGenre(any(Genre.class), anyInt(), anyInt())).thenReturn(List.of(
                new Jeu(
                        "1",
                        "Jeu 1 " + Genre.ARCADE,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(Genre.ARCADE),
                        Note.de(9)
                ),
                new Jeu(
                        "2",
                        "Jeu 2 " + Genre.PUZZLE,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(Genre.PUZZLE),
                        Note.de(9)
                )
        ));
        when(utilisateurs.genresLesPlusJoues(any(String.class))).thenReturn(Set.of());
        when(catalogue.genresLesPlusJoues(anyInt())).thenReturn(Set.of(Genre.ARCADE, Genre.PUZZLE));
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(2, jeux.size());
        assertEquals(10, jeux.get(Genre.ARCADE).size());
        assertEquals(10, jeux.get(Genre.PUZZLE).size());
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiNAJamaisJoueAvecUnCatalogueVide() {
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(0, jeux.size());
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiADejaJoueADesJeuxDAction() {
        when(utilisateurs.genresLesPlusJoues(anyString())).thenReturn(Set.of(Genre.ACTION));
        when(catalogue.jeuxLesMieuxNotesParGenre(any(Genre.class), anyInt(), anyInt())).thenReturn(List.of(
                new Jeu(
                        "1",
                        "Jeu 1 " + Genre.ACTION,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(Genre.ACTION),
                        Note.de(9)
                ),
                new Jeu(
                        "2",
                        "Jeu 2 " + Genre.ACTION,
                        "Descri",
                        List.of(new Avis("Mon avis", utilisateur)),
                        new Prix(10.0),
                        List.of(Genre.ACTION),
                        Note.de(9)
                )
        ));
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(1, jeux.size());
        assertEquals(10, jeux.get(Genre.ACTION).size());
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiADejaJoueADesJeuxDActionAvecUnCatalogueVide() {
        when(utilisateurs.genresLesPlusJoues(anyString())).thenReturn(Set.of(Genre.ACTION));
        when(catalogue.jeuxLesMieuxNotesParGenre(any(Genre.class), anyInt(), anyInt())).thenReturn(List.of());
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(0, jeux.size());
    }
}