package tealistfileconverter;

/**
 * Class for information regarding tea.
 * 
 * @author Thomas Ejnefjäll 
 */
public class Tea {	
	public String category;
	public String name;
	public int price;
	public String description;
	
	/**
	 * Constructs a Tea object.
	 */
	public Tea() {
		category = name = description = "";
	}
	/**
	 * Construct a Tea object.
	 * 
	 * @param category The tea category (black, red, herb tea etc).
	 * @param name The name of the tea.
	 * @param price The price.
	 * @param description A description containing flavors, how it is done etc.
	 */
	public Tea(String category, String name, int price, String description) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.description = description;
	}
}