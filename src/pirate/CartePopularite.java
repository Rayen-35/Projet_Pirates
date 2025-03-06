package pirate;

//Classe CartePopularite
class CartePopularite extends Carte {
 private int popularite;

 public CartePopularite(String nom, String description, int popularite) {
     super(nom, description);
     this.popularite = popularite;
 }

 @Override
 public void effet(Pirate pirate, Pirate adversaire) {
     pirate.ajouterPopularite(popularite);
 }
}