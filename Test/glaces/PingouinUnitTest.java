package glaces;

import geometrie.Point;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Valentin.
 * <p>
 * TU : jUnit 4 (méthode non TDD)
 * Taux de couverture : 100%
 */
public class PingouinUnitTest {
	private static final int TAILLE_PINGOUIN = 16;

	@Test
	public void testPingouin() {
		try {
			new Pingouin(-10, 0);
			Assert.fail("Exception non retournée");
		} catch (AssertionError exception) {
			Assert.assertEquals("La taille de l'océan est invalide (minimum " + TAILLE_PINGOUIN + "px requis)", exception.getMessage());
		}

		Pingouin pingouin = new Pingouin(600, 400);
		Assert.assertEquals(new Point(400, 600 - TAILLE_PINGOUIN), pingouin.getBasAgauche());
		Assert.assertEquals(new Point(400 - TAILLE_PINGOUIN, 600), pingouin.getHautAdroite());
	}

	@Test
	public void testDeplacer() {
		Pingouin pingouin = new Pingouin(600, 400);
		pingouin.deplacer(-10, -10);
		Assert.assertEquals(new Point(390, 590 - TAILLE_PINGOUIN), pingouin.getBasAgauche());
		Assert.assertEquals(new Point(390 - TAILLE_PINGOUIN, 590), pingouin.getHautAdroite());

		pingouin.deplacer(15, 15);
		Assert.assertEquals(new Point(400, 600 - TAILLE_PINGOUIN), pingouin.getBasAgauche());
		Assert.assertEquals(new Point(400 - TAILLE_PINGOUIN, 600), pingouin.getHautAdroite());

		pingouin.deplacer(-405, 0);
		//noinspection PointlessArithmeticExpression
		Assert.assertEquals(new Point(0 + TAILLE_PINGOUIN, 600 - TAILLE_PINGOUIN), pingouin.getBasAgauche());
		Assert.assertEquals(new Point(0, 600), pingouin.getHautAdroite());

		pingouin.deplacer(0, -605);
		//noinspection PointlessArithmeticExpression
		Assert.assertEquals(new Point(0 + TAILLE_PINGOUIN, 0), pingouin.getBasAgauche());
		//noinspection PointlessArithmeticExpression
		Assert.assertEquals(new Point(0, 0 + TAILLE_PINGOUIN), pingouin.getHautAdroite());
	}

	@Test
	public void testVie() {
		Pingouin pingouin = new Pingouin(600, 400);
		Assert.assertTrue(pingouin.isVivant());
		pingouin.tuerLePingouin();
		Assert.assertFalse(pingouin.isVivant());
	}

	@Test
	public void testToString() {
		Pingouin pingouin = new Pingouin(20, 20);
		Assert.assertEquals("Pingouin :\n - <20.0," + (20 - TAILLE_PINGOUIN) + ".0>\n - <" + (20 - TAILLE_PINGOUIN) + ".0,20.0>", pingouin.toString());
	}
}
