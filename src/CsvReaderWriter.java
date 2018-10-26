import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
/**
 * The CsvReaderWriter class provides some basic functionality to write and read csv files. 
 * In particular, it can read a csv file to an ArrayList<ArrayList<String>>, or
 * write an ArrayList<ArrayList<String>> to a csv file, using
 * the default separator (;) or a user supplied separator.
 * @author Shichao Ma
 * @version 0.1
 */
public class CsvReaderWriter {
	private final static String DEFAULT_SEPARATOR = ";";
    private String separator;
    private String file; // file to be read or written on
    private ArrayList<ArrayList<String>> csvContent;
    
    /**
     * Creates a csv reader/writer that is used primarily for reading a csv file with the default separator.
     * @param file A String that represents the csv file to be read/write.
     */
    public CsvReaderWriter(String file) {
    	this(file, DEFAULT_SEPARATOR, new ArrayList<ArrayList<String>>());
    }
    
    /**
     * Creates a csv reader/writer that is used for primarily for reading a csv file with the user supplied separator.
     * @param file A String that contains the directory the csv file to be read/write.
     * @param separator User supplied separator.
     */
    public CsvReaderWriter(String file, String separator) {
    	this(file, DEFAULT_SEPARATOR, new ArrayList<ArrayList<String>>());
    }
    
    /**
     * Creates a csv reader/writer that is used for primarily for writing a csv file.
     * The file content must be provided.
     * It uses the default separator.
     * @param file A String that contains the directory the csv file to be read/write.
     * @param csvContent File content.
     */
    public CsvReaderWriter(String file, ArrayList<ArrayList<String>> csvContent) {
    	this(file, DEFAULT_SEPARATOR, csvContent);
    }
    
    /**
     * Creates a csv reader/writer that is used for primarily for writing a csv file.
     * The file content must be provided.
     * It uses the user supplied separator.
     * @param file A String that contains the directory the csv file to be read/write.
     * @param separator User supplied separator.
     * @param csvContent File content.
     */
    public CsvReaderWriter(String file, String separator, ArrayList<ArrayList<String>> csvContent) {
    	this.file = file;
    	this.separator = separator;
    	this.csvContent = csvContent;
    }
    
    /**
     * Returns the content.
     * @return Return the content.
     */
    public ArrayList<ArrayList<String>> getContent(){
    	return csvContent;
    }
    
    /**
     * Sets the content for writing.
     * @param csvContent File content
     */
    public void setContent(ArrayList<ArrayList<String>> csvContent){
    	this.csvContent = csvContent;
    }
    
    /**
     * Reads the provided csv file and stores its content into the class.
     */
    public void readCsv() {
    	String line;
    	
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {        	
            while ((line = br.readLine()) != null) {
            	ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split(separator)));
            	
            	// remove quotation marks
            	ListIterator<String> iterator = row.listIterator();
            	while (iterator.hasNext()) {
            	     String string = iterator.next();
            	     if (string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"')
            	     {
            	    	 iterator.set(string.substring(1, string.length() - 1));
            	     }
            	 }
//            	check to make sure quotation marks are removed
//            	System.out.println(row);
            	
                csvContent.add(row); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Writes the content to the provided csv file. 
     * It will create the directory and the file if not existed, and
     * overwrite the content if the file is already existed.
     */
    public void writeCsv() {
    	String line;
    	Path path = Paths.get(file);
        try {
			if (!Files.exists(path.getParent()))
				Files.createDirectories(path.getParent());
			if (!Files.exists(path))
				Files.createFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {  
            for (int rowIndex = 0; rowIndex < csvContent.size(); rowIndex++) {            	
            	line = convertRowToString(csvContent.get(rowIndex));
                bw.write(line);  
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * A helper method to convert each row from ArrayList<String> to a single String.
     * It uses the separator to do the conversion.
     * @param thisRow An ArrayList<String> to be converted.
     * @return A converted String.
     */
    private String convertRowToString(ArrayList<String> thisRow){
        StringBuilder row = new StringBuilder();
        
        for(String str : thisRow){
            if(row.length() != 0){
            	row.append(separator);
            }
            row.append(str);
        }
        return row.toString();
    }
}
