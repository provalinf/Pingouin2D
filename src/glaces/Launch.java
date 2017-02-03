package glaces;

/**
 * Created by Valentin.
 */
public class Launch {
	public static void main(String[] args) {
		Ocean ocean = new Ocean(2, 600, 400);
		ArcticImage arctic = new ArcticImage(ocean.getWidth(), ocean.getHeight());
		arctic.generateImage(ocean.getMatriceOcean());
	}
}
