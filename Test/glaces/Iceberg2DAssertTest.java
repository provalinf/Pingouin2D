package glaces;


import geometrie.Point;

/**
 * Created by Valentin.
 * <p>
 * Tests créés à partir des TU
 * Taux de couverture : identique Iceberg2DUnitTest.java
 */
public class Iceberg2DAssertTest {
	private static final Point n1 = new Point(6, 1);
	private static final Point n2 = new Point(1, 10);

	public static void main(String[] args) {
		testCoordNegativeInterditeIceberg2D();
		testCoordInvalideInterditeIceberg2D();
		testIceberg2D();
		testSecondIceberg2D();
		testCaptureIsCapture();
		testHauteur();
		testLargeur();
		testSurface();
		testCollision();
		testEstPlusGrosQue();
		testCentre();
		testfondre();
		testCasserDroite();
		testCasserGauche();
		testCasserhaut();
		testCasserBas();
		testToString();
	}

	private static void testCoordNegativeInterditeIceberg2D() {
		try {
			new Iceberg2D(new Point(-6, 1), new Point(-1, 10));
			//noinspection ConstantConditions
			assert (false) : "Exception non retournée";
		} catch (AssertionError exception) {
			assert (exception.getMessage().equals("Les coordonnées négatives ne sont pas autorisées !")) : "Erreur " + exception.getMessage();
		}

		try {
			new Iceberg2D(new Point(6, -1), new Point(1, -10));
			//noinspection ConstantConditions
			assert (false) : "Exception non retournée";
		} catch (AssertionError exception) {
			assert (exception.getMessage().equals("Les coordonnées négatives ne sont pas autorisées !")) : "Erreur " + exception.getMessage();
		}
	}

	private static void testCoordInvalideInterditeIceberg2D() {
		try {
			new Iceberg2D(new Point(1, 10), new Point(6, 1));
			//noinspection ConstantConditions
			assert (false) : "Exception non retournée";
		} catch (AssertionError exception) {
			assert (exception.getMessage().equals("Les coordonnées des points sont incorrects. Le premier point doit être en bas à gauche, le second en haut à droite !")) : "Erreur " + exception.getMessage();
		}
	}

	private static void testIceberg2D() {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		assert (n1.toString().equals(iceberg.coinEnBasAGauche().toString())) : "Le point en bas à gauche ne correspond pas au paramètre";
		assert (n2.toString().equals(iceberg.coinEnHautADroite().toString())) : "Le point en haut à droite ne correspond pas au paramètre";
	}

