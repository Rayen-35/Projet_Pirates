package pirate;

class Pirate {
    private String nom;
private int pointsVie;
private int popularite;
private Carte[] main;
private int nombreCartes;
private boolean defenseActive;
private boolean doubleAttaqueActive;

public Pirate(String nom) {
    this.nom = nom;
    this.pointsVie = 5;
    this.popularite = 0;
    this.main = new Carte[5]; // Chaque pirate peut avoir jusqu'à 5 cartes
    this.nombreCartes = 0;
    this.defenseActive = false;
    this.doubleAttaqueActive = false;
}

public void ajouteCarte(Carte carte) {
    if (nombreCartes < main.length) {
        main[nombreCartes++] = carte;
    }
}
public Carte getCarte(int index) {
    if (index >= 0 && index < nombreCartes) {
        return main[index];
    }
    return null; // Gérer le cas où l'index est invalide
}
public Carte jouerCarte(int index) {
    if (index >= 0 && index < nombreCartes) {
        Carte carteJouee = main[index];
        for (int i = index; i < nombreCartes - 1; i++) {
            main[i] = main[i + 1]; // Décaler les cartes restantes
        }
        nombreCartes--;
        return carteJouee;
    }
    return null;
}

public int getNombreCartes() {
    return nombreCartes;
}

public String getNom() {
    return nom;
}

public int getPointsVie() {
    return pointsVie;
}

public int getPopularite() {
    return popularite;
}
public void ajouterPopularite(int points) {
    this.popularite += points;
    System.out.println(nom + " gagne " + points + " point(s) de popularité !");
}
public boolean estVivant() {
    return pointsVie > 0;
}

public boolean aGagne() {
    return popularite >= 5;
}

public void subitAttaque(int degats) {
    if (defenseActive) {
        degats /= 2;
        defenseActive = false; // Désactiver la défense après une attaque
    }
    pointsVie -= degats;
    
    System.out.println(nom + " perd " + degats + " point(s) de vie !");
}

public void reduirePopularite() {
    
        popularite--;
        System.out.println(nom + " perd 1 point de popularité !");
    }


public void activerDefense() {
    defenseActive = true;
    System.out.println(nom + " active une défense pour réduire les dégâts de la prochaine attaque.");
}

public void activerDoubleAttaque() {
    doubleAttaqueActive = true;
    System.out.println(nom + " active Double Attaque pour multiplier l'effet de la prochaine attaque.");
}

public boolean estDoubleAttaqueActive() {
    return doubleAttaqueActive;
}

public void desactiverDoubleAttaque() {  // // L'effet ne s'applique qu'une seule fois
    doubleAttaqueActive = false;
}
}

