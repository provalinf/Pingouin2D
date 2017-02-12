//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package glaces;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ArcticImage extends JFrame {
	protected static int OCEAN = 1007296;
	protected static int GLACE = 16777215;
	protected static int PINGU2 = 13731860;
	protected static int PINGU3 = 13720084;
	protected static int NEANT = 0;
	private JLabel jlabel;

	public ArcticImage(int largeur, int hauteur) {
		super("ArcticGame");
		System.out.println("largeur " + largeur);
		System.out.println("hauteur : " + hauteur);
		this.jlabel = new JLabel();
		this.jlabel.setPreferredSize(new Dimension(largeur, hauteur));
		this.add(this.jlabel, "Center");
		this.pack();
		this.setLocationRelativeTo((Component) null);
		this.setVisible(true);
	}

	/**
	 * Déprécié, utiliser generateImage()
	 *
	 * @param tab
	 */
	public void setColors(int[][] tab) {
		System.out.println("Deprecie, la logique ne correspond pas au TP1 ce qui provoque une erreur d'affichage, utiliser generateImage()");
		/*int largeur = tab.length;
		int hauteur = tab[0].length;
		BufferedImage image = new BufferedImage(largeur, hauteur, 1);

		for (int x = 0; x < largeur; ++x) {
			for (int y = 0; y < hauteur; ++y) {
				int couleur;
				switch (tab[x][y]) {
					case 0:
						couleur = OCEAN;
						break;
					case 1:
						couleur = GLACE;
						break;
					case 2:
						couleur = PINGU2;
						break;
					case 3:
						couleur = PINGU3;
						break;
					default:
						couleur = NEANT;
				}

				image.setRGB(x, y, couleur);
			}
		}

		this.jlabel.setIcon(new ImageIcon(image));*/
	}

	/**
	 * Génère des pixels de couleur représentatif de l'océan
	 * pour une représentation graphique.
	 *
	 * @param matriceOcean int[][] : matrice de l'océan à afficher
	 */
	public void generateImage(int[][] matriceOcean) {
		BufferedImage image = new BufferedImage(matriceOcean[0].length, matriceOcean.length, 1);
		for (int i = 0; i < matriceOcean.length; i++) {
			for (int j = 0; j < matriceOcean[i].length; j++) {
				int couleur;
				switch (matriceOcean[i][j]) {
					case 0:
						couleur = OCEAN;
						break;
					case 1:
						couleur = GLACE;
						break;
					case 2:
						couleur = PINGU2;
						break;
					case 3:
						couleur = PINGU3;
						break;
					default:
						couleur = NEANT;
				}
				image.setRGB(j, i, couleur);
			}
		}
		this.jlabel.setIcon(new ImageIcon(image));
	}

	public void fermer() {
		this.dispose();
	}
}

