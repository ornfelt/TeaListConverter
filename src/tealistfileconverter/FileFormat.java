package tealistfileconverter;

/**
 * Enumeration for supported file formats.
 * 
 * @author Thomas Ejnefjäll
 */
public enum FileFormat {
	CSV("csv"), DSV("dsv"), XML("xml");
	
	private String fileFormat;
	
	/**
	 * Private constructor only for the enumeration itself.
	 * 
	 * @param fileFormat Name of the file format.
	 */
	private FileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	/**
	 * Check if the current file format is equal to another.
	 * 
	 * @param fileFormat A string containing the other file format.
	 * @return true it they are the same file format.
	 */
	public boolean equals(String fileFormat) {
		return this.fileFormat.equalsIgnoreCase(fileFormat);
	}
	/**
	 * Check if the current file format is equal to another.
	 * 
	 * @param fileFormat The other file format.
	 * @return true it they are the same file format.
	 */
	public boolean equals(FileFormat fileFormat) {
		return this.equals(fileFormat.fileFormat);
	}
	/**
	 * Check if the string contains a file format that is valid (part of the enumeration).
	 * 
	 * @param fileFormat A string containing the file format.
	 * @return true If the file format is valid (part of the enumeration).
	 */
	public static boolean isValid(String fileFormat) {
		for(FileFormat ff : FileFormat.values()) {
			if(ff.equals(fileFormat)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Check if the file format is valid (part of the enumeration).
	 * 
	 * @param fileFormat The file format we want to check.
	 * @return true If the file format is valid (part of the enumeration).
	 */
	public static boolean isValid(FileFormat fileFormat) {
		return FileFormat.isValid(fileFormat.toString());
	}
	/**
	 * Return a String with valid file formats.
	 * 
 	 * @return A String with valid file formats.
	 */
	public static String validFormats() {
		String validFormats = "";
		
		for(FileFormat f : FileFormat.values()) {
			validFormats += f.toString() + " ";
		}		
		return validFormats;
	}
	@Override
	public String toString() {
		return this.fileFormat;
	}
}