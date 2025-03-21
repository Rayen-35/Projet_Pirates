package pirate;

class Pirate {
    private String nom;                      // Nom du pirate
    private int pointsVie;                   // Points de vie du pirate
    private int popularite;                  // Points de popularité
    private Carte[] main;                    // Main de cartes (max 5)
    private int nombreCartes;                // Nombre de cartes actuellement dans la main
    private boolean defenseActive;           // Indique si une défense est activée
    private boolean doubleAttaqueActive;     // Indique si l'effet Double Attaque est activé

    // Constructeur : initialise les attributs du pirate
    public Pirate(String nom) {
        this.nom = nom;
        this.pointsVie = 5;
        this.popularite = 0;
        this.main = new Carte[5]; // Main de 5 cartes max
        this.nombreCartes = 0;
        this.defenseActive = false;
        this.doubleAttaqueActive = false;
    }

    // Ajoute une carte à la main si la limite n'est pas atteinte
    public void ajouteCarte(Carte carte) {
        if (nombreCartes < main.length) {
            main[nombreCartes++] = carte;
        }
    }

    // Récupère une carte par son index
    public Carte getCarte(int index) {
        if (index >= 0 && index < nombreCartes) {
            return main[index];
        }
        return null; // Retourne null si l'index est invalide
    }

    // Joue une carte et la retire de la main (en décalant les autres)
    public Carte jouerCarte(int index) {
        if (index >= 0 && index < nombreCartes) {
            Carte carteJouee = main[index];
            for (int i = index; i < nombreCartes - 1; i++) {
                main[i] = main[i + 1];
            }
            nombreCartes--;
            return carteJouee;
        }
        return null;
    }

    // Getters utiles
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

    // Ajoute des points de popularité
    public void ajouterPopularite(int points) {
        this.popularite += points;
        System.out.println(nom + " gagne " + points + " point(s) de popularité !");
    }

    // Vérifie si le pirate est encore en vie
    public boolean estVivant() {
        return pointsVie > 0;
    }

    // Vérifie si le pirate a gagné (popularité >= 5)
    public boolean aGagne() {
        return popularite >= 5;
    }

    // Applique une attaque, en tenant compte d'une éventuelle défense
    public void subitAttaque(int degats) {
        if (defenseActive) {
            degats /= 2;            // Réduction des dégâts
            defenseActive = false;  // Une seule utilisation
        }
        pointsVie -= degats;
        System.out.println(nom + " perd " + degats + " point(s) de vie !");
    }

    // Diminue la popularité (utilisé par Sabotage)
    public void reduirePopularite() {
        popularite--;
        System.out.println(nom + " perd 1 point de popularité !");
    }

    // Active la défense
    public void activerDefense() {
        defenseActive = true;
        System.out.println(nom + " active une défense pour réduire les dégâts de la prochaine attaque.");
    }

    // Active l'effet de double attaque
    public void activerDoubleAttaque() {
        doubleAttaqueActive = true;
        System.out.println(nom + " active Double Attaque pour multiplier l'effet de la prochaine attaque.");
    }

    // Vérifie si l'effet de double attaque est actif
    public boolean estDoubleAttaqueActive() {
        return doubleAttaqueActive;
    }

    // Désactive l'effet de double attaque (après l'avoir utilisé)
    public void desactiverDoubleAttaque() {
        doubleAttaqueActive = false;
    }
}
