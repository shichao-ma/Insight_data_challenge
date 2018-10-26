import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
/**
 * A static class that prepares the output,
 * and calls a csv writer to write the csv file.
 * @author Shichao Ma
 *
 */
public final class Output {
	private final static int DEFAULT_OUTPUT_LENGTH = 10;
	
	private Output() {
	}
	
	/**
	 * Generates output with counting results and output file. 
	 * No table header is provided and the length of the table is set to default.
	 * @param counted A hash map that contains counting results. 
	 * @param outputFile A String that contains the directory the csv file to be write.
	 */
	public static void GenerateOutput(HashMap<String, OutputItem> counted, String outputFile) {
		GenerateOutput(counted, outputFile, new String(), DEFAULT_OUTPUT_LENGTH);
	}
	
	/**
	 * Generates output with counting results and output file with a user provided table header.
	 * The length of the table is set to default.
	 * @param counted A hash map that contains counting results. 
	 * @param outputFile A String that contains the directory the csv file to be write.
	 * @param header A String that contains the header, separated by a comma (,).
	 */
	public static void GenerateOutput(HashMap<String, OutputItem> counted, String outputFile, String header) {
		GenerateOutput(counted, outputFile, header, DEFAULT_OUTPUT_LENGTH);
	}
	
	/**
	 * Generates output with counting results and output file with a user provided table header and
	 * a user provided table length.
	 * @param counted A hash map that contains counting results. 
	 * @param outputFile A String that contains the directory the csv file to be write.
	 * @param header A String that contains the header, separated by a comma (,).
	 * @param length The length of the output table.
	 */
	public static void GenerateOutput(HashMap<String, OutputItem> counted, String outputFile, String header, int length) {
		ArrayList<OutputItem> outputSorted = sortOutput(counted);
		ArrayList<ArrayList<String>> OutputItem = new ArrayList<>();
		if (!header.isEmpty())
			OutputItem.add(convertHeader(header));
		
		ArrayList<String> row = new ArrayList<>();
		int bound = Math.min(length, outputSorted.size());
		
		for (int i = 0; i < bound; i++) {
			row.add(outputSorted.get(i).getName());
			row.add(Integer.toString(outputSorted.get(i).getNumber()));
			row.add(String.format("%.1f%%", 100 * outputSorted.get(i).getPercentage()));
			OutputItem.add(new ArrayList<String>(row));
			row.clear();
		}
		
		CsvReaderWriter csvWriter = new CsvReaderWriter(outputFile, OutputItem);
		csvWriter.writeCsv();
	}
	
	/**
	 * Sorts the output.
	 * @param counted A hash map that contains counting results. 
	 * @return Sorted result.
	 */
	private static ArrayList<OutputItem> sortOutput(HashMap<String, OutputItem> counted) {
		ArrayList<OutputItem> outputSorted = new ArrayList<>(counted.values());
		Collections.sort(outputSorted);
		return outputSorted;
	}
	
	/**
	 * Converts the header to an ArrayList<String>
	 * @param header The header to be converted.
	 * @return Returns the converted result.
	 */
	private static ArrayList<String> convertHeader(String header){
		ArrayList<String> headerArray = new ArrayList<>(Arrays.asList(header.split(",")));
		return headerArray;
	}
}
