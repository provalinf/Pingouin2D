package glaces;

import geometrie.Point;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Valentin.
 */

/**
 * TU : jUnit 4 (méthode TDD)
 * Taux de couverture : 94% (toString & main non testé)
 */
public class Iceberg2DUnitTest {
	final Point n1 = new Point(6, 1);
	final Point n2 = new Point(1, 10);

	@Test(expected = CoordonneesNegativeException.class)
	public void testNegatifInterditAbscisseIceberg2D() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(-1, 10), new Point(-6, 1));
	}

	@Test(expected = CoordonneesNegativeException.class)
	public void testNegatifInterditOrdonneeIceberg2D() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(1, -10), new Point(6, -1));
	}

	@Test(expected = CoordonneesPositionException.class)
	public void testCoordInterditIceberg2D() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(1, 10), new Point(6, 1));
	}

	@Test
	public void testIceberg2D() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		Assert.assertEquals(n1, iceberg.coinEnBasAGauche());
		Assert.assertEquals(n2, iceberg.coinEnHautADroite());
	}

	@Test
	public void testSecondIceberg2D() throws Exception {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 5), new Point(2, 10));
		Iceberg2D icebergFusion = new Iceberg2D(icebergUn, icebergDeux);
		Assert.assertEquals("Coin en haut à droite", new Point(1, 10), icebergFusion.coinEnHautADroite());
		Assert.assertEquals("Coin en bas à gauche", new Point(8, 1), icebergFusion.coinEnBasAGauche());


		icebergFusion = new Iceberg2D(icebergDeux, icebergUn);
		Assert.assertEquals("Coin en haut à droite", new Point(1, 10), icebergFusion.coinEnHautADroite());
		Assert.assertEquals("Coin en bas à gauche", new Point(8, 1), icebergFusion.coinEnBasAGauche());

			/* Utile si les coordonnées négatives avaient été autorisés
			icebergUn = new Iceberg2D(new Point(-2, -9), new Point(-7, -6));
			icebergDeux = new Iceberg2D(new Point(-1, -6), new Point(-5, 0));
			icebergFusion = new Iceberg2D(icebergUn, icebergDeux);
			Assert.assertEquals("Coin en haut à droite", new Point(-7, 0), icebergFusion.coinEnHautADroite());
			Assert.assertEquals("Coin en bas à gauche", new Point(-1, -9), icebergFusion.coinEnBasAGauche());*/
	}

	@Test
	public void testHauteur() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		Assert.assertEquals(5., iceberg.hauteur(), 0);
	}

	@Test
	public void testLargeur() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		Assert.assertEquals(9., iceberg.largeur(), 0);
	}

	@Test
	public void testSurface() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(n1, n2);
		Assert.assertEquals(iceberg.hauteur() * iceberg.largeur(), iceberg.surface(), 0);
	}

	@Test
	public void testCollision() throws Exception {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 10));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 5), new Point(3, 12));
		Iceberg2D icebergTrois = new Iceberg2D(new Point(10, 4), new Point(9, 15));
		Iceberg2D icebergQuatre = new Iceberg2D(new Point(5, 2), new Point(2, 9));
		Iceberg2D icebergCinq = new Iceberg2D(new Point(4, 7), new Point(2, 15));

		Assert.assertTrue("Collision iceberg n°1 avec n°2", icebergUn.collision(icebergDeux));
		Assert.assertTrue("Collision iceberg n°2 avec n°1", icebergDeux.collision(icebergUn));

		Assert.assertTrue("Collision iceberg n°2 avec n°5", icebergDeux.collision(icebergCinq));
		Assert.assertTrue("Collision iceberg n°5 avec n°2", icebergCinq.collision(icebergDeux));

		Assert.assertTrue("Collision iceberg n°1 avec n°4", icebergUn.collision(icebergQuatre));

		Assert.assertFalse("Pas de collision iceberg n°2 avec n°3", icebergTrois.collision(icebergDeux));
	}

	@Test
	public void testEstPlusGrosQue() throws Exception {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), n2);
		Iceberg2D icebergDeux = new Iceberg2D(new Point(8, 1), n2);

		Assert.assertFalse("Iceberg n°1 est plus gros que Iceberg n°2", icebergUn.estPlusGrosQue(icebergDeux));
		Assert.assertTrue("Iceberg n°2 est plus gros que Iceberg n°1", icebergDeux.estPlusGrosQue(icebergUn));
	}

	@Test
	public void testCentre() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));
		Assert.assertEquals(new Point(3.5, 5.5), iceberg.centre());
	}

	@Test
	public void testfondre() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.fondre(0);
		iceberg.fondre(1.5);
		Assert.assertEquals("Fondre en bas à gauche 0 & 1", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("Fondre en haut à droite 0 & 1", new Point(1, 10), iceberg.coinEnHautADroite());

		iceberg.fondre(0.4);
		Assert.assertEquals("Fondre en bas à gauche", new Point(5.5, 1.9), iceberg.coinEnBasAGauche());
		Assert.assertEquals("Fondre en haut à droite", new Point(1.5, 9.1), iceberg.coinEnHautADroite());
	}

	@Test
	public void testCasserDroite() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserDroite(0);
		iceberg.casserDroite(1.5);
		Assert.assertEquals("CasserDroite en bas à gauche 0 & 1", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserDroite en haut à droite 0 & 1", new Point(1, 10), iceberg.coinEnHautADroite());

		iceberg.casserDroite(0.1);
		Assert.assertEquals("CasserDroite en bas à gauche", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserDroite en haut à droite", new Point(1, 9.1), iceberg.coinEnHautADroite());
	}

	@Test
	public void testCasserGauche() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserGauche(0);
		iceberg.casserGauche(1.5);
		Assert.assertEquals("CasserGauche en bas à gauche 0 & 1", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserGauche en haut à droite 0 & 1", new Point(1, 10), iceberg.coinEnHautADroite());

		iceberg.casserGauche(0.1);
		Assert.assertEquals("CasserGauche en bas à gauche", new Point(6, 1.9), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserGauche en haut à droite", new Point(1, 10), iceberg.coinEnHautADroite());
	}

	@Test
	public void testCasserhaut() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserHaut(0);
		iceberg.casserHaut(1.5);
		Assert.assertEquals("CasserHaut en bas à gauche 0 & 1", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserHaut en haut à droite 0 & 1", new Point(1, 10), iceberg.coinEnHautADroite());

		iceberg.casserHaut(0.1);
		Assert.assertEquals("CasserHaut en bas à gauche", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserHaut en haut à droite", new Point(1.5, 10), iceberg.coinEnHautADroite());
	}

	@Test
	public void testCasserBas() throws Exception {
		Iceberg2D iceberg = new Iceberg2D(new Point(6, 1), new Point(1, 10));

		iceberg.casserBas(0);
		iceberg.casserBas(1.5);
		Assert.assertEquals("CasserBas en bas à gauche 0 & 1", new Point(6, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserBas en haut à droite 0 & 1", new Point(1, 10), iceberg.coinEnHautADroite());

		iceberg.casserBas(0.1);
		Assert.assertEquals("CasserBas en bas à gauche", new Point(5.5, 1), iceberg.coinEnBasAGauche());
		Assert.assertEquals("CasserBas en haut à droite", new Point(1, 10), iceberg.coinEnHautADroite());
	}
}
