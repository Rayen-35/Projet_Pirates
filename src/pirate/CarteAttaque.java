package pirate;


public class CarteAttaque extends Carte {
	    private int degats;

	    public CarteAttaque(int degats) {
	        super("-" + degats + " Vie");
	        this.degats = degats;
	    }

	    @Override
	    public void effet(Pirate pirate, Pirate adversaire) {
	        
	        if (pirate.estDoubleAttaqueActive()) {
	            degats *= 2;
	            pirate.desactiverDoubleAttaque(); // Désactiver après l'utilisation
	            System.out.println(pirate.getNom() + " utilise Double Attaque ! Dégâts doublés : -" + degats + " Vie");
	        }

	       
	    	adversaire.subitAttaque(degats);
	    }
	}


