package pirate;

public class Deck {
    private Carte[] cartes;        // Tableau de cartes du deck
    private int indexPioche;       // Indique la prochaine carte à piocher (position actuelle)

    // Constructeur : initialise le deck avec un tableau de cartes
    public Deck(Carte[] cartes) {
        this.cartes = cartes;
        this.indexPioche = 0; // Commence à la première carte
    }

    // Pioche une carte si le deck n'est pas vide
    public Carte piocher() {
        if (!estVide()) {
            return cartes[indexPioche++]; // Retourne la carte actuelle, puis avance le compteur
        }
        return cartes[cartes.length - 1]; // Si vide, retourne la dernière carte par défaut (évite null)
    }

    // Vérifie si toutes les cartes ont été piochées
    public boolean estVide() {
        return indexPioche >= cartes.length;
    }
}
