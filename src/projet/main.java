package projet;

public class main {

    public static void main(String[] args) {
        affichage affichage = new affichage();

      
        affichage.demarrerJeu();
        String pirateChoisi = affichage.saisirChoix();

   
        int popularitePirate1 = 0;
        int popularitePirate2 = 0;

    

      
        int tour = 1;
        while (true) {
            System.out.println("=== Tour " + tour + " ===");

           
            String pirate1 = "Jack le Borgne";
            String pirate2 = "Bill Jambe-de-Bois";

            affichage.afficherTirageCarte(pirate1, "popularité");

           
            affichage.afficherChoixZone(pirate1, "de popularité");

    
            affichage.afficherJeuCarte(pirate1, "Popularité");

        
            popularitePirate1 = affichage.afficherEffet(pirate1, popularitePirate1);

           
            System.out.println("Points de popularité après le tour de " + pirate1 + ": " + popularitePirate1);
            System.out.println("Points de popularité après le tour de " + pirate2 + ": " + popularitePirate2);

           
            if (popularitePirate1 >= 5) {
                affichage.afficherVictoire(pirate1);
                break;  
            }

           
            affichage.afficherTirageCarte(pirate2, "popularité");

     
            affichage.afficherChoixZone(pirate2, "de popularité");

          
            affichage.afficherJeuCarte(pirate2, "Popularité");

         
            popularitePirate2 = affichage.afficherEffet(pirate2, popularitePirate2);

          
            System.out.println("Points de popularité après le tour de " + pirate1 + ": " + popularitePirate1);
            System.out.println("Points de popularité après le tour de " + pirate2 + ": " + popularitePirate2);

            // Vérification de la victoire pour Pirate 2
            if (popularitePirate2 >= 5) {
                affichage.afficherVictoire(pirate2);
                break;  
            }

            tour++; 
        }
    }
}
