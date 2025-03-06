package pirate;

class CarteSpeciale extends Carte {
    private EffetSpecial effet;

    public CarteSpeciale(EffetSpecial effet) {
        super(effet == EffetSpecial.DEFENSE ? "Diminue l'effet de la prochaine attaque de moitié" :
              effet == EffetSpecial.SABOTAGE ? "Réduit la popularité de l'adversaire" :
              "Double l'effet de la prochaine attaque");
        this.effet = effet;
    }

    @Override
    public void effet(Pirate pirate, Pirate adversaire) {
        if (effet == EffetSpecial.DEFENSE) {
            pirate.activerDefense();
        } else if (effet == EffetSpecial.SABOTAGE) {
            adversaire.reduirePopularite();
        } else if (effet == EffetSpecial.DOUBLE_ATTAQUE) {
            pirate.activerDoubleAttaque();
        }
    }
}