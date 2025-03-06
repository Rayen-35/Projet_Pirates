package pirate;

//Classe CarteSpeciale
class CarteSpeciale extends Carte {
 private EffetSpecial effet;

 public CarteSpeciale(String nom, String description, EffetSpecial effet) {
     super(nom, description);
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