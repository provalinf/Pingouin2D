package glaces;

/**
 * Created by Valentin.
 */
public class CoordonneesNegativeException extends Exception {
	public CoordonneesNegativeException(String message) {
		super(message);
		System.out.println(message);
	}
}
