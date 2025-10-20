package com.example.tennis;

public class Joueur {
    private String nom;
    private String point;
    private boolean avantage;

    public Joueur(String nom) {
        this.nom = nom;
        this.point = "Love";
        this.avantage =false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public boolean getAvantage() {
        return avantage;
    }

    public void setAvantage(boolean aAvantage) {
        this.avantage = aAvantage;
    }

    public void marquerPoint(){
        switch (point) {
            case "Love" -> this.point ="15";
            case "15" -> this.point ="30";
            case "30" -> this.point ="40";
        }
    }
}
