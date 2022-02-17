package tealistfileconverter;

/**
 * Program entry point. The provided options (String[]) must contain four values: 
 * [0] name of the file we want to read from, [1] file format of the file we want 
 * to read from, [2] name of the file we want to create and copy the tea information 
 * to, and [3] the file format of the file we want to create. See readme file for 
 * more information.
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaListFileConverterMain {

	/**
	 * Program entry point. The provided options (String[]) must contain four values: 
	 * [0] name of the file we want to read from, [1] file format of the file we want 
	 * to read from, [2] name of the file we want to create and copy the tea information 
	 * to, and [3] the file format of the file we want to create. See readme file for 
	 * more information.
	 * 
	 * @param options Program parameters. 
	 */
	public static void main(String[] options) {
		try {
			TeaListFileConverter.processRequest(new String[]{"tesorter-UTF-8.csv", "csv", "test.xml", "xml"});
			System.out.println("Convertion complete");
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		}
	}
}