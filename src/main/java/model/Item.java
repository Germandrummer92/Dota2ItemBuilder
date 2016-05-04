/**
 * 
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Daniel Draper
 * @version 1.0
 * This class represents an item that can be put into one of the guides.
 *
 */
public class Item {
	private String name;
	private String formattedName;
	private BufferedImage image;
	
	/**
	 * Creates an Item with the name and loads its
	 * @param name
	 */
	public Item(String name) {
		this.name = name;
		formattedName = "\"item_" + name.replace(" ", "_") + "\"";
		try {
			image = ImageIO.read(new File("res/images/" + name.replace(" ", "_") + "_lg" + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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
		//System.out.println(formattedName);
		return formattedName;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
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
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	

	
}
