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
	private static final double SURF_ICE_MIN = 2;
	private Pingouin pingouin;

	private static Random random;

	/**
	 * Constructeur par défaut
	 * Initialise l'océan avec :
	 * - nombre d'iceberg à 2
	 * - largeur à 40
	 * - hauteur à 20
	 */
	public Ocean() {
		this(2, 40, 20);
	}

	/**
	 * Constructeur requis pour exercice
	 *
	 * @param nbIceberg int : nombre d'iceberg
	 * @param width     int : largeur de l'océan
	 * @param height    int : hauteur de l'océan
	 */
	public Ocean(int nbIceberg, int width, int height) {
		this(new Iceberg2D[nbIceberg], width, height, new Random());
		generateIcebergs();
	}

	/**
	 * Constructeur permettant de tester toutes les méthodes de la classe
	 *
	 * @param icebergs Iceberg2D[] : tableau d'icebergs
	 * @param width    int : largeur de l'océan
	 * @param height   int : hauteur de l'océan
	 * @param rand     Random : class Random
	 */
	public Ocean(Iceberg2D[] icebergs, int width, int height, Random rand) {
		this.width = width;
		this.height = height;
		this.icebergs = icebergs;
		random = rand;
		pingouin = new Pingouin(width, height);
	}

	/**
	 * @return int : hauteur de l'océan
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return int : largeur de l'océan
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Dépréciée, utiliser getNbIceberg()
	 */
	public void getCount() {
		System.out.println("Depreciee, utiliser getNbIceberg()");
	}

	/**
	 * @return int : nombre d'iceberg dans l'océan
	 */
	public int getNbIceberg() {
		return icebergs.length;
	}

	/**
	 * Génère des icebergs de taille aléatoire conformes
	 */
	public void generateIcebergs() {
		if (icebergs[0] != null) return;
		for (int i = 0; i < icebergs.length; i++) {
			Point bAg = randPointIceberg();
			icebergs[i] = new Iceberg2D(bAg, randPointIceberg(bAg));
		}
	}

	/**
	 * Génère un pingouin dans l'océan
	 *
	 * @return Pingouin : le pingouin créé
	 */
	public Pingouin getPingouin() {
		return pingouin;
	}

	/**
	 * Surcharge randPointIceberg(Point bAg)
	 * Génère un point situé dans l'océan à destination d'un iceberg
	 *
	 * @return Point : bas à gauche
	 */
	private Point randPointIceberg() {
		return randPointIceberg(new Point(0, 0));
	}

	/**
	 * Génère un point situé dans l'océan à destination d'un iceberg
	 * avec comme contrainte un paramètre.
	 * Mettre en contrainte un point en bas à gauche,
	 * pour générer un point en haut à droite.
	 *
	 * @param bAg Point : contrainte (coord à 0,0 pour annuler la contrainte)
	 * @return Point : bas à gauche si contrainte à 0,0, sinon haut à droite
	 */
	private Point randPointIceberg(Point bAg) {
		double absMin = 0;
		double absMax = bAg.getAbscisse() == 0 ? height : bAg.getAbscisse();
		double ordMin = bAg.getOrdonnee(), ordMax = width;
		double abscisse = absMin + (absMax - absMin) * random.nextDouble();
		double ordonnee = ordMin + (ordMax - ordMin) * random.nextDouble();
		return new Point(abscisse, ordonnee);
	}

	/**
	 * Déprécié, utiliser getMatriceOcean()
	 */
	public void getColors() {
		System.out.println("Deprecie, utiliser getMatriceOcean()");
	}

	/**
	 * Génère une matrice 2D d'entier avec :
	 * - 0 pour la représentation de l'eau
	 * - 1 pour la représentation d'iceberg
	 * - 2 pour la représentation de pingouin vivant
	 * - 3 pour la représentation d'un pingouin mort
	 *
	 * @return int[][] : matrice de l'océan
	 */
	public int[][] getMatriceOcean() {
		int[][] result = new int[height][width];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = 0;
			}
		}

		for (Iceberg2D iceberg : icebergs) {
			if (!iceberg.isCapture()) {
				Point bAg = iceberg.coinEnBasAGauche();
				Point hAd = iceberg.coinEnHautADroite();
				for (int i = (int) hAd.getAbscisse(); i < (int) bAg.getAbscisse() + 1; i++) {
					for (int j = (int) bAg.getOrdonnee(); j < (int) hAd.getOrdonnee() + 1; j++) {
						result[i][j] = 1;
					}
				}
			}
		}

		Point bAg = pingouin.getBasAgauche();
		Point hAd = pingouin.getHautAdroite();
		for (int i = (int) hAd.getAbscisse(); i < (int) bAg.getAbscisse(); i++) {
			for (int j = (int) bAg.getOrdonnee(); j < (int) hAd.getOrdonnee(); j++) {
				result[i][j] = pingouin.isVivant() ? 2 : 3;
			}
		}

		return result;
	}

	/**
	 * Déclenche la fonte de tous les icebergs de l'océan
	 *
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
	 */
	public void fondre(double fr) {
		for (Iceberg2D iceberg : icebergs) {
			if (!iceberg.isCapture()) {
				iceberg.fondre(fr);
			}
		}
	}

	/**
	 * Très sale, mais il fallait donner tous les éléments du TP en même temps.
	 * Une Iceberg et Pingouin héritant de Rectangle aurait été plus logique et propre.
	 */
	public void collisionPigouinIceberg() {
		for (Iceberg2D iceberg : icebergs) {
			if (!iceberg.isCapture() && iceberg.collision(new Iceberg2D(pingouin.getBasAgauche(), pingouin.getHautAdroite()))) {
				iceberg.capturer();
			}
		}
	}

	/**
	 * Vérifie si la surface de tous les Icebergs
	 * est supérieur à la surface minimum
	 * qui considère un iceberg fondu.
	 * Si un iceberg est fondu, le pingouin est tué
	 */
	public void verifSurfaceMinIce() {
		for (Iceberg2D iceberg : icebergs) {
			if (!iceberg.isCapture() && iceberg.surface() < SURF_ICE_MIN) {
				pingouin.tuerLePingouin();
			}
		}
	}

	/**
	 * Vérifie s'il reste un iceberg
	 * qui n'a pas été attrapé par le pingouin
	 *
	 * @return boolean : True : il en reste, False : sinon
	 */
	public boolean verifResteIceberg() {
		for (Iceberg2D iceberg : icebergs) {
			if (!iceberg.isCapture()) return true;
		}
		return false;
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "NbIceberg : " + getNbIceberg()
				+ "\n Hauteur océan : " + height
				+ "\n Largeur océan : " + width;
	}
}
