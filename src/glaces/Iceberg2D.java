package glaces;

import geometrie.*;

public class Iceberg2D {

	private Point enBasAGauche;
	private Point enHautADroite;

	/**
	 * @param bg le coin en bas à gauche
	 * @param hd le coin en haut à droite
	 *           uniquement en coordonnées positives
	 */
	public Iceberg2D(Point bg, Point hd) throws Exception {
		verifNegativePoints(bg);
		verifNegativePoints(hd);
		if (bg.getAbscisse() < hd.getAbscisse() || bg.getOrdonnee() > hd.getOrdonnee()) {
			throw new CoordonneesPositionException("Les coordonnées des points sont incorrects. Le premier point doit être en bas à gauche, le second en haut à droite !");
		}

		enBasAGauche = bg;
		enHautADroite = hd;
	}

	/**
	 * Vérifie si les coordonnées du point sont positives sinon retourne une exception
	 *
	 * @param p le point à vérifier
	 * @throws Exception de type CoordonneesNegativeException
	 */
	private void verifNegativePoints(Point p) throws Exception {
		if (p.getAbscisse() < 0 || p.getOrdonnee() < 0)
			throw new CoordonneesNegativeException("Les coordonnées négatives ne sont pas autorisées !");
	}

	/**
	 * Construction par fusion de deux icebergs qui se touchent
	 *
	 * @param i1 iceberg n°1
	 * @param i2 iceberg n°2
	 */
	public Iceberg2D(Iceberg2D i1, Iceberg2D i2) {
		Point had1 = i1.coinEnHautADroite();
		Point bag1 = i1.coinEnBasAGauche();

		Point had2 = i2.coinEnHautADroite();
		Point bag2 = i2.coinEnBasAGauche();

		double Abscisse;
		double Ordonnee;

		if (had1.getAbscisse() < had2.getAbscisse()) {
			Abscisse = had1.getAbscisse();
		} else {
			Abscisse = had2.getAbscisse();
		}

		if (had1.getOrdonnee() > had2.getOrdonnee()) {
			Ordonnee = had1.getOrdonnee();
		} else {
			Ordonnee = had2.getOrdonnee();
		}

		enHautADroite = new Point(Abscisse, Ordonnee);

		if (bag1.getAbscisse() > bag2.getAbscisse()) {
			Abscisse = bag1.getAbscisse();
		} else {
			Abscisse = bag2.getAbscisse();
		}

		if (bag1.getOrdonnee() < bag2.getOrdonnee()) {
			Ordonnee = bag1.getOrdonnee();
		} else {
			Ordonnee = bag2.getOrdonnee();
		}

		enBasAGauche = new Point(Abscisse, Ordonnee);

	}

	/**
	 * @return le coin en bas à gauche
	 */
	public Point coinEnBasAGauche() {
		return enBasAGauche;
	}

	/**
	 * @return le coin en haut à droite
	 */
	public Point coinEnHautADroite() {
		return enHautADroite;
	}

	/**
	 * @return hauteur
	 */
	public double hauteur() {
		/*return Math.abs(enBasAGauche.getAbscisse() - enHautADroite.getAbscisse());*/
		return enBasAGauche.getAbscisse() - enHautADroite.getAbscisse();
	}

	/**
	 * @return largeur
	 */
	public double largeur() {
		/*return Math.abs(enHautADroite.getOrdonnee() - enBasAGauche.getOrdonnee());*/
		return enHautADroite.getOrdonnee() - enBasAGauche.getOrdonnee();
	}

	/**
	 * @return surface totale
	 */
	public double surface() {
		return hauteur() * largeur();
	}

	/**
	 * @param i
	 * @return vrai si collision entre les deux icebergs
	 */
	public boolean collision(Iceberg2D i) {

		if (enBasAGauche.getAbscisse() <= i.coinEnBasAGauche().getAbscisse() && enBasAGauche.getAbscisse() >= i.coinEnHautADroite().getAbscisse()) {
			if (enHautADroite.getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee() && enHautADroite.getOrdonnee() <= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision point en haut à gauche de this");
				return true;
			}
			if (enBasAGauche.getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee() && enBasAGauche.getOrdonnee() <= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision point en bas à gauche de this");
				return true;
			}
		}

		if (enHautADroite.getAbscisse() <= i.coinEnBasAGauche().getAbscisse() && enHautADroite.getAbscisse() >= i.coinEnHautADroite().getAbscisse()) {
			if (enHautADroite.getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee() && enHautADroite.getOrdonnee() <= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision point en haut à droite de this");
				return true;
			}
			if (enBasAGauche.getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee() && enBasAGauche.getOrdonnee() <= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision point en bas à droite de this");
				return true;
			}
		}

		if (i.coinEnBasAGauche().getAbscisse() <= enBasAGauche.getAbscisse() && i.coinEnBasAGauche().getAbscisse() >= enHautADroite.getAbscisse()) {
			if (i.coinEnBasAGauche().getOrdonnee() >= enBasAGauche.getOrdonnee() && i.coinEnBasAGauche().getOrdonnee() <= enHautADroite.getOrdonnee()) {
				System.out.println("Collision de tous les points de i");        // Si ce cas de figure, tous les points de i se situe forcément dans this
				return true;
			}
		}

		return false;
	}

	/**
	 * @param i
	 * @return vrai si this est plus volumineux que i
	 */
	public boolean estPlusGrosQue(Iceberg2D i) {
		return surface() > i.surface();
	}

	public String toString() {
		return "Iceberg :"
				+ "\n - " + enBasAGauche
				+ "\n - " + enHautADroite;
	}

	/**
	 * @return le point au centre de l'iceberg
	 */
	public Point centre() {
		return new Point(((hauteur() / 2.) + enHautADroite.getAbscisse()), ((largeur() / 2.) + enBasAGauche.getOrdonnee()));
	}

	/**
	 * Réduction dans les quatre directions ; le centre ne bouge pas
	 *
	 * @param fr dans ]0..1[ facteur de réduction
	 */
	public void fondre(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		fr = fr / 4.;
		double largeur = largeur();
		double hauteur = hauteur();
		enHautADroite.deplacer(0, -largeur * fr);
		enBasAGauche.deplacer(0, largeur * fr);
		enHautADroite.deplacer(hauteur * fr, 0);
		enBasAGauche.deplacer(-hauteur * fr, 0);
	}

	/**
	 * Casser une partie à droite
	 *
	 * @param fr dans ]0..1[ facteur de réduction
	 */
	public void casserDroite(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		enHautADroite.deplacer(0, -largeur() * fr);
	}

	/**
	 * Casser une partie à gauche
	 *
	 * @param fr dans ]0..1[ facteur de réduction
	 */
	public void casserGauche(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		enBasAGauche.deplacer(0, largeur() * fr);
	}

	/**
	 * Casser une partie en haut
	 *
	 * @param fr dans ]0..1[ facteur de réduction
	 */
	public void casserHaut(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		enHautADroite.deplacer(hauteur() * fr, 0);
	}

	/**
	 * Casser une partie en bas
	 *
	 * @param fr dans ]0..1[ : définit le pourcentage supprimé
	 */
	public void casserBas(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		enBasAGauche.deplacer(-hauteur() * fr, 0);
	}

}
