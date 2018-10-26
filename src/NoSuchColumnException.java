import java.util.NoSuchElementException;

/**
 * Thrown by the countUnique method to indicate that there is no such column.
 * @author Shichao Ma
 *
 */
public class NoSuchColumnException extends NoSuchElementException {

	private static final long serialVersionUID = 3445093664301706592L;
	
	/**
	 * Constructs a NoSuchColumnException with "No such column!" as its error message string.
	 */
	public NoSuchColumnException() {
		super("No such column!");
	}
	
	/**
	 * Constructs a NoSuchColumnException with a provided error message string.
	 */
	public NoSuchColumnException(String message) {
		super(message);
	}
}
