package projet;

import java.util.Scanner;
import java.util.Random;

public class affichage {

    
    public void demarrerJeu() {
        System.out.println("Bienvenue dans le jeu de cartes des pirates");
        System.out.println("Choisissez le personnage que vous voulez :");
   
    }

    public String saisirChoix() {
        System.out.println("1. Jack le Borgne");
        System.out.println("2. Bill Jambe-de-Bois");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
       
        String pirateChoisi = ""; 

        if (choix == 1) {
            pirateChoisi = "Jack le Borgne";
            System.out.println("Vous avez choisi : Jack le Borgne.");
        } else if (choix == 2) {
            pirateChoisi = "Bill Jambe-de-Bois";
            System.out.println("Vous avez choisi : Bill Jambe-de-Bois.");
        } else {
            System.out.println("Choix invalide. Vous n'avez pas sélectionné un personnage valide.");
        }

        return pirateChoisi;
    }



 
    // Méthode pour afficher le message lorsqu'un pirate tire une carte
    public void afficherTirageCarte(String pirate, String descriptionCarte) {
        System.out.println(pirate + " tire une carte de " + descriptionCarte);
    }

 
    public void afficherChoixZone(String pirate, String choix) {
        System.out.println(pirate + " choisit la carte  " + choix);
    }

        public void afficherJeuCarte(String pirate, String zone) {
        System.out.println(pirate + " joue une carte dans la zone de " + zone + ".");
    }

    // Méthode pour afficher l'effet appliqué par une carte (ici, on ajoute des points de popularité)
    public int afficherEffet(String pirate, int popularite) {
        System.out.println("Effet appliqué à " + pirate + ": +1 point de popularité.");
        return popularite + 1; 
    }

    // Méthode pour afficher le message de victoire
    public void afficherVictoire(String pirate) {
        System.out.println(pirate + " a gagné la partie !");
    }
}
