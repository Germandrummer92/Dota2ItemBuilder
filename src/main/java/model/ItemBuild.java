/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Draper
 * @version 1.0
 * This class represents a created ItemBuild for one hero.
 */
public class ItemBuild {
	private String author;
	private Hero hero;
	private String title;
	private List<ItemHeadline> headlines;
	
	/**
	 * Creates a new ItemBuild
	 * @param author
	 * @param hero
	 * @param title
	 */
	public ItemBuild(final String author, final String hero, String title) {
		this.author = author;
		this.hero = new Hero(hero);
		this.title = title;
		headlines = new ArrayList<>();
	}

	/**
	 * Creates an ItemBuild object from a newly opened File.
	 * @param opened
	 */
	public ItemBuild(File opened) {
		FileReader fr = null;
		try {
			fr = new FileReader(opened);
		} catch (FileNotFoundException e) { // Should never Happen
			e.printStackTrace();
		}
		if (fr != null) {
			
		}
	}


	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the headlines
	 */
	public List<ItemHeadline> getHeadlines() {
		return headlines;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param hero the hero to set
	 */
	public void setHero(String hero) {
		this.hero = new Hero(hero);
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param headlines the headlines to set
	 */
	public void setHeadlines(List<ItemHeadline> headlines) {
		this.headlines = headlines;
	}
	
	public void writeToDotaDir() throws FileAlreadyExistsException {
		writeToDir("C:/Program Files (x86)/Steam/steamapps/common/dota 2 beta/dota/itembuilds/");
	}
	
	/**
	 * Writes the Build to the directory specified formatted correctly.
	 * @param path which should be written to.
	 * @throws FileAlreadyExistsException 
	 */
	public void writeToDir(String path) throws FileAlreadyExistsException {
		File f = new File(path + "default_" + hero.getHeroNameBuild() + ".txt");
		PrintWriter writer = null;
		if (!f.exists()) {
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		else {
			throw new FileAlreadyExistsException(f.getAbsolutePath());
		}
		if (writer != null) {
			writer.append("\"itembuilds/test_axe.txt\"");
			writer.println();
			writer.append("{");
			writer.println();
			writer.append("\t\"author\"\t\"" + author + "\"");
			writer.println();
			writer.append("\t\"hero\"\t\t" + hero.getHeroNameFormatted());
			writer.println();
			writer.append("\t\"Title\"\t\t\"" + title + "\"");
			writer.println(); writer.println();
			writer.append("\t\"Items\"");
			writer.println();
			writer.append("\t{");
			for (ItemHeadline h : headlines) {
				writer.println();
				writer.append("\t\t" + h.getFormattedName());
				writer.println();
				writer.append("\t\t{");
				writer.println();
				for (Item i : h.getItems()) {
					writer.append("\t\t\t\"item\"\t\t" + i.getFormattedName());
					writer.println();
				}
				writer.append("\t\t}");
				writer.println();
			}
			writer.append("\t}");
			writer.println();
			writer.append("}");
			writer.close();
		}

	}
	
	@Override
	public String toString() {
		return "author: " + author + " hero: " + hero.getHeroNameBuild() + " title: " + title; 
	}

	/**
	 * @param string
	 */
	public void writeToDirOverwrite(String path) {
		File f = new File(path + "default_" + hero.getHeroNameBuild() + ".txt");
		f.delete();
		try {
			writeToDir(path);
		} catch (FileAlreadyExistsException e) { //Can never happen, since already deleted
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static void main(String args[]) {
		ItemBuild b = new ItemBuild("Samdrian", "Zeus", "Good Items for Zeus");
		ItemHeadline h = new ItemHeadline("Starting Items"); 
		h.addItem(new Item("clarity"));
		h.addItem(new Item("gauntlets"));
		b.getHeadlines().add(h);
		b.writeToDir("res/files/");
	}*/
}
