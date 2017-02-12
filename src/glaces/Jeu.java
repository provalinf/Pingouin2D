package glaces;

import static java.lang.Thread.sleep;

/**
 * Created by Valentin.
 */
public class Jeu {
	public static void main(String[] args) {
		jouer();
	}

	public static void jouer() {
		Ocean ocean = new Ocean(6, 600, 400);
		ArcticImage arctic = new ArcticImage(ocean.getWidth(), ocean.getHeight());
		ThreadScan mv_key = new ThreadScan(ocean, arctic);
		mv_key.start();

		System.out.println("\nCapturez tous les icebergs avec votre pingouin avant qu'ils n'aient trop fondu.\n - 'z' pour monter\n - 'q' pour deplacement lateral gauche\n - 's' pour descendre\n - 'd' pour deplacement lateral droit\n - 'e' pour quitter");

		arctic.generateImage(ocean.getMatriceOcean());
		while (!mv_key.isInterrupted() && ocean.getPingouin().isVivant()
				&& ocean.verifResteIceberg()) {

			ocean.fondre(0.02);
			ocean.verifSurfaceMinIce();
			arctic.generateImage(ocean.getMatriceOcean());

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (ocean.getPingouin().isVivant()) {
			System.out.println("\nFelicitation, vous avez reussi a capturer tous les icebergs avant qu'ils ne fondent !\nPour la peine, votre pingouin aura a manger ce soir !");
		} else {
			System.out.println("\nUn iceberg est devenu trop petit.\nVotre pingouin est donc mort dans d'atroce souffrance.\nOui, vous avez perdu, si vous ne l'aviez pas compris :)\n - 'e' pour quitter");
		}
	}
}
