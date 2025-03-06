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

        // Création d'un deck de 40 cartes avec nom et description
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

        // Distribution des cartes initiales au joueur
        String[] cartesJoueur = new String[4];
        for (int i = 0; i < 4; i++) {
            Carte carte = deck.piocher();
            joueur.ajouteCarte(carte);
            cartesJoueur[i] = carte.getNom() + " - " + carte.getDescription();
        }
        affichage.afficherCartesInitiales(joueur.getNom(), cartesJoueur);

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
        affichage.afficherTour(round);

        // Tour du joueur
        if (joueur.getNombreCartes() > 0) {
            Carte carteJoueur = joueur.jouerCarte(0);
            affichage.afficherChoixCarte(joueur.getNom(), carteJoueur.getNom() + " - " + carteJoueur.getDescription());
            affichage.afficherJeuCarte(joueur.getNom(), carteJoueur.getDescription(), carteJoueur.getZone());
            carteJoueur.effet(joueur, adversaire);
        }

        if (!adversaire.estVivant() || joueur.aGagne()) {
            affichage.afficherVictoire(joueur.getNom());
            return;
        }

        // Tour de l'adversaire
        if (adversaire.getNombreCartes() > 0) {
            Carte carteAdversaire = adversaire.jouerCarte(0);
            affichage.afficherChoixCarte(adversaire.getNom(), carteAdversaire.getNom() + " - " + carteAdversaire.getDescription());
            affichage.afficherJeuCarte(adversaire.getNom(), carteAdversaire.getDescription() , carteAdversaire.getZone());
            carteAdversaire.effet(adversaire, joueur);
        }

        if (!joueur.estVivant() || adversaire.aGagne()) {
            affichage.afficherVictoire(adversaire.getNom());
            return;
        }

        // Piocher une carte si possible
        if (!deck.estVide()) joueur.ajouteCarte(deck.piocher());
        if (!deck.estVide()) adversaire.ajouteCarte(deck.piocher());

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

        affichage.afficherDemarrage();
        affichage.afficherChoixPirate();
        int choix = scanner.nextInt();
        scanner.nextLine();

        Pirate joueur = choix == 1 ? new Pirate("Jack le Borgne") : new Pirate("Bill Jambe-de-Bois");
        Pirate adversaire = choix == 1 ? new Pirate("Bill Jambe-de-Bois") : new Pirate("Jack le Borgne");

        Carte[] deckCartes = new Carte[40];
        Random random = new Random();
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

        Deck deck = new Deck(deckCartes);
        Jeu jeu = new Jeu(joueur, adversaire, deck, affichage);
        jeu.demarrerJeu();
    }
}