	private static void testSecondIceberg2D() {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 5), new Point(2, 10));
		Iceberg2D icebergFusion = new Iceberg2D(icebergUn, icebergDeux);
		assert (new Point(1, 10).toString().equals(icebergFusion.coinEnHautADroite().toString())) : "Coin en haut à droite invalide";
		assert (new Point(8, 1).toString().equals(icebergFusion.coinEnBasAGauche().toString())) : "Coin en bas à gauche invalide";

		icebergFusion = new Iceberg2D(icebergDeux, icebergUn);
		assert (new Point(1, 10).toString().equals(icebergFusion.coinEnHautADroite().toString())) : "Coin en haut à droite invalide";
		assert (new Point(8, 1).toString().equals(icebergFusion.coinEnBasAGauche().toString())) : "Coin en bas à gauche invalide";
	}

	private static void testCaptureIsCapture() {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		assert (!iceberg.isCapture()) : "Non capturé";
		iceberg.capturer();
		assert (iceberg.isCapture()) : "Capturé";
	}

	private static void testHauteur() {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		assert (5. == iceberg.hauteur()) : "Hauteur";
	}

	private static void testLargeur() {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		assert (9. == iceberg.largeur()) : "Largeur";
	}

	private static void testSurface() {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		assert (iceberg.hauteur() * iceberg.largeur() == iceberg.surface()) : "Surface";
	}

	private static void testCollision() {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 10));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 5), new Point(3, 12));
		Iceberg2D icebergTrois = new Iceberg2D(new Point(10, 4), new Point(9, 15));
		Iceberg2D icebergQuatre = new Iceberg2D(new Point(5, 2), new Point(2, 9));
		Iceberg2D icebergCinq = new Iceberg2D(new Point(4, 7), new Point(2, 15));
		Iceberg2D icebergSix = new Iceberg2D(new Point(10, 6), new Point(1, 10));

		assert (icebergUn.collision(icebergDeux)) : "Collision iceberg n°1 avec n°2";
		assert (icebergDeux.collision(icebergUn)) : "Collision iceberg n°2 avec n°1";

		assert (icebergDeux.collision(icebergCinq)) : "Collision iceberg n°2 avec n°5";
		assert (icebergCinq.collision(icebergDeux)) : "Collision iceberg n°5 avec n°2";

		assert (icebergUn.collision(icebergQuatre)) : "Collision iceberg n°1 avec n°4";

		assert (icebergDeux.collision(icebergSix)) : "Collision iceberg n°2 avec n°6";
		assert (icebergSix.collision(icebergDeux)) : "Collision iceberg n°6 avec n°2";

		assert (!icebergTrois.collision(icebergDeux)) : "Pas de collision iceberg n°2 avec n°3";
	}

	private static void testEstPlusGrosQue() {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), n2);
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 1), n2);

		assert (!icebergUn.estPlusGrosQue(icebergDeux)) : "Iceberg n°1 est plus gros que Iceberg n°2";
		assert (icebergDeux.estPlusGrosQue(icebergUn)) : "Iceberg n°2 est plus gros que Iceberg n°1";
	}

	private static void testCentre() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));
		assert (new Point(3.5, 5.5).toString().equals(iceberg.centre().toString())) : "Coordonnées du centre";
	}

	private static void testfondre() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.fondre(0);
		iceberg.fondre(1.5);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "Fondre en bas à gauche 0 & 1";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "Fondre en haut à droite 0 & 1";

		iceberg.fondre(0.4);
		assert (new Point(5.5, 1.9).toString().equals(iceberg.coinEnBasAGauche().toString())) : "Fondre en bas à gauche";
		assert (new Point(1.5, 9.1).toString().equals(iceberg.coinEnHautADroite().toString())) : "Fondre en haut à droite";
	}

	private static void testCasserDroite() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserDroite(0);
		iceberg.casserDroite(1.5);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserDroite en bas à gauche 0 & 1";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserDroite en haut à droite 0 & 1";

		iceberg.casserDroite(0.1);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserDroite en bas à gauche";
		assert (new Point(1, 9.1).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserDroite en haut à droite";
	}

	private static void testCasserGauche() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserGauche(0);
		iceberg.casserGauche(1.5);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserGauche en bas à gauche 0 & 1";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserGauche en haut à droite 0 & 1";

		iceberg.casserGauche(0.1);
		assert (new Point(6, 1.9).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserGauche en bas à gauche";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserGauche en haut à droite";
	}

	private static void testCasserhaut() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserHaut(0);
		iceberg.casserHaut(1.5);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserHaut en bas à gauche 0 & 1";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserHaut en haut à droite 0 & 1";

		iceberg.casserHaut(0.1);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserHaut en bas à gauche";
		assert (new Point(1.5, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserHaut en haut à droite";
	}

	private static void testCasserBas() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserBas(0);
		iceberg.casserBas(1.5);
		assert (new Point(6, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserBas en bas à gauche 0 & 1";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserBas en haut à droite 0 & 1";

		iceberg.casserBas(0.1);
		assert (new Point(5.5, 1).toString().equals(iceberg.coinEnBasAGauche().toString())) : "CasserBas en bas à gauche";
		assert (new Point(1, 10).toString().equals(iceberg.coinEnHautADroite().toString())) : "CasserBas en haut à droite";
	}

	private static void testToString() {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));
		assert (iceberg.toString().equals("Iceberg :\n - <6.0,1.0>\n - <1.0,10.0>"));
	}
}
