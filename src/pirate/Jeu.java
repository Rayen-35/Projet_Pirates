package pirate;

import java.util.Scanner;
import java.util.Random;

public class Jeu {
    // Attributs du jeu : joueur, adversaire, deck de cartes, affichage, etc.
    private Pirate joueur;
    private Pirate adversaire;
    private Deck deck;
    private Affichage affichage;
    private int round = 1; // compteur de tours
    private Random random;
    private Scanner scanner;

    // Constructeur pour initialiser les composants du jeu
    public Jeu(Pirate joueur, Pirate adversaire, Deck deck, Affichage affichage) {
        this.joueur = joueur;
        this.adversaire = adversaire;
        this.deck = deck;
        this.affichage = affichage;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    // Méthode principale pour démarrer le jeu
    public void demarrerJeu() {
        affichage.afficherDemarrage();

        // Choix du personnage par l'utilisateur
        int choix;
        do {
            affichage.afficherChoixPirate();
            choix = scanner.nextInt();
            scanner.nextLine(); // nettoyage du buffer
            if (choix != 1 && choix != 2) {
                affichage.afficherMessageInvalide();
            }
        } while (choix != 1 && choix != 2);

        // Attribution des pirates selon le choix
        this.joueur = choix == 1 ? new Pirate("Jack le Borgne") : new Pirate("Bill Jambe-de-Bois");
        this.adversaire = choix == 1 ? new Pirate("Bill Jambe-de-Bois") : new Pirate("Jack le Borgne");

        affichage.afficherConfirmationChoix(joueur.getNom());
        affichage.afficherDebutJeu();

        // Création d'un deck de 40 cartes avec différents types
        Carte[] deckCartes = new Carte[40];
        for (int i = 0; i < 40; i++) {
            if (i % 3 == 0) {
                deckCartes[i] = new CarteAttaque("Coup d'Épée", "Inflige 1 point de dégât", 1);
            } else if (i % 5 == 0) {
                deckCartes[i] = new CartePopularite("Discours Motivant", "Augmente la popularité de 1", 1);
            } else {
                EffetSpecial effet = EffetSpecial.values()[random.nextInt(EffetSpecial.values().length)];
                String nom = effet == EffetSpecial.DEFENSE ? "Parade Défensive" :
                             effet == EffetSpecial.SABOTAGE ? "Sabotage Caché" : "Attaque Féroce";
                String description = effet == EffetSpecial.DEFENSE ? 
                    "Réduit les dégâts de moitié si la prochaine attaque est une Double Attaque, sinon annule tous les dégâts d'une attaque normale" :
                    effet == EffetSpecial.SABOTAGE ? 
                    "Réduit la popularité de l'adversaire" : 
                    "Double les dégâts de la prochaine attaque";
                deckCartes[i] = new CarteSpeciale(nom, description, effet);
            }
        }
        this.deck = new Deck(deckCartes);

        // Donne 4 cartes au joueur au début du jeu
        String[] cartesJoueur = new String[4];
        for (int i = 0; i < 4; i++) {
            Carte carte = deck.piocher();
            joueur.ajouteCarte(carte);
            cartesJoueur[i] = carte.getNom() + " - " + carte.getDescription();
        }
        affichage.afficherCartesInitiales(joueur.getNom(), cartesJoueur);

        // Affiche les points de vie et popularité
        affichage.afficherPoints(joueur.getNom(), joueur.getPointsVie(), joueur.getPopularite(),
                adversaire.getNom(), adversaire.getPointsVie(), adversaire.getPopularite());

        // Boucle de jeu : tant qu'aucun joueur n'est KO ou ne gagne
        while (joueur.estVivant() && adversaire.estVivant() && !joueur.aGagne() && !adversaire.aGagne()) {
            jouerTour();
        }
    }

    // Méthode pour gérer un tour complet
    public void jouerTour() {
        affichage.afficherTour(round);

        // Le joueur pioche une carte s'il reste dans le deck
        if (!deck.estVide()) {
            Carte nouvelleCarteJoueur = deck.piocher();
            joueur.ajouteCarte(nouvelleCarteJoueur);
            affichage.afficherTirageCarte(joueur.getNom(), nouvelleCarteJoueur.getNom() + " - " + nouvelleCarteJoueur.getDescription());
        }

        // Affiche les cartes à choisir et attend le choix de l'utilisateur
        affichage.afficherChoixCarteAJouer(joueur.getNom(), joueur.getNombreCartes());
        affichage.afficherCartesDisponibles(joueur);

        int choix = scanner.nextInt() - 1;
        while (choix < 0 || choix >= joueur.getNombreCartes()) {
            affichage.afficherMessageChoixInvalide();
            choix = scanner.nextInt() - 1;
        }

        // Le joueur joue sa carte
        Carte carteJoueur = joueur.jouerCarte(choix);
        affichage.afficherChoixCarte(joueur.getNom(), carteJoueur.getNom() + " - " + carteJoueur.getDescription());
        affichage.afficherJeuCarte(joueur.getNom(), carteJoueur.getDescription(), carteJoueur.getZone());
        carteJoueur.effet(joueur, adversaire);

        // L'adversaire pioche 4 cartes depuis le deck
        String[] cartesAdversaire = new String[4];
        for (int i = 0; i < 4; i++) {
            Carte carte = deck.piocher();
            adversaire.ajouteCarte(carte);
            cartesAdversaire[i] = carte.getNom() + " - " + carte.getDescription();
        }

        // Vérifie si l'adversaire est KO
        if (!adversaire.estVivant() || joueur.aGagne()) {
            affichage.afficherVictoire(joueur.getNom());
            return;
        }

        // Tour de l’adversaire (joue aléatoirement)
        if (round == 1 || adversaire.getNombreCartes() > 0) {
            int choixAdversaire = random.nextInt(adversaire.getNombreCartes());
            Carte carteAdversaire = adversaire.jouerCarte(choixAdversaire);
            affichage.afficherChoixCarte(adversaire.getNom(), carteAdversaire.getNom() + " - " + carteAdversaire.getDescription());
            affichage.afficherJeuCarte(adversaire.getNom(), carteAdversaire.getDescription(), carteAdversaire.getZone());
            carteAdversaire.effet(adversaire, joueur);
        }

        // Vérifie si le joueur est KO
        if (!joueur.estVivant() || adversaire.aGagne()) {
            affichage.afficherVictoire(adversaire.getNom());
            return;
        }

        // Affiche les points après chaque tour
        affichage.afficherPoints(joueur.getNom(), joueur.getPointsVie(), joueur.getPopularite(),
                adversaire.getNom(), adversaire.getPointsVie(), adversaire.getPopularite());

        round++; // prochain tour
    }

    // Méthode main pour lancer le jeu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AffichageTexte affichage = new AffichageTexte();

        // Deck vide à la base, sera rempli dans demarrerJeu
        Deck deck = new Deck(new Carte[0]);

        // Création du jeu, les joueurs seront créés plus tard
        Jeu jeu = new Jeu(null, null, deck, affichage);

        // Lancement de la logique de jeu
        jeu.demarrerJeu();
    }
}
