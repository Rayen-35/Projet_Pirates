package pirate;

class CartePopularite extends Carte {
    private int popularite;

    public CartePopularite(int popularite) {
        super("+" + popularite + " Popularité");
        this.popularite = popularite;
    }

    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        pirate.ajouterPopularite(popularite);
    }
}
