package org.esgi.use_cases;

import org.esgi.models.*;
import org.esgi.use_cases.mocks.MockCatalogueRepository;
import org.esgi.use_cases.mocks.MockUtilisateurRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RecupererRecommandationsTest {
    private UtilisateurRepository utilisateurs;
    private CatalogueRepository catalogue;

    @Before
    public void setUp() {
        utilisateurs = new MockUtilisateurRepository();
        catalogue = new MockCatalogueRepository();
    }

    @Test
    public void recupererRecommandationsDUnUtilisateurQuiNAJamaisJoue() {
        RecupererRecommandations recupererRecommandations = new RecupererRecommandations(utilisateurs, catalogue);
        Recommendations recommendations = recupererRecommandations.recuperer("toto");
        Map<Genre, List<Jeu>> jeux = recommendations.jeux();

        assertEquals(2, jeux.size());
        assertEquals(10, jeux.get(Genre.ARCADE).size());
        assertEquals(10, jeux.get(Genre.ACTION).size());
    }

}