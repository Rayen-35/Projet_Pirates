package pirate;

//Classe abstraite pour les cartes
abstract class Carte {
 protected String nom;
 protected String description;

 public Carte(String nom, String description) {
     this.nom = nom;
     this.description = description;
 }

 public String getNom() {
     return nom;
 }

 public String getDescription() {
     return description;
 }

 public abstract void effet(Pirate pirate, Pirate adversaire);
 
 public String getZone() {
     if (this instanceof CarteAttaque) {
         return "Zone des cartes d'Attaque";
     } else if (this instanceof CartePopularite) {
         return "Zone des cartes de Popularité";
     } else if (this instanceof CarteSpeciale) {
         return "Zone des cartes Spéciales";
     }
     return "Zone inconnue";
 }
}