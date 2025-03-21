package pirate;

// Classe représentant une carte d'attaque
class CarteAttaque extends Carte {
    private int degats; // Nombre de points de dégâts infligés

    // Constructeur : initialise le nom, la description et les dégâts
    public CarteAttaque(String nom, String description, int degats) {
        super(nom, description); // Appelle le constructeur de la classe Carte
        this.degats = degats;
    }

    // Méthode qui applique l'effet de la carte (attaque l'adversaire)
    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        // Si le joueur a activé une double attaque, on double les dégâts
        if (pirate.estDoubleAttaqueActive()) {
            degats *= 2;
            pirate.desactiverDoubleAttaque(); // Une fois utilisée, l'effet est désactivé
            System.out.println(pirate.getNom() + " utilise Double Attaque ! Dégâts doublés : -" + degats + " Vie");
        }
        // Appliquer les dégâts à l'adversaire
        adversaire.subitAttaque(degats);
    }
}
