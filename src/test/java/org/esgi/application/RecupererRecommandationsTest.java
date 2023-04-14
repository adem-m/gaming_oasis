package org.esgi.application;

import org.esgi.domain.Jeux;
import org.esgi.domain.Recommendations;
import org.esgi.domain.Utilisateur;
import org.esgi.domain.Utilisateurs;
import org.esgi.domain.jeu.Genre;
import org.esgi.domain.jeu.Jeu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecupererRecommandationsTest {
    @Mock
    private Utilisateurs utilisateurs;

    @Mock
    private Jeux catalogue;

    private RecupererRecommandationsTestData testData;

    @Before
    public void setUp() {
        Utilisateur utilisateur = new Utilisateur("Adem", List.of());
        testData = new RecupererRecommandationsTestData();
        when(utilisateurs.recuperer(any(String.class))).thenReturn(utilisateur);
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiNAJamaisJoue() {
        when(catalogue.recupererMeilleursJeux(eq(Genre.AVENTURE), anyInt(), anyInt())).thenReturn(testData.JEUX_D_AVENTURE);
        when(catalogue.recupererMeilleursJeux(eq(Genre.RPG), anyInt(), anyInt())).thenReturn(testData.JEUX_RPG);

        when(utilisateurs.genresLesPlusJoues("toto")).thenReturn(Set.of());
        when(catalogue.genresLesPlusJoues(anyInt())).thenReturn(Set.of(Genre.AVENTURE, Genre.RPG));
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(2, jeux.size());
        assertTrue(jeux.containsKey(Genre.AVENTURE));
        assertTrue(jeux.containsKey(Genre.RPG));
        assertEquals(testData.JEUX_D_AVENTURE.subList(0, 10), jeux.get(Genre.AVENTURE));
        assertEquals(testData.JEUX_RPG.subList(0, 10), jeux.get(Genre.RPG));
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiNAJamaisJoueAvecUnCatalogueVide() {
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(0, jeux.size());
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiADejaJoueADeuxJeuxDAction() {
        when(utilisateurs.recuperer("toto")).thenReturn(new Utilisateur("toto", testData.JEUX_D_ACTION.subList(0, 2)));
        when(utilisateurs.genresLesPlusJoues("toto")).thenReturn(Set.of(Genre.ACTION));
        when(catalogue.recupererMeilleursJeux(eq(Genre.ACTION), anyInt(), anyInt())).thenReturn(testData.JEUX_D_ACTION);
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(1, jeux.size());
        assertTrue(jeux.containsKey(Genre.ACTION));

        assertEquals(testData.JEUX_D_ACTION.subList(2, 12), jeux.get(Genre.ACTION));
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiADejaJoueADixJeuxDAction() {
        when(utilisateurs.recuperer("toto")).thenReturn(new Utilisateur("toto", testData.JEUX_D_ACTION.subList(0, 10)));
        when(utilisateurs.genresLesPlusJoues("toto")).thenReturn(Set.of(Genre.ACTION));
        when(catalogue.recupererMeilleursJeux(eq(Genre.ACTION), anyInt(), eq(0))).thenReturn(testData.JEUX_D_ACTION.subList(0,10));
        when(catalogue.recupererMeilleursJeux(eq(Genre.ACTION), anyInt(), eq(1))).thenReturn(testData.JEUX_D_ACTION.subList(11,15));
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(1, jeux.size());
        assertTrue(jeux.containsKey(Genre.ACTION));

        assertEquals(testData.JEUX_D_ACTION.subList(11, 15), jeux.get(Genre.ACTION));
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiADejaJoueADesJeuxDAventureAvecUnCatalogueVide() {
        when(utilisateurs.genresLesPlusJoues(anyString())).thenReturn(Set.of(Genre.AVENTURE));
        when(catalogue.recupererMeilleursJeux(any(Genre.class), anyInt(), anyInt())).thenReturn(List.of());
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(0, jeux.size());
    }
}