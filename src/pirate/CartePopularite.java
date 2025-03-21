package pirate;

// Classe représentant une carte qui augmente la popularité
class CartePopularite extends Carte {
    private int popularite; // Points de popularité gagnés par cette carte

    // Constructeur : initialise le nom, la description et les points de popularité
    public CartePopularite(String nom, String description, int popularite) {
        super(nom, description); // Appelle le constructeur de la classe Carte
        this.popularite = popularite;
    }

    // Implémentation de l'effet de la carte : augmente la popularité du pirate
    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        pirate.ajouterPopularite(popularite);
    }
}
