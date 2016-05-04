/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Draper
 * @version 1.0
 * This class represents one headline in the item build
 */
public class ItemHeadline {

	private String name;
	private String formattedName;
	private List<Item> items;
	
	/**
	 * Creates a new Headline
	 * @param name
	 */
	public ItemHeadline(String name) {
		this.name = name.replace(' ', '_');
		formattedName = "\"#DOTA_Item_Build_" + this.name + "\"";
		items = new ArrayList<Item>();
	}
	
	/**
	 * Adds an Item to this headline
	 * @param itemToAdd
	 */
	public void addItem(Item itemToAdd) {
		items.add(itemToAdd);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the formattedName
	 */
	public String getFormattedName() {
		return formattedName;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param formattedName the formattedName to set
	 */
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
