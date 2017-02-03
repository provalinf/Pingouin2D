package glaces;

import geometrie.Point;

import java.util.Random;

/**
 * Created by Valentin.
 */
public class Ocean {
	private int height;
	private int width;

	private Iceberg2D[] icebergs;

	private static Random random;

	/**
	 * Constructeur par défaut
	 * Initialise le nombre d'iceberg à 2
	 * la largeur à 20 et la hauteur à 10.
	 */
	public Ocean() {
		this(2, 20, 10);
	}

	/**
	 * Constructeur
	 *
	 * @param nbIceberg nombre d'iceberg
	 * @param width     largeur de l'océan
	 * @param height    hauteur de l'océan
	 */
	public Ocean(int nbIceberg, int width, int height) {
		this.width = width;
		this.height = height;

		icebergs = new Iceberg2D[nbIceberg];
		random = new Random();
		for (int i = 0; i < icebergs.length; i++) {
			try {
				Point bAg = randPointIceberg();
				icebergs[i] = new Iceberg2D(bAg, randPointIceberg(bAg));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return hauteur de l'océan
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return largeur de l'océan
	 */
	public int getWidth() {
		return width;
	}

	public void getCount() {
		System.out.println("Executer la méthode getNbIceberg()");
	}

	/**
	 * @return le nombre d'iceberg dans l'océan
	 */
	public int getNbIceberg() {
		return icebergs.length;
	}

	/**
	 * Méthode par défaut pour la génération d'un point
	 * situé dans l'océan à destination d'un iceberg
	 *
	 * @return Point
	 */
	private Point randPointIceberg() {
		return randPointIceberg(new Point(0, 0));
	}

	/**
	 * Génération d'un point situé dans l'océan à destination d'un iceberg
	 * avec comme contrainte un point en bas à gauche d'un iceberg
	 * pour la génération du point en haut à droite
	 *
	 * @param bAg point pour contrainte (coord à 0 pour annuler la contrainte)
	 * @return Point
	 */
	private Point randPointIceberg(Point bAg) {
		double absMin = 0;
		double absMax = bAg.getAbscisse() == 0 ? height : bAg.getAbscisse();
		double ordMin = bAg.getOrdonnee(), ordMax = width;
		double abscisse = absMin + (absMax - absMin) * random.nextDouble();
		double ordonnee = ordMin + (ordMax - ordMin) * random.nextDouble();
		return new Point(abscisse, ordonnee);
	}

	public void getColors() {
		System.out.println("Executer la méthode getMatriceOcean()");
	}

	/**
	 * Génère une matrice 2D d'entier
	 * avec 0 pour la représentation de l'eau
	 * puis 1 pour la représentation d'un iceberg
	 *
	 * @return matrice de l'océan
	 */
	public int[][] getMatriceOcean() {
		int[][] result = new int[height][width];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = 0;
			}
		}

		for (Iceberg2D iceberg : icebergs) {
			Point bAg = iceberg.coinEnBasAGauche();
			Point hAd = iceberg.coinEnHautADroite();
			for (int i = (int) hAd.getAbscisse(); i < (int) bAg.getAbscisse() + 1; i++) {
				for (int j = (int) bAg.getOrdonnee(); j < (int) hAd.getOrdonnee() + 1; j++) {
					result[i][j] = 1;
				}
			}
		}
		return result;
	}

	/**
	 * Fait fondre tous les icebergs de l'océan
	 *
	 * @param fr ]0..1[ facteur de réduction en pourcentage
	 */
	public void fondre(double fr) {
		for (Iceberg2D iceberg : icebergs) {
			iceberg.fondre(fr);
		}
	}

	public void goDown() {

	}
}
