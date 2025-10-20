package com.example.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TennisApplicationTests {

    @Test
    @DisplayName("Test initialisation d'un match")
    void initMatch() {
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //THEN
        assertThat(match.getScore()).isEqualTo("Love - Love");
    }

    @Test
    @DisplayName("Test Joueur 1 marque premier point")
    void testJoueur1MarquerPoint() {

        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur1();

        //THEN
        assertThat(match.getScore()).isEqualTo("15 - Love");
    }

    @Test
    @DisplayName("Test Joueur 2 marque premier point")
    void testJoueur2MarquerPoint() {

        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();

        //THEN
        assertThat(match.getScore()).isEqualTo("Love - 15");
    }

    @Test
    @DisplayName("Test Joueur 2 marque 2 points")
    void testJoueursMarquePlusieursPoint() {
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur2();

        //THEN
        assertThat(match.getScore()).isEqualTo("15 - 30");
    }

    @Test
    @DisplayName("Test victoire sans Deuce")
    void testJoueur1Gagne(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();

        //THEN
        assertThat(match.getScore()).isEqualTo("joueur1 a gagné");

    }

    @Test
    @DisplayName("Test pas de victoire si moins de 2 points d'écart quand les points d'un joueur sont arrivés à 40")
    void testPasEcartDeDeuxPoints(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();

        //THEN
        assertThat(match.getScore()).isEqualTo("30 - 40");

    }

    @Test
    @DisplayName("Test de l'égalité Deuce")
    void testDeuce(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();

        //THEN
        assertThat(match.getScore()).isEqualTo("Deuce - Deuce");

    }

    @Test
    @DisplayName("Test de la prise de l'avantage")
    void testJoueur1PrendAvantage(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();

        //THEN
        assertThat(match.getScore()).isEqualTo("Avantage - ");

    }

    @Test
    @DisplayName("Test de la perte l'avantage")
    void testAnnulationAvantage(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();

        //THEN
        assertThat(match.getScore()).isEqualTo("Deuce - Deuce");

    }


    @Test
    @DisplayName("Test de la victoire après l'avantage")
    void testGagnerAvecAvantage(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();

        //THEN
        assertThat(match.getScore()).isEqualTo("joueur1 a gagné");

    }

    @Test
    @DisplayName("Reinitialisation d'un match")
    void testReinitMatch(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();

        match.reinitMatch("joueur3", "joueur4");

        //THEN
        assertThat(match.getScore()).isEqualTo("Love - Love");
        assertThat(match.isEstJeuTermine()).isFalse();
        assertThat(match.isEstDeuce()).isFalse();
        assertThat(match.getNomGagnant()).isNull();
        assertThat(match.getJoueur1().getNom()).isEqualTo("joueur3");
        assertThat(match.getJoueur2().getNom()).isEqualTo("joueur4");

    }

    @Test
    @DisplayName("Test qu'on ne peut plus marquer de point quand le jeu est terminé")
    void testNonMarquageDePointQuandJeuTerminé(){
        //GIVEN
        Match match = new Match("joueur1", "joueur2");

        //WHEN
        match.marquerPointJoueur2();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();
        match.marquerPointJoueur1();
        match.marquerPointJoueur2();
        match.marquerPointJoueur2();
        match.marquerPointJoueur2();
        match.marquerPointJoueur2();

        //THEN
        assertThat(match.getScore()).isEqualTo("joueur1 a gagné");

    }


}
