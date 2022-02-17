package tealistfileconverter.converters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import tealistfileconverter.Tea;

/**
 * Converts files containing tea information from and to specific formats. 
 * 
 * @author Thomas Ejnefjäll
 */
public abstract class TeaListConverter {
	/**
	 * Read and parse a file containing tea information in a specific format into a List of Tea-objects.
	 * 
	 * @param fileName Name of the file containing the tea information.
	 * @return A List of Tea-objects
	 * @throws Exception If there is an error while converting the file. 
	 */
	public final List<Tea> convert(String fileName) throws Exception {
		File file = new File(fileName);

		if(!file.exists() || !file.isFile())
		{
			throw new IOException("The file " + fileName +  " does not exist");
		}		
		return readAndParse(fileName);
	}
	/**
	 * Parse a List of Tea-objects into a specific format and write it to a file.
	 * 
	 * @param teaList A List of Tea-objects.
	 * @param fileName Name of the file we want to write to.
	 * @throws Exception If there is an error while converting the file.
	 */
	public final void convert(List<Tea> teaList, String fileName) throws Exception {
		File file = new File(fileName);

		if(!file.exists())
		{
			file.createNewFile();
		}
		parseAndWrite(teaList, fileName);
	}	
	/**
	 * Read and parse a file containing tea information in a specific format into a List of Tea-objects. 
	 * 
	 * @param fileName Name of the file containing the tea information.
	 * @return A List of Tea-objects
	 * @throws Exception If there is an error while reading or parsing the file. 
	 */
	protected abstract List<Tea> readAndParse(String fileName)  throws Exception;	
	/**
	 * Parse a List of Tea-objects into a specific format and write it to a file.
	 * 
	 * @param teaList A List of Tea-objects.
	 * @param fileName Name of the file we want to write to.
	 * @throws Exception If there is an error while parsing or writing to the file.
	 */
	protected abstract void parseAndWrite(List<Tea> teaList, String fileName) throws Exception;
}
