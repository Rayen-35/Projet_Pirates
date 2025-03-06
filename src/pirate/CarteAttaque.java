package pirate;

//Classe CarteAttaque
class CarteAttaque extends Carte {
    private int degats;

    public CarteAttaque(String nom, String description, int degats) {
        super(nom, description);
        this.degats = degats;
    }

    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        if (pirate.estDoubleAttaqueActive()) {
            degats *= 2;
            pirate.desactiverDoubleAttaque();
            System.out.println(pirate.getNom() + " utilise Double Attaque ! Dégâts doublés : -" + degats + " Vie");
        }
        adversaire.subitAttaque(degats);
    }
}
