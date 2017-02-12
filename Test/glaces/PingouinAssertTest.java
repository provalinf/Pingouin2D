package glaces;

import geometrie.Point;

/**
 * Created by Valentin.
 * <p>
 * TU : jUnit 4 (méthode non TDD)
 * Taux de couverture : 100%
 */
public class PingouinAssertTest {
	private static final int TAILLE_PINGOUIN = 16;

	public static void main(String[] args) {
		testPingouin();
		testDeplacer();
		testVie();
		testToString();
	}

	private static void testPingouin() {
		try {
			new Pingouin(-10, 0);
			//noinspection ConstantConditions
			assert (false) : "Exception non retournée";
		} catch (AssertionError exception) {
			assert (exception.getMessage().equals("La taille de l'océan est invalide (minimum " + TAILLE_PINGOUIN + "px requis)")) : "Erreur " + exception.getMessage();
		}

		Pingouin pingouin = new Pingouin(600, 400);
		assert (new Point(400, 600 - TAILLE_PINGOUIN).toString().equals(pingouin.getBasAgauche().toString()));
		assert (new Point(400 - TAILLE_PINGOUIN, 600).toString().equals(pingouin.getHautAdroite().toString()));
	}

	private static void testDeplacer() {
		Pingouin pingouin = new Pingouin(600, 400);
		pingouin.deplacer(-10, -10);
		assert (new Point(390, 590 - TAILLE_PINGOUIN).toString().equals(pingouin.getBasAgauche().toString()));
		assert (new Point(390 - TAILLE_PINGOUIN, 590).toString().equals(pingouin.getHautAdroite().toString()));

		pingouin.deplacer(15, 15);
		assert (new Point(400, 600 - TAILLE_PINGOUIN).toString().equals(pingouin.getBasAgauche().toString()));
		assert (new Point(400 - TAILLE_PINGOUIN, 600).toString().equals(pingouin.getHautAdroite().toString()));

		pingouin.deplacer(-405, 0);
		//noinspection PointlessArithmeticExpression
		assert (new Point(0 + TAILLE_PINGOUIN, 600 - TAILLE_PINGOUIN).toString().equals(pingouin.getBasAgauche().toString()));
		assert (new Point(0, 600).toString().equals(pingouin.getHautAdroite().toString()));

		pingouin.deplacer(0, -605);
		//noinspection PointlessArithmeticExpression
		assert (new Point(0 + TAILLE_PINGOUIN, 0).toString().equals(pingouin.getBasAgauche().toString()));
		//noinspection PointlessArithmeticExpression
		assert (new Point(0, 0 + TAILLE_PINGOUIN).toString().equals(pingouin.getHautAdroite().toString()));
	}

	private static void testVie() {
		Pingouin pingouin = new Pingouin(600, 400);
		assert (pingouin.isVivant());
		pingouin.tuerLePingouin();
		assert (!pingouin.isVivant());
	}

	private static void testToString() {
		Pingouin pingouin = new Pingouin(20, 20);
		assert (pingouin.toString().equals("Pingouin :\n - <20.0," + (20 - TAILLE_PINGOUIN) + ".0>\n - <" + (20 - TAILLE_PINGOUIN) + ".0,20.0>"));
	}
}
