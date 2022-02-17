package tealistfileconverter;

import java.util.List;

import tealistfileconverter.converters.TeaListConverter;
import tealistfileconverter.converters.TeaListFactory;
import tealistfileconverter.converters.*;

/**
 * Handles parsing of options and conversion between different files 
 * containing information about tea.
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaListFileConverter 
{	
	/**
	 * Construct a TeaListFileConverter.
	 */
	private TeaListFileConverter() { }
	/**
	 * Process a tea list file converter request. The provided options (String[])
	 * must contain four values: [0] name of the file we want to read from,
	 * [1] file format of the file we want to read from, [2] name of the 
	 * file we want to create and copy the tea information to, and
	 * [3] the file format of the file we want to create.  
	 * 
	 * @param options The four options.
	 * @throws Exception If there is an error while converting the file. 
	 */
	public static void processRequest(String[] options) throws Exception {
		if(options.length != 4) {
			throw new IllegalArgumentException("Four parameters must be provided: FileFrom FormatFrom FileTo FormatTo");
		}
		convertFile(options[0], options[1], options[2], options[3]);
	}
	/**
	 * Convert a file containing tea information from one format to another.
	 *  
	 * @param fileFrom The source file containing the tea information.
	 * @param formatFrom The format of the source file.
	 * @param fileTo The destination file for the tea information.
	 * @param formatTo The format of the destination file. 
	 * @throws Exception If there is an error while converting the file. 
	 */
	private static void convertFile(String fileFrom, String formatFrom, String fileTo, String formatTo) throws Exception {

		if(!FileFormat.isValid(formatFrom) || !FileFormat.isValid(formatTo)) {
			throw new IllegalArgumentException("Format not supported. Valid formats: " + FileFormat.validFormats());
		}
                
                TeaListFactory factory = new TeaListFactory();
		TeaListConverter tlcFrom = null; //converter for the current file format
		TeaListConverter tlcTo = null; //converter for the new file format
		List<Tea> teaList = null;
		
                tlcFrom = factory.createConverter(formatFrom); //creates converter for the desired file format
                teaList = tlcFrom.convert(fileFrom);
                tlcTo = factory.createConverter(formatTo);
		tlcTo.convert(teaList, fileTo); //converts the file to desired file format
	}	
}