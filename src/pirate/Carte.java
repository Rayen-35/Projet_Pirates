package pirate;

//Classe abstraite pour les cartes
abstract class Carte {
protected String description;

public Carte(String description) {
   this.description = description;
}

public String getDescription() {
   return description;
}

public abstract void effet(Pirate pirate, Pirate adversaire);
}
