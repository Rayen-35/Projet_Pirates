package pirate;

// Classe représentant les cartes spéciales
class CarteSpeciale extends Carte {
    private EffetSpecial effet; // Type d'effet spécial associé à la carte

    // Constructeur : initialise le nom, la description et l'effet
    public CarteSpeciale(String nom, String description, EffetSpecial effet) {
        super(nom, description);
        this.effet = effet;
    }

    // Méthode appelée quand la carte est jouée
    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        if (effet == EffetSpecial.DEFENSE) {
            // Active une défense pour réduire les dégâts de la prochaine attaque
            pirate.activerDefense();
        } else if (effet == EffetSpecial.SABOTAGE) {
            // Réduit la popularité de l'adversaire
            adversaire.reduirePopularite();
        } else if (effet == EffetSpecial.DOUBLE_ATTAQUE) {
            // Active un bonus qui double les dégâts de la prochaine attaque du joueur
            pirate.activerDoubleAttaque();
        }
    }
}
