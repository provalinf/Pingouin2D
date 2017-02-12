package glaces;

import geometrie.Point;

import java.util.Random;

/**
 * Created by Valentin.
 * <p>
 * Tests créés à partir des TU
 * Taux de couverture : identique OceanUnitTest.java
 */
public class OceanAssertTest {
	private static final int TAILLE_PINGOUIN = 16;
	private static final double SURF_ICE_MIN = 2;

	public static void main(String[] args) {
		testOcean();
		testOcean2();
		testGetPingouin();
		testOcean3();
		testgenerateIceberg();
		testfondre();
		testCollisionPingouinIceberg();
		testVerifSurfaceMinIce();
		testVerifResteIceberg();
		testToString();
	}

	private static void testOcean() {
		Ocean ocean = new Ocean();
		assert (2 == ocean.getNbIceberg()) : "Nb iceberg";
		assert (40 == ocean.getWidth()) : "Width";
		assert (20 == ocean.getHeight()) : "Height";
	}

	private static void testOcean2() {
		Ocean ocean = new Ocean(2, 40, 20);
		assert (2 == ocean.getNbIceberg()) : "Nb iceberg";
		assert (40 == ocean.getWidth()) : "Width";
		assert (20 == ocean.getHeight()) : "Height";
	}

	private static void testGetPingouin() {
		Ocean ocean = new Ocean(2, 40, 20);
		Pingouin pingouin = new Pingouin(40, 20);
		assert (pingouin.toString().equals(ocean.getPingouin().toString())) : "Pingouin";
	}

	private static void testOcean3() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 40, 20, new Random());
		assert (2 == ocean.getNbIceberg()) : "Nb iceberg";
		int[][] matriceOcean = ocean.getMatriceOcean();
		for (int i = 0; i < matriceOcean.length; i++) {
			for (int j = 0; j < matriceOcean[i].length; j++) {
				if (i >= (20 - TAILLE_PINGOUIN) && i <= 40 && j >= (40 - TAILLE_PINGOUIN) && j <= 40) {
					assert (2 == matriceOcean[i][j]) : "Pingouin";
				} else if (i >= 1 && i <= 6 && j >= 1 && j <= 5) {
					assert (1 == matriceOcean[i][j]) : "Iceberg 1";
				} else if (i >= 5 && i <= 7 && j >= 7 && j <= 10) {
					assert (1 == matriceOcean[i][j]) : "Iceberg 2";
				} else {
					assert (0 == matriceOcean[i][j]) : "Erreur pour " + i + ":" + j;
				}
			}
		}
	}

	private static void testgenerateIceberg() {
		System.out.println("Impossible de tester generateIceberg, reportez-vous aux tests unitaire");
	}

	private static void testfondre() {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 20, 20, new Random());

		ocean.fondre(0.5);
		icebergUn.fondre(0.5);
		icebergDeux.fondre(0.5);

		assert (icebergUn.toString().equals(icebergs[0].toString()));
		assert (icebergDeux.toString().equals(icebergs[1].toString()));
	}

	private static void testCollisionPingouinIceberg() {
		// Attention à la taille du pingouin !
		Iceberg2D[] icebergs = new Iceberg2D[1];
		icebergs[0] = new Iceberg2D(new Point(5, 0), new Point(0, 5));

		Ocean ocean = new Ocean(icebergs, 40, 40, new Random());

		assert (!icebergs[0].isCapture()) : "Pas de collision avec un pingouin";

		ocean.collisionPigouinIceberg();
		assert (!icebergs[0].isCapture()) : "Pas de collision avec un pingouin";

		ocean.getPingouin().deplacer(-40, -40);
		ocean.collisionPigouinIceberg();
		assert (icebergs[0].isCapture()) : "Collision avec un pingouin";
	}

	private static void testVerifSurfaceMinIce() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 40, 40, new Random());

		ocean.verifSurfaceMinIce();
		assert (ocean.getPingouin().isVivant());

		icebergs[0].capturer();
		while (icebergs[0].surface() > SURF_ICE_MIN) icebergs[0].fondre(0.1);
		ocean.verifSurfaceMinIce();
		assert (ocean.getPingouin().isVivant()) : "Pingouin vivant";

		while (icebergs[1].surface() > SURF_ICE_MIN) icebergs[1].fondre(0.1);
		ocean.verifSurfaceMinIce();
		assert (!ocean.getPingouin().isVivant()) : "Pingouin mort";
	}

	private static void testVerifResteIceberg() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 20, 20, new Random());
		assert (ocean.verifResteIceberg()) : "2 icebergs restant";

		icebergs[0].capturer();
		assert (ocean.verifResteIceberg()) : "iceberg n°1 restant";

		icebergs[1].capturer();
		assert (!ocean.verifResteIceberg()) : "Aucun icebergs restant";
	}

	private static void testToString() {
		Ocean ocean = new Ocean(2, 40, 20);
		assert (ocean.toString().equals("NbIceberg : 2\n Hauteur océan : 20\n Largeur océan : 40"));
	}

}
