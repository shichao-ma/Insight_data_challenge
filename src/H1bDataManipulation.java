import java.util.ArrayList;
import java.util.HashMap;

public class H1bDataManipulation {
	private final static String KEEP_COLUMN = "STATUS";
	private final static String KEEP_KEY = "CERTIFIED";
	
	/**
	 * Reads the csv file, does the counting for occupations and states, and writes the results to the output folder.	
	 * @param args Not used. They could be extended should more flexibility is needed.
	 */
	public static void main(String[] args){
//		should you need more flexibility, uncomment these and adjust accordingly
//		String inputDir = args[0];
//		String outputDir = args[1];
//		String outputHeader = args[2];
//		String columnCount = args[3];
//		String columnName = columnCount;
//		if (args.length > 4) {
//			columnName = args[4];
//		}
		
		String inputDir = "./input/h1b_input.csv";

		// read data
		CsvReaderWriter csvReader = new CsvReaderWriter(inputDir);
		csvReader.readCsv();
		ArrayList<ArrayList<String>> dataTable = csvReader.getContent();
		
		// occupations
		String outputFile = "./output/top_10_occupations.txt";
		String outputHeader = "TOP_OCCUPATIONS,NUMBER_CERTIFIED_APPLICATIONS,PERCENTAGE";
		String columnCount = "LCA_CASE_SOC_CODE";
		String columnName = "LCA_CASE_SOC_NAME";

		HashMap<String, OutputItem> counted = doCount(dataTable, columnCount, columnName);
		
		Output.GenerateOutput(counted, outputFile, outputHeader);
		// end occupations
		
		// states
		outputFile = "./output/top_10_states.txt";
		outputHeader = "TOP_STATES,NUMBER_CERTIFIED_APPLICATIONS,PERCENTAGE";
		columnCount = columnName = "LCA_CASE_EMPLOYER_STATE";

		counted = doCount(dataTable, columnCount, columnName);
		
		Output.GenerateOutput(counted, outputFile, outputHeader);
		// end states
		
	}
	
	/**
	 * A thin wrapper that does the actual counting.
	 * @param dataTable Data table (ArrayList<ArrayList<String>>) that is used for counting.
	 * @param columnCount Field that is counted upon.
	 * @param columnName Name that is used for generating the output.
	 * @return A hash map that contains counting results.  
	 */
	private static HashMap<String, OutputItem> doCount(ArrayList<ArrayList<String>> dataTable, String columnCount, String columnName) {
		Count count = new Count(dataTable);
		return count.countUnique(columnCount, columnName, KEEP_COLUMN, KEEP_KEY);
	}
	
}
