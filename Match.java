package com.example.tennis;

public class Match {

    private Joueur joueur1;
    private Joueur joueur2;
    private boolean estJeuTermine;
    private String nomGagnant;
    private boolean estDeuce;

    public Match(String nomJoueur1, String nomJoueur2) {
        reinitMatch(nomJoueur1, nomJoueur2);
    }

    public String getScore() {
        if (estJeuTermine) {
            return nomGagnant + " a gagné";
        }
        if(estDeuce){
            if(joueur1.getAvantage()){
                return "Avantage - ";
            }
            if(joueur2.getAvantage()){
                return " - Avantage";
            }
            return "Deuce - Deuce";
        }
        return joueur1.getPoint() + " - " + joueur2.getPoint();
    }

    public void marquerPoint(Joueur joueur, Joueur joueurAdversaire) {
        if(!estJeuTermine) {
            if (joueur.getAvantage()) {
                victoire(joueur.getNom());
            }
            if (estDeuce) {
                if (joueurAdversaire.getAvantage()) {
                    joueurAdversaire.setAvantage(false);
                } else {
                    joueur.setAvantage(true);
                }
                return;
            }
            joueur.marquerPoint();
            if (joueur.getPoint().equals("40")) {
                if (joueurAdversaire.getPoint().equals("40")) {
                    this.estDeuce = true;
                } else if (!joueurAdversaire.getPoint().equals("30")) {
                    victoire(joueur.getNom());
                }
            }
        }

    }

    public void victoire(String nomJoueurGagnant){
        this.estJeuTermine = true;
        this.nomGagnant = nomJoueurGagnant;
    }

    public void marquerPointJoueur1() {
        marquerPoint(this.joueur1, this.joueur2);
    }

    public void marquerPointJoueur2() {
        marquerPoint(this.joueur2, this.joueur1);
    }

    public void reinitMatch(String nomJoueur1, String nomJoueur2){
        this.joueur1 = new Joueur(nomJoueur1);
        this.joueur2 = new Joueur(nomJoueur2);
        this.estJeuTermine = false;
        this.nomGagnant = null;
        this.estDeuce = false;
    }

    public boolean isEstJeuTermine() {
        return estJeuTermine;
    }

    public String getNomGagnant() {
        return nomGagnant;
    }

    public boolean isEstDeuce() {
        return estDeuce;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }
}
