package pirate;

	class AffichageTexte implements Affichage {
	    @Override
	    public void afficherDemarrage() {
	        System.out.println("OWWWW !!!! Bienvenue dans le jeu de cartes des pirates.");
	    }

	    @Override
	    public void afficherChoixPirate() {
	        System.out.println("Choisissez le personnage que vous voulez :\n  1. Jack le Borgne\n  2. Bill Jambe-de-Bois");
	    }

	    @Override
	    public void afficherConfirmationChoix(String pirate) {
	        System.out.println("Vous avez choisi : " + pirate + ".");
	    }

	    @Override
	    public void afficherMessageInvalide() {
	        System.out.println("Choix invalide. Vous n'avez pas sélectionné un personnage valide.");
	    }
	  
	    
	    
	    @Override
	    public void afficherDebutJeu() {
	        System.out.println("On commence le jeu :");
	    }

	    @Override
	    public void afficherCartesInitiales(String pirate, String[] cartes) {
	        System.out.println(pirate + " a les 4 cartes suivantes :");
	        for (int i = 0; i < cartes.length; i++) {
	            System.out.println((i + 1) + "- " + cartes[i]);
	        }
	    }

	    @Override
	    public void afficherTour(int round) {
	        System.out.println("\n Owwww !!! allez !!! Round " + round);
	    }

	    @Override
	    public void afficherTirageCarte(String pirate, String descriptionCarte) {
	        System.out.println(pirate + " tire une carte de " + descriptionCarte);
	    }

	    @Override
	    public void afficherChoixCarte(String pirate, String choix) {
	        System.out.println(pirate + " choisit la carte de " + choix);
	    }

	    @Override
	    public void afficherJeuCarte(String pirate, String description) {
	        System.out.println(pirate + " joue une carte dans la zone de " + description + ".");
	    }
	   
	    
	    
	    @Override
	    public void afficherEffet(String pirate, String effet) {
	        System.out.println("Effet appliqué à " + pirate + " : " + effet);
	    }

	    @Override
	    public void afficherPoints(String pirate1, int vie1, int pop1, String pirate2, int vie2, int pop2) {
	        System.out.println(pirate1 + " (Vie: " + vie1 + ", Popularité: " + pop1 + ")");
	        System.out.println(pirate2 + " (Vie: " + vie2 + ", Popularité: " + pop2 + ")");
	    }

	    @Override
	    public void afficherVictoire(String pirate) {
	        System.out.println(pirate + " a gagné la partie !");
	    }
	    
	    @Override
	    public void afficherDeckVide() {
	        System.out.println("Le deck est vide ! Aucun joueur ne peut plus piocher.");
	    }
	}

