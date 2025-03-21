package pirate;

// Classe qui gère tous les affichages en texte dans la console
class AffichageTexte implements Affichage {

    // Affiche le message de démarrage du jeu
    @Override
    public void afficherDemarrage() {
        System.out.println("OWWWW !!!! Bienvenue dans le jeu de cartes des pirates.");
    }

    // Invite l'utilisateur à choisir un pirate
    @Override
    public void afficherChoixPirate() {
        System.out.println("Choisissez le personnage que vous voulez :\n  1. Jack le Borgne\n  2. Bill Jambe-de-Bois");
    }

    // Affiche la confirmation du pirate choisi
    @Override
    public void afficherConfirmationChoix(String pirate) {
        System.out.println("Vous avez choisi : " + pirate + ".");
    }

    // Message si le choix du personnage est invalide
    @Override
    public void afficherMessageInvalide() {
        System.out.println("Choix invalide. Vous n'avez pas sélectionné un personnage valide.");
    }

    // Affiche le début du jeu
    @Override
    public void afficherDebutJeu() {
        System.out.println("On commence le jeu :");
    }

    // Affiche les 4 cartes de départ du joueur
    @Override
    public void afficherCartesInitiales(String pirate, String[] cartes) {
        System.out.println(pirate + " a les 4 cartes suivantes :");
        for (int i = 0; i < cartes.length; i++) {
            System.out.println((i + 1) + "- " + cartes[i]);
        }
    }

    // Affiche le numéro du round
    @Override
    public void afficherTour(int round) {
        System.out.println("\n Owwww !!! allez !!! Round " + round);
    }

    // Affiche la carte que le joueur vient de tirer
    @Override
    public void afficherTirageCarte(String pirate, String descriptionCarte) {
        System.out.println(pirate + " tire une carte de " + descriptionCarte);
    }

    // Affiche le choix de carte du joueur
    @Override
    public void afficherChoixCarte(String pirate, String choix) {
        System.out.println(pirate + " choisit la carte de " + choix);
    }

    // Affiche l'action de jouer une carte et sa zone
    @Override
    public void afficherJeuCarte(String pirate, String description, String zone) {
        System.out.println(pirate + " joue une carte dans la " + zone + " : " + description);
    }

    // Affiche l'invite pour que le joueur choisisse une carte à jouer
    @Override
    public void afficherChoixCarteAJouer(String pirate, int nombreCartes) {
        System.out.println(pirate + ", choisissez une carte à jouer (1 - " + nombreCartes + ") :");
    }

    // Affiche la liste des cartes actuellement dans la main du joueur
    @Override
    public void afficherCartesDisponibles(Pirate joueur) {
        for (int i = 0; i < joueur.getNombreCartes(); i++) {
            System.out.println((i + 1) + "- " + joueur.getCarte(i).getNom() + " - " + joueur.getCarte(i).getDescription());
        }
    }

    // Message si le choix de carte est invalide
    @Override
    public void afficherMessageChoixInvalide() {
        System.out.println("Choix invalide. Veuillez saisir un numéro valide.");
    }

    // Affiche les points de vie et de popularité de chaque pirate
    @Override
    public void afficherPoints(String pirate1, int vie1, int pop1, String pirate2, int vie2, int pop2) {
        System.out.println(pirate1 + " (Vie: " + vie1 + ", Popularité: " + pop1 + ")");
        System.out.println(pirate2 + " (Vie: " + vie2 + ", Popularité: " + pop2 + ")");
    }

    // Affiche le gagnant de la partie
    @Override
    public void afficherVictoire(String pirate) {
        System.out.println(pirate + " a gagné la partie !");
    }
}
