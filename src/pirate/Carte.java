package pirate;

// Classe abstraite représentant une carte du jeu
abstract class Carte {
    // Attributs communs à toutes les cartes
    protected String nom;
    protected String description;

    // Constructeur pour initialiser nom et description
    public Carte(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // Getter pour le nom de la carte
    public String getNom() {
        return nom;
    }

    // Getter pour la description de la carte
    public String getDescription() {
        return description;
    }

    // Méthode abstraite : chaque type de carte doit définir son effet
    public abstract void effet(Pirate pirate, Pirate adversaire);

    // Retourne la zone de jeu selon le type de carte (utile pour l'affichage)
    public String getZone() {
        if (this instanceof CarteAttaque) {
            return "Zone des cartes d'Attaque";
        } else if (this instanceof CartePopularite) {
            return "Zone des cartes de Popularité";
        } else if (this instanceof CarteSpeciale) {
            return "Zone des cartes Spéciales";
        }
        return "Zone inconnue"; // Cas de sécurité si le type est inattendu
    }
}
