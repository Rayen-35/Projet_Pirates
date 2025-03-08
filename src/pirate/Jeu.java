package pirate;

import java.util.Scanner;
import java.util.Random;

public class Jeu {
    private Pirate joueur;
    private Pirate adversaire;
    private Deck deck;
    private Affichage affichage;
    private int round = 1;
    private Random random;
    private Scanner scanner;

    public Jeu(Pirate joueur, Pirate adversaire, Deck deck, Affichage affichage) {
        this.joueur = joueur;
        this.adversaire = adversaire;
        this.deck = deck;
        this.affichage = affichage;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void demarrerJeu() {
        affichage.afficherDemarrage();
        int choix;
        do {
            affichage.afficherChoixPirate();
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix != 1 && choix != 2) {
                affichage.afficherMessageInvalide();
            }
        } while (choix != 1 && choix != 2);
        
        // Assignation des joueurs à l'attribut de la classe
        this.joueur = choix == 1 ? new Pirate("Jack le Borgne") : new Pirate("Bill Jambe-de-Bois");
        this.adversaire = choix == 1 ? new Pirate("Bill Jambe-de-Bois") : new Pirate("Jack le Borgne");

        affichage.afficherConfirmationChoix(joueur.getNom());
        affichage.afficherDebutJeu();

        // Création du deck avec 40 cartes
        Carte[] deckCartes = new Carte[40];
        for (int i = 0; i < 40; i++) {
            if (i % 3 == 0) {
                deckCartes[i] = new CarteAttaque("Coup d'Épée", "Inflige 1 point de dégât", 1);
            } else if (i % 3 == 1) {
                deckCartes[i] = new CartePopularite("Discours Motivant", "Augmente la popularité de 1", 1);
            } else {
                EffetSpecial effet = EffetSpecial.values()[random.nextInt(EffetSpecial.values().length)];
                String nom = effet == EffetSpecial.DEFENSE ? "Parade Défensive" : effet == EffetSpecial.SABOTAGE ? "Sabotage Caché" : "Attaque Féroce";
                String description = effet == EffetSpecial.DEFENSE ? "Réduit les dégâts de moitié si la prochaine attaque est une Double Attaque, sinon annule tous les dégâts d'une attaque normale" : effet == EffetSpecial.SABOTAGE ? "Réduit la popularité de l'adversaire" : "Double les dégâts de la prochaine attaque";
                deckCartes[i] = new CarteSpeciale(nom, description, effet);
            }
        }
        this.deck = new Deck(deckCartes);

        // Distribution des 4 cartes initiales au joueur
        String[] cartesJoueur = new String[4];
        for (int i = 0; i < 4; i++) {
            Carte carte = deck.piocher();
            joueur.ajouteCarte(carte);
            cartesJoueur[i] = carte.getNom() + " - " + carte.getDescription();
        }
        affichage.afficherCartesInitiales(joueur.getNom(), cartesJoueur);

        afficherEtatJeu();

        // Suppression de la vérification si le deck est vide
        while (joueur.estVivant() && adversaire.estVivant() && !joueur.aGagne() && !adversaire.aGagne()) {
            jouerTour(); // On continue les tours même si le deck est vide
        }
    }



    public void jouerTour() {
        affichage.afficherTour(round);

        // Piocher une nouvelle carte pour le joueur avant le choix
        if (!deck.estVide()) {
            Carte nouvelleCarteJoueur = deck.piocher();
            joueur.ajouteCarte(nouvelleCarteJoueur);
            affichage.afficherTirageCarte(joueur.getNom(), nouvelleCarteJoueur.getNom() + " - " + nouvelleCarteJoueur.getDescription());
        }

        // Affichage des cartes disponibles
        affichage.afficherChoixCarteAJouer(joueur.getNom(), joueur.getNombreCartes());
        affichage.afficherCartesDisponibles(joueur);

        // Saisie utilisateur pour le choix de carte
        int choix = scanner.nextInt() - 1;
        while (choix < 0 || choix >= joueur.getNombreCartes()) {
            affichage.afficherMessageChoixInvalide();
            choix = scanner.nextInt() - 1;
        }

        // Jouer la carte sélectionnée
        Carte carteJoueur = joueur.jouerCarte(choix);
        affichage.afficherChoixCarte(joueur.getNom(), carteJoueur.getNom() + " - " + carteJoueur.getDescription());
        affichage.afficherJeuCarte(joueur.getNom(), carteJoueur.getDescription(), carteJoueur.getZone());
        carteJoueur.effet(joueur, adversaire);
        
        String[] cartesAdversaire = new String[4];
        for (int i = 0; i < 4; i++) {
            Carte carte = deck.piocher();
            adversaire.ajouteCarte(carte);
            cartesAdversaire[i] = carte.getNom() + " - " + carte.getDescription();
        }
      //  affichage.afficherCartesInitiales(adversaire.getNom(), cartesAdversaire);
        if (!adversaire.estVivant() || joueur.aGagne()) {
            affichage.afficherVictoire(joueur.getNom());
            return;
        }

        // Tour de l'adversaire
        if  (round == 1 || adversaire.getNombreCartes() > 0) {

            int choixAdversaire = random.nextInt(adversaire.getNombreCartes());
            Carte carteAdversaire = adversaire.jouerCarte(choixAdversaire);
            affichage.afficherChoixCarte(adversaire.getNom(), carteAdversaire.getNom() + " - " + carteAdversaire.getDescription());
            affichage.afficherJeuCarte(adversaire.getNom(), carteAdversaire.getDescription(), carteAdversaire.getZone());
            carteAdversaire.effet(adversaire, joueur);
        }
        

        if (!joueur.estVivant() || adversaire.aGagne()) {
            affichage.afficherVictoire(adversaire.getNom());
            return;
        }

        afficherEtatJeu();
        round++;
    }


    private void afficherEtatJeu() {
        affichage.afficherPoints(joueur.getNom(), joueur.getPointsVie(), joueur.getPopularite(),
                                 adversaire.getNom(), adversaire.getPointsVie(), adversaire.getPopularite());
    }

    public static void main(String[] args) {
    	
    	    Scanner scanner = new Scanner(System.in);
    	    AffichageTexte affichage = new AffichageTexte();

    	    // Création d'un deck vide (sera rempli dans `demarrerJeu`)
    	    Deck deck = new Deck(new Carte[0]);

    	    // Initialisation du jeu avec un joueur et un adversaire qui seront définis dans `demarrerJeu`
    	    Jeu jeu = new Jeu(null, null, deck, affichage);

    	    // Démarrer le jeu, la méthode `demarrerJeu` gère la sélection du pirate et l'initialisation
    	    jeu.demarrerJeu();
    	}

    }

