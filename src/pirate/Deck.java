package pirate;

public class Deck {
    private Carte[] cartes;
    private int indexPioche;
    
    public Deck(Carte[] cartes) {
        this.cartes = cartes;
        this.indexPioche = 0;
    }
    
    public Carte piocher() {
        if (!estVide()) {
            return cartes[indexPioche++];
        }
        return cartes[cartes.length - 1]; // Retourner la dernière carte pour éviter `null`
    }

    public boolean estVide() { // Vérifie si toutes les cartes ont été piochées.
        return indexPioche >= cartes.length;  
    }
    
}
