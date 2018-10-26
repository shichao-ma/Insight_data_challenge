import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map; 

/**
 * The class Count takes a data table, does the counting and calculate the percentages. 
 * @author Shichao Ma
 *
 */
public class Count {
	private ArrayList<ArrayList<String>> content; // data table
	
	/**
	 * Constructs a Count object and initializes it with its content.
	 * @param content
	 */
	public Count(ArrayList<ArrayList<String>> content) {
		this.content = content;
	}
	
	/**
	 * Counts how many items exist for each unique entry and computes percentages.
	 * The result is a hash map that contains the counting field and an object of all output fields. 
	 * @param columnCount The column that counting is based on (the counting field).
	 * @param columnName The name that is associated with the counting field.
	 * @param keepColumn The column that contains certified information.
	 * @param keepKey Which value is certified.
	 * @return The counting result.
	 * @throws NoSuchColumnException throws if no such column exists.
	 */
	public HashMap<String, OutputItem> countUnique(String columnCount, String columnName, String keepColumn, String keepKey) throws NoSuchColumnException  {
		ArrayList<String> header = content.get(0);
		
		if (!header.contains(columnCount)) 
			throw new NoSuchColumnException("No such column!");
		
		if (!header.contains(columnName))
			throw new NoSuchColumnException("No such column name!");
		
		if (!header.contains(keepColumn))
			throw new NoSuchColumnException("No such keep column!");
		
		int columnIndex = header.indexOf(columnCount);
		int nameIndex = header.indexOf(columnName);
		int keepIndex = header.indexOf(keepColumn);
		HashMap<String, OutputItem> counted = new HashMap<>();
		String key;
		String name;
		long totalCount = 0;
		
		// count
		for (int j = 1; j < content.size(); j++) {
			if (!content.get(j).get(keepIndex).equals(keepKey))
				continue;
			
			key = content.get(j).get(columnIndex);
			totalCount++;
			
			if(counted.containsKey(key)) {
				counted.get(key).increaseNumber();
			} else {
				// a new item, find its name and add into the map
				name = content.get(j).get(nameIndex);
				counted.put(key, new OutputItem(name, 1));
			}	
		}
		
		return computePercentage(counted, totalCount);		
	}
	
	/**
	 * Computes percentage after counting.
	 * @param counted The counting result with no percentage information.
	 * @param total Total certified applications.
	 * @return The counting result with percentage information updated.
	 */
	private HashMap<String, OutputItem> computePercentage(HashMap<String, OutputItem> counted, long total) {
		for (Map.Entry<String, OutputItem> entry : counted.entrySet())
		{
		    entry.getValue().computePercentage(total);
		}
		return counted;
	}
}
