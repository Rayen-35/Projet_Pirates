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

	    public Jeu(Pirate joueur, Pirate adversaire, Deck deck, Affichage affichage) {
	        this.joueur = joueur;
	        this.adversaire = adversaire;
	        this.deck = deck;
	        this.affichage = affichage;
	        this.random = new Random();
	    }

	   
	    public void demarrerJeu() {
	       
	        affichage.afficherConfirmationChoix(joueur.getNom());
	        affichage.afficherDebutJeu();

	        // Création d'un deck de 40 cartes
	        Carte[] deckCartes = new Carte[40]; // on crée un tableau pouvant contenir des sous-classes de Carte
	        for (int i = 0; i < 40; i++) {
	            if (i % 3 == 0) {
	                deckCartes[i] = new CarteAttaque(1);
	            } else if (i % 3 == 1) {
	                deckCartes[i] = new CartePopularite(1);
	            } else {
	                deckCartes[i] = new CarteSpeciale(EffetSpecial.values()[random.nextInt(EffetSpecial.values().length)]);
	            }
	        }
	        this.deck = new Deck(deckCartes);
	        
	        afficherEtatJeu();
	        
	        while (joueur.estVivant() && adversaire.estVivant() && !joueur.aGagne() && !adversaire.aGagne()) {
	            if (deck.estVide()) {
	                affichage.afficherDeckVide();
	                break;
	            }
	            jouerTour();
	        }
	    }
	
	    public void jouerTour() {
	       

	        // Tour du joueur
	        if (joueur.getNombreCartes() > 0) {
	            Carte carteJoueur = joueur.jouerCarte(0);
	            affichage.afficherChoixCarte(joueur.getNom(), carteJoueur.getDescription());
	            affichage.afficherJeuCarte(joueur.getNom(), carteJoueur.getDescription());
	            carteJoueur.effet(joueur, adversaire);
	        }

	        if (!adversaire.estVivant() || joueur.aGagne()) {
	            affichage.afficherVictoire(joueur.getNom());
	            return;
	        }

	        // Tour de l'adversaire
	        if (adversaire.getNombreCartes() > 0) {
	            Carte carteAdversaire = adversaire.jouerCarte(0);
	            affichage.afficherChoixCarte(adversaire.getNom(), carteAdversaire.getDescription());
	            affichage.afficherJeuCarte(adversaire.getNom(), carteAdversaire.getDescription());
	            carteAdversaire.effet(adversaire, joueur);
	        }

	        if (!joueur.estVivant() || adversaire.aGagne()) {
	            affichage.afficherVictoire(adversaire.getNom());
	            return;
	        }

	        // Piocher une carte si possible
	        if (!deck.estVide()) joueur.ajouteCarte(deck.piocher());
	        if (!deck.estVide()) adversaire.ajouteCarte(deck.piocher());
	        affichage.afficherTour(round);
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

	     //  System.out.println("Choisissez votre pirate : 1. Jack le Borgne  2. Bill Jambe-de-Bois");
	        affichage.afficherDemarrage();
	        affichage.afficherChoixPirate();
	        int choix = scanner.nextInt();
	        scanner.nextLine();

	        Pirate joueur = choix == 1 ? new Pirate("Jack le Borgne") : new Pirate("Bill Jambe-de-Bois");
	        Pirate adversaire = choix == 1 ? new Pirate("Bill Jambe-de-Bois") : new Pirate("Jack le Borgne");

	        Carte[] deckCartes = {
	        	    new CarteAttaque(1),
	        	    new CartePopularite(1),
	        	    new CarteSpeciale(EffetSpecial.SABOTAGE),
	        	    new CarteSpeciale(EffetSpecial.DEFENSE)
	        	};

	        Deck deck = new Deck(deckCartes);
	        Jeu jeu = new Jeu(joueur, adversaire, deck, affichage);
	        jeu.demarrerJeu();
	        
	    }
	}
