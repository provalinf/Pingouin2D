package glaces;

import geometrie.Point;

public class Iceberg2D {

	private Point enBasAGauche;
	private Point enHautADroite;

	private boolean capture;

	/**
	 * Constructeur
	 * Les points passés en paramètre doivent être positifs,
	 * être valides (bg en bas à gauche et hd en haut à droite)
	 *
	 * @param bg Point : coin en bas à gauche
	 * @param hd Point : coin en haut à droite
	 */
	public Iceberg2D(Point bg, Point hd) {
		verifNegativePoints(bg);
		verifNegativePoints(hd);
		verifPositionPoints(bg, hd);

		enBasAGauche = bg;
		enHautADroite = hd;
		capture = false;
	}

	/**
	 * Construction par fusion de deux icebergs qui se touchent
	 *
	 * @param i1 Iceberg2D : iceberg n°1
	 * @param i2 Iceberg2D : iceberg n°2
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
	 * Vérifie si les coordonnées du point sont positives,
	 * sinon retourne une exception de type AssertionError
	 *
	 * @param p Point : celui à vérifier
	 */
	private void verifNegativePoints(Point p) {
		assert (!(p.getAbscisse() < 0 || p.getOrdonnee() < 0)) : "Les coordonnées négatives ne sont pas autorisées !";
	}

	/**
	 * Vérifie si les coordonnées des points sont valides,
	 * le point bg doit être véritablement en bas à gauche et inversement,
	 * sinon retourne une exception de type AssertionError
	 *
	 * @param bg Point : en bas à gauche
	 * @param hd Point : en haut à droite
	 */
	private void verifPositionPoints(Point bg, Point hd) {
		assert (!(bg.getAbscisse() < hd.getAbscisse() || bg.getOrdonnee() > hd.getOrdonnee())) : "Les coordonnées des points sont incorrects. Le premier point doit être en bas à gauche, le second en haut à droite !";
	}

	/**
	 * @return Point : le coin en bas à gauche
	 */
	public Point coinEnBasAGauche() {
		return enBasAGauche;
	}

	/**
	 * @return Point : le coin en haut à droite
	 */
	public Point coinEnHautADroite() {
		return enHautADroite;
	}

	/**
	 * Retourne l'état de l'iceberg
	 * s'il est capturé ou non
	 *
	 * @return boolean : True : capturé, False : sinon
	 */
	public boolean isCapture() {
		return capture;
	}

	/**
	 * Capture l'iceberg
	 */
	public void capturer() {
		capture = true;
	}

	/**
	 * @return double : hauteur
	 */
	public double hauteur() {
		/*return Math.abs(enBasAGauche.getAbscisse() - enHautADroite.getAbscisse());*/
		return enBasAGauche.getAbscisse() - enHautADroite.getAbscisse();
	}

	/**
	 * @return double : largeur
	 */
	public double largeur() {
		/*return Math.abs(enHautADroite.getOrdonnee() - enBasAGauche.getOrdonnee());*/
		return enHautADroite.getOrdonnee() - enBasAGauche.getOrdonnee();
	}

	/**
	 * @return double surface totale
	 */
	public double surface() {
		return hauteur() * largeur();
	}

	/**
	 * Vérification de collision entre deux icebergs
	 *
	 * @param i Iceberg2D : second iceberg pour collision
	 * @return boolean : True : si collision, False : sinon
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
			if (enBasAGauche.getOrdonnee() <= i.coinEnBasAGauche().getOrdonnee() && enHautADroite.getOrdonnee() >= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision par chevauchement type 1");
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

		if (enBasAGauche.getAbscisse() >= i.coinEnBasAGauche().getAbscisse() && enHautADroite.getAbscisse() <= i.coinEnHautADroite().getAbscisse()) {
			if (enBasAGauche.getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee() && enHautADroite.getOrdonnee() <= i.coinEnHautADroite().getOrdonnee()) {
				System.out.println("Collision par chevauchement type 2");
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
	 * Compare la taille de deux icebergs
	 *
	 * @param i Iceberg2D : second iceberg pour comparaison
	 * @return boolean : True : si this est le plus volumineux, False : si i est plus volumineux
	 */
	public boolean estPlusGrosQue(Iceberg2D i) {
		return surface() > i.surface();
	}

	/**
	 * @return Point : centre de l'iceberg
	 */
	public Point centre() {
		return new Point(((hauteur() / 2.) + enHautADroite.getAbscisse()), ((largeur() / 2.) + enBasAGauche.getOrdonnee()));
	}

	/**
	 * Réduction dans les quatre directions
	 * Centre fixe
	 *
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
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
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
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
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
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
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
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
	 * @param fr double : ]0..1[ facteur de réduction (% à supprimer)
	 */
	public void casserBas(double fr) {
		if (fr <= 0 || fr >= 1) {
			System.out.println("Coefficient de réduction invalide (doit être compris entre ]0..1[)");
			return;
		}
		enBasAGauche.deplacer(-hauteur() * fr, 0);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "Iceberg :"
				+ "\n - " + enBasAGauche
				+ "\n - " + enHautADroite;
	}

}
