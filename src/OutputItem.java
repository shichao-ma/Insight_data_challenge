/**
 * A holder of all output fields. Each object holds one line of information. It also provides methods to count and compute percentage.
 * @author Shichao Ma
 * @version 0.1
 */
public class OutputItem implements Comparable<OutputItem> {
	private String name; // The name that is associated to the counting field.
	private int number; // The number of certified applications.
	private double percentage; //The percentage of certified applications for that counting field compared to the total number of certified applications.
	
	/**
	 * Creates an output item by providing its name, number, and percentage.
	 * @param name The name that is associated to the counting field.
	 * @param number The number of certified applications.
	 * @param percentage The percentage of certified applications for that counting field compared to the total number of certified applications.
	 */
	public OutputItem(String name, int number, double percentage) {
		if (number < 0)
			throw new IllegalArgumentException("Number must be non-negative!");
		if (percentage < 0)
			throw new IllegalArgumentException("Percentage must be non-negative!");
		
		this.name = name;
		this.number = number;
		this.percentage = percentage;
	}
	
	/**
	 * Creates an output item by providing its name and number. 
	 * Percentage is not set and can be updated later.
	 * @param name The name that is associated to the counting field.
	 * @param number The number of certified applications.
	 */
	public OutputItem(String name, int number) {
		this(name, number, 0.0);
	}
	
	/**
	 * Creates an output item by providing its name. 
	 * Number and percentage are not set and can be updated later.
	 * @param name The name that is associated to the counting field.
	 */
	public OutputItem(String name) {
		this(name, 0, 0.0);
	}
	
	/**
	 * Sets the number directly.
	 * @param number The number to be set to.
	 */
	public void setNumber(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Number must be non-negative!");
		this.number = number;
	}
	
	/**
	 * Increases the number count by one.
	 */
	public void increaseNumber() {
		number++;
	}
	
	/**
	 * Returns the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the number.
	 * @return The number.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Returns the percentage in the form of a double number.
	 * @return The percentage.
	 */
	public double getPercentage() {
		return percentage;
	}
	
	/**
	 * Sets the percentage directly. 
	 * @param percentage
	 */
	public void setPercentage(double percentage) {
		if (percentage < 0)
			throw new IllegalArgumentException("Percentage must be non-negative!");
		
		this.percentage = percentage;
	}
	
	/**
	 * Computes the percentage by using the total certified applications.
	 * @param total Total certified applications.
	 */
	public void computePercentage(long total) {
		if (total <= 0)
			throw new IllegalArgumentException("Total must be positive!");
		
		percentage = (double) number/total;
	}
	
	/**
	 * Compares two output items according to their numbers.
	 * A item that is ahead has a larger number.
	 * Ties are broken by their names alphabetically.
	 */
	public int compareTo(OutputItem that) {
		if (this.number != that.number)
			return  that.number - this.number;
		return this.name.compareTo(that.name);
	}
}
