package glaces;

import geometrie.Point;

/**
 * Created by Valentin.
 */

public class Pingouin {
	private int oceanWidth;
	private int oceanHeight;

	private static final int TAILLE_PINGOUIN = 16;
	private Point hAd;
	private Point bAg;
	private boolean vivant;

	/**
	 * Constructeur (caractéristiques de l'océan requis en paramètre)
	 *
	 * @param oceanWidth  double : largeur de l'océan
	 * @param oceanHeight double : hauteur de l'océan
	 */
	public Pingouin(int oceanWidth, int oceanHeight) {
		assert (!(oceanWidth <= TAILLE_PINGOUIN || oceanHeight <= TAILLE_PINGOUIN)) : "La taille de l'océan est invalide (minimum " + TAILLE_PINGOUIN + "px requis)";
		this.oceanWidth = oceanWidth;
		this.oceanHeight = oceanHeight;

		hAd = new Point(oceanHeight - TAILLE_PINGOUIN, oceanWidth);
		bAg = new Point(oceanHeight, oceanWidth - TAILLE_PINGOUIN);
		vivant = true;
	}

	/**
	 * @return Point : en haut à droite
	 */
	public Point getHautAdroite() {
		return hAd;
	}

	/**
	 * @return Point : en bas à gauche
	 */
	public Point getBasAgauche() {
		return bAg;
	}

	/**
	 * Déplacement du pingouin dans l'océan
	 * (Rappel tout l'exercice utilise des axes inversés, voir pdf TP1)
	 *
	 * @param v double : déplacement vertical
	 * @param h double : déplacement horizontal
	 */
	public void deplacer(double v, double h) {
		if (hAd.getAbscisse() + v <= 0.) {                                // Positionnement à l'abscisse 0
			bAg.deplacer(-hAd.getAbscisse(), 0);
			hAd.deplacer(-hAd.getAbscisse(), 0);
		} else if (bAg.getAbscisse() + v >= oceanHeight) {                // Positionnement à l'abscisse oceanHeight
			hAd.deplacer(oceanHeight - bAg.getAbscisse(), 0);
			bAg.deplacer(oceanHeight - bAg.getAbscisse(), 0);
		} else {
			hAd.deplacer(v, 0);
			bAg.deplacer(v, 0);
		}

		if (bAg.getOrdonnee() + h <= 0.) {                                // Positionnement à l'ordonnée 0
			hAd.deplacer(0, -bAg.getOrdonnee());
			bAg.deplacer(0, -bAg.getOrdonnee());
		} else if (hAd.getOrdonnee() + h >= oceanWidth) {                // Positionnement à l'ordonnée oceanWidth
			bAg.deplacer(0, oceanWidth - hAd.getOrdonnee());
			hAd.deplacer(0, oceanWidth - hAd.getOrdonnee());
		} else {
			hAd.deplacer(0, h);
			bAg.deplacer(0, h);
		}
	}

	/**
	 * Retourne l'état du pingouin
	 *
	 * @return boolean : True : vivant, False : mort
	 */
	public boolean isVivant() {
		return vivant;
	}

	/**
	 * /!\ Méthode violente
	 * Tue le pingouin
	 */
	public void tuerLePingouin() {
		vivant = false;
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Pingouin :"
				+ "\n - " + bAg
				+ "\n - " + hAd;
	}

}
