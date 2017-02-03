/*
package glaces;

*/
/**
 * Created by Valentin.
 * <p>
 * Génère une chaine de caractère représentative de l'océan
 * pour une vue graphique.
 *
 * @param matriceOcean matrice de l'océan à afficher
 *//*

public class ArcticImage {
	private int height;
	private int width;

	public ArcticImage(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setColors() {
		System.out.println("Executer la méthode generateImage(int[][] matriceOcean)");
	}

	*/
/**
 * Génère une chaine de caractère représentative de l'océan
 * pour une vue graphique.
 *
 * @param matriceOcean matrice de l'océan à afficher
 *//*

	public void generateImage(int[][] matriceOcean) {
		System.out.println("\nGénération de l'image de l'Océan\n");
		for (int[] aMatriceOcean : matriceOcean) {
			for (int anAMatriceOcean : aMatriceOcean) {
				System.out.print(anAMatriceOcean == 0 ? "▒" : "█");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public void fermer() {

	}
}
*/

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package glaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	 * Déprécié, utiliser plutôt generateImage()
	 * @param tab
	 */
	public void setColors(int[][] tab) {
		int largeur = tab.length;
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

		this.jlabel.setIcon(new ImageIcon(image));
	}

	/**
	 * Génère une chaine de caractère représentative de l'océan
	 * pour une vue graphique.
	 *
	 * @param matriceOcean matrice de l'océan à afficher
	 */

	public void generateImage(int[][] matriceOcean) {
		System.out.println("\nGénération de l'image de l'Océan\n");
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

