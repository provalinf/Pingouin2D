package glaces;

import geometrie.Point;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Valentin.
 * <p>
 * TU : jUnit 4 (méthode non TDD)
 * Taux de couverture : 100% des méthodes utiles
 */
public class OceanUnitTest {
	private static final int TAILLE_PINGOUIN = 16;
	private static final double SURF_ICE_MIN = 2;

	@Test
	public void testOcean() {
		Ocean ocean = new Ocean();
		Assert.assertEquals("Nb iceberg", 2, ocean.getNbIceberg(), 0);
		Assert.assertEquals("Width", 40, ocean.getWidth(), 0);
		Assert.assertEquals("Height", 20, ocean.getHeight(), 0);
	}

	@Test
	public void testOcean2() {
		Ocean ocean = new Ocean(2, 40, 20);
		Assert.assertEquals("Nb iceberg", 2, ocean.getNbIceberg(), 0);
		Assert.assertEquals("Width", 40, ocean.getWidth(), 0);
		Assert.assertEquals("Height", 20, ocean.getHeight(), 0);
	}

	@Test
	public void testGetPingouin() {
		Ocean ocean = new Ocean(2, 40, 20);
		Pingouin pingouin = new Pingouin(40, 20);
		// Erreur d'espace lors d'une comparaison sans toString()
		Assert.assertEquals("Pingouin", pingouin.toString(), ocean.getPingouin().toString());
	}

	@Test
	public void testOcean3() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 40, 20, new Random());
		Assert.assertEquals("Nb iceberg", 2, ocean.getNbIceberg(), 0);
		int[][] matriceOcean = ocean.getMatriceOcean();
		for (int i = 0; i < matriceOcean.length; i++) {
			for (int j = 0; j < matriceOcean[i].length; j++) {
				if (i >= (20 - TAILLE_PINGOUIN) && i <= 40 && j >= (40 - TAILLE_PINGOUIN) && j <= 40) {
					Assert.assertEquals("Pingouin", 2, matriceOcean[i][j]);
				} else if (i >= 1 && i <= 6 && j >= 1 && j <= 5) {
					Assert.assertEquals("Iceberg 1", 1, matriceOcean[i][j]);
				} else if (i >= 5 && i <= 7 && j >= 7 && j <= 10) {
					Assert.assertEquals("Iceberg 2", 1, matriceOcean[i][j]);
				} else {
					Assert.assertEquals("Erreur pour " + i + ":" + j, 0, matriceOcean[i][j]);
				}
			}
		}
	}

	@Test
	public void testgenerateIceberg() {
		// When
		Random random = mock(Random.class);
		Iceberg2D[] icebergs = new Iceberg2D[1];
		Ocean ocean = new Ocean(icebergs, 20, 20, random);

		when(random.nextDouble()).thenReturn(0.5);
		when(random.nextDouble()).thenReturn(0.5);
		when(random.nextDouble()).thenReturn(0.5);
		when(random.nextDouble()).thenReturn(0.5);

		// Then
		ocean.generateIcebergs();

		// Verify
		Assert.assertEquals(new Point(10, 10), icebergs[0].coinEnBasAGauche());
		Assert.assertEquals(new Point(5, 15), icebergs[0].coinEnHautADroite());
	}

	@Test
	public void testfondre() throws Exception {
		Iceberg2D icebergUn = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		Iceberg2D icebergDeux = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 20, 20, new Random());

		ocean.fondre(0.5);
		icebergUn.fondre(0.5);
		icebergDeux.fondre(0.5);

		Assert.assertEquals(icebergUn.coinEnBasAGauche(), icebergs[0].coinEnBasAGauche());
		Assert.assertEquals(icebergUn.coinEnHautADroite(), icebergs[0].coinEnHautADroite());

		Assert.assertEquals(icebergDeux.coinEnBasAGauche(), icebergs[1].coinEnBasAGauche());
		Assert.assertEquals(icebergDeux.coinEnHautADroite(), icebergs[1].coinEnHautADroite());
	}

	@Test
	public void testCollisionPingouinIceberg() {
		// When
		Iceberg2D[] icebergs = new Iceberg2D[1];
		icebergs[0] = Mockito.spy(new Iceberg2D(new Point(6, 1), new Point(1, 5)));

		Ocean ocean = new Ocean(icebergs, 20, 20, new Random());

		// Then & Verify
		Assert.assertFalse("Pas de collision avec un pingouin", icebergs[0].isCapture());

		doReturn(false).when(icebergs[0]).collision(any(Iceberg2D.class));
		ocean.collisionPigouinIceberg();
		Assert.assertFalse("Pas de collision avec un pingouin", icebergs[0].isCapture());

		doReturn(true).when(icebergs[0]).collision(any(Iceberg2D.class));
		ocean.collisionPigouinIceberg();
		Assert.assertTrue("Collision avec un pingouin", icebergs[0].isCapture());
	}

	@Test
	public void testVerifSurfaceMinIce() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 40, 40, new Random());

		ocean.verifSurfaceMinIce();
		Assert.assertTrue(ocean.getPingouin().isVivant());

		icebergs[0].capturer();
		while (icebergs[0].surface() > SURF_ICE_MIN) icebergs[0].fondre(0.1);
		ocean.verifSurfaceMinIce();
		Assert.assertTrue("Pingouin vivant", ocean.getPingouin().isVivant());

		while (icebergs[1].surface() > SURF_ICE_MIN) icebergs[1].fondre(0.1);
		ocean.verifSurfaceMinIce();
		Assert.assertFalse("Pingouin mort", ocean.getPingouin().isVivant());
	}

	@Test
	public void testVerifResteIceberg() {
		Iceberg2D[] icebergs = new Iceberg2D[2];
		icebergs[0] = new Iceberg2D(new Point(6, 1), new Point(1, 5));
		icebergs[1] = new Iceberg2D(new Point(7, 7), new Point(5, 10));

		Ocean ocean = new Ocean(icebergs, 20, 20, new Random());
		Assert.assertTrue("2 icebergs restant", ocean.verifResteIceberg());

		icebergs[0].capturer();
		Assert.assertTrue("iceberg n°1 restant", ocean.verifResteIceberg());

		icebergs[1].capturer();
		Assert.assertFalse("Aucun icebergs restant", ocean.verifResteIceberg());
	}

	@Test
	public void testToString() {
		Ocean ocean = new Ocean(2, 40, 20);
		Assert.assertEquals("NbIceberg : 2\n Hauteur océan : 20\n Largeur océan : 40", ocean.toString());
	}

}
