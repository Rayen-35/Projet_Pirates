package pirate;

public interface Affichage {
	   void afficherDemarrage();
	    void afficherChoixPirate();
	    void afficherConfirmationChoix(String pirate);
	    void afficherMessageInvalide();
	    void afficherDebutJeu();
	    void afficherCartesInitiales(String pirate, String[] cartes);
	    void afficherTour(int round);
	    void afficherTirageCarte(String pirate, String descriptionCarte);
	    void afficherChoixCarte(String pirate, String choix);
	    void afficherJeuCarte(String pirate, String description, String zone);
	    void afficherEffet(String pirate, String effet);
	    void afficherPoints(String pirate1, int vie1, int pop1, String pirate2, int vie2, int pop2);
	    void afficherVictoire(String pirate);
	    void afficherDeckVide();
}
