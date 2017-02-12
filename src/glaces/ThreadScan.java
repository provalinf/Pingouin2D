package glaces;

import java.util.Scanner;

/**
 * Created by Valentin.
 */
public class ThreadScan extends Thread {
	private Scanner scan;
	private Ocean ocean;
	private ArcticImage arctic;

	private static final double DEP_PING = 5;

	public ThreadScan(Ocean ocean, ArcticImage arctic) {
		this.ocean = ocean;
		this.arctic = arctic;
		scan = new Scanner(System.in);
	}

	public void run() {
		try {
			requestLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void requestLoop() throws InterruptedException {
		while (!isInterrupted()) {
			String sc = scan.next();
			switch (sc.charAt(0)) {
				case 'z':
					deplacerSansAccoup(false, "v");
					break;
				case 'q':
					deplacerSansAccoup(false, "h");
					break;
				case 's':
					deplacerSansAccoup(true, "v");
					break;
				case 'd':
					deplacerSansAccoup(true, "h");
					break;
				case 'e':
					arctic.fermer();
					System.out.println("A bientot !");
					System.exit(0);
					break;
			}
		}
	}

	private void deplacerSansAccoup(boolean pos, String sens) {
		if (!ocean.getPingouin().isVivant()) return;

		for (double i = 0; i < DEP_PING; i += 0.5) {
			if (sens.contentEquals("v")) {
				ocean.getPingouin().deplacer(pos ? i : -i, 0);
			} else {
				ocean.getPingouin().deplacer(0, pos ? i : -i);
			}
			arctic.generateImage(ocean.getMatriceOcean());
			ocean.collisionPigouinIceberg();
		}
	}

}
