import java.text.DecimalFormat;

public class Main {
	// Formule de base homme : (V*t*0.8) / (0.7 * m)
	// Formule de base femme : (V*t*0.8) / (0.6 * m)
	// Avec V = volume en ml de la boisson
	// Avec t =  degré d'alcool en %, donc 12° = 0,12
	// 0.8 est une constante, la densité de l'alcool
	//  0.7 est le coefficient de diffusion pour un homme, 0.6 pour la femme
	//
	// Limite à 0,5g/L
	
	public static final double DENSITE_ALCOOL = 0.8;
	public static final double COEF_DIFFUSION_HOMME = 0.7;
	public static final double COEF_DIFFUSION_FEMME = 0.6;
	public static final double COEF_ELIMINATION_HOMME = (0.1/60); // Un homme élimine entre 0,1 et 0,15g/L par heure
	public static final double COEF_ELIMINATION_FEMME = (0.085/60); // Un homme élimine entre 0,1 et 0,15g/L par heure
	
	public static double quantiteAlcoolHomme(double volumeMililitre, double degreAlcool, double kg) {
			return ( (volumeMililitre * degreAlcool * DENSITE_ALCOOL) / (COEF_DIFFUSION_HOMME * kg) );
	}
	
	public static double quantiteAlcoolFemme(double volumeMililitre, double degreAlcool, double kg) {
		return ( (volumeMililitre * degreAlcool * DENSITE_ALCOOL) / (COEF_DIFFUSION_FEMME * kg) );
	}
	
	public static double quantiteAlcoolHomme(double volumeMililitre, double degreAlcool, double kg, double verres) {
		return ( (verres * (volumeMililitre * degreAlcool * DENSITE_ALCOOL) ) / (COEF_DIFFUSION_HOMME * kg) );
}

public static double quantiteAlcoolFemme(double volumeMililitre, double degreAlcool, double kg, double verres) {
	return ( ( (verres * volumeMililitre * degreAlcool * DENSITE_ALCOOL) ) / (COEF_DIFFUSION_FEMME * kg) );
}
	
	public static double eliminationAlcoolHomme(double minutes) {
		return (COEF_ELIMINATION_HOMME * minutes);
	}
	
	public static double eliminationAlcoolFemme(double minutes) {
		return (COEF_ELIMINATION_FEMME * minutes);
	}
	
	public static double eliminationAlcoolHomme(double minutes, boolean aManger) {
		if(minutes == 0) {
			return eliminationAlcoolHomme(minutes);
		}
		if(aManger) {
			return (COEF_ELIMINATION_HOMME * (minutes - 60));
		}
		else {
			return (COEF_ELIMINATION_HOMME * (minutes - 30));			
		}
	}
	
	public static double eliminationAlcoolFemme(double minutes, boolean aManger) {
		if(minutes == 0) {
			return eliminationAlcoolFemme(minutes);
		}
		if(aManger) {
			return (COEF_ELIMINATION_FEMME * (minutes - 60));
		}
		else {
			return (COEF_ELIMINATION_FEMME * (minutes - 30));			
		}
	}
	
	public static double calculHomme(double volumeMililitre, double degreAlcool, double  kg, double minutes) {
		return (quantiteAlcoolHomme(volumeMililitre, degreAlcool, kg) - eliminationAlcoolHomme(minutes));
	}
	
	public static double calculFemme(double volumeMililitre, double degreAlcool, double  kg, double minutes) {
		return (quantiteAlcoolFemme(volumeMililitre, degreAlcool, kg) - eliminationAlcoolFemme(minutes));
	}
	
	public static double calculHomme(double volumeMililitre, double degreAlcool, double  kg, double verres, double minutes) {
		return (quantiteAlcoolHomme(volumeMililitre, degreAlcool, kg, verres) - eliminationAlcoolHomme(minutes));
	}
	
	public static double calculFemme(double volumeMililitre, double degreAlcool, double  kg, double verres, double minutes) {
		return (quantiteAlcoolFemme(volumeMililitre, degreAlcool, kg, verres) - eliminationAlcoolFemme(minutes));
	}
	
	public static double calculHomme(double volumeMililitre, double degreAlcool, double  kg, double minutes, boolean aManger) {
		return (quantiteAlcoolHomme(volumeMililitre, degreAlcool, kg) - eliminationAlcoolHomme(minutes, aManger));
	}
	
	public static double calculFemme(double volumeMililitre, double degreAlcool, double  kg, double minutes, boolean aManger) {
		return (quantiteAlcoolFemme(volumeMililitre, degreAlcool, kg) - eliminationAlcoolFemme(minutes, aManger));
	}
	
	public static double calculHomme(double volumeMililitre, double degreAlcool, double  kg, double verres, double minutes, boolean aManger) {
		return (quantiteAlcoolHomme(volumeMililitre, degreAlcool, kg, verres) - eliminationAlcoolHomme(minutes, aManger));
	}
	
	public static double calculFemme(double volumeMililitre, double degreAlcool, double  kg, double verres, double minutes, boolean aManger) {
		return (quantiteAlcoolFemme(volumeMililitre, degreAlcool, kg, verres) - eliminationAlcoolFemme(minutes, aManger));
	}
	public static void main(String[] args) {
		DecimalFormat numberFormat = new DecimalFormat("#0.00");
		DecimalFormat numberFormat2 = new DecimalFormat("#0.000");
		boolean aManger = true;
		double volumeAlcoolIngeree = 150;
		double degreeAlcoolIngeree = 0.12;
		double poids = 75;
		double nombreDeVerres = 2;
		double tempsPasseeDepuis = 60;
		
		double xx = quantiteAlcoolHomme(volumeAlcoolIngeree, degreeAlcoolIngeree, poids, nombreDeVerres);
		double xxx = quantiteAlcoolHomme(330, 0.02, 75);
	    double y = eliminationAlcoolHomme(tempsPasseeDepuis, aManger);
		double z = calculHomme(volumeAlcoolIngeree, degreeAlcoolIngeree, poids, nombreDeVerres, tempsPasseeDepuis, aManger);
		double x = xx;
		
/*		System.out.println(x);
		System.out.println(xx);
		System.out.println(xxx);
		System.out.println("");
*/	    
		System.out.println("Quantitée ingérée = " + volumeAlcoolIngeree  * nombreDeVerres +"ml.");
		System.out.println("Degré de l'alcool = " + numberFormat.format((degreeAlcoolIngeree * 100)) + "%");
		System.out.println("Poids : " + poids + "kg.");
		System.out.println("Nombres de verres : " + nombreDeVerres + " verres de " + volumeAlcoolIngeree + " ml chacun.");
		System.out.println("Temps passé depuis premier verre ingéré : " + tempsPasseeDepuis + " minutes.");
		
		System.out.println("Quantité d'alcool ingérée = " + numberFormat.format(x) + "g/L");
		System.out.println("Elimination naturelle = " + numberFormat.format(y) + "g/L");
		System.out.println("Quantitée d'alcool actuelle = " + numberFormat2.format(z) + "g/L   (nombre complet =>" + (z) + (")"));
		if(z > 0.5) {
			System.out.println("Au dessus de la limite légale ! (0.5g/L)");
		}
		else {
			System.out.println("En dessous de la limite légale ! (0.5g/L)");
		}
	}
}
