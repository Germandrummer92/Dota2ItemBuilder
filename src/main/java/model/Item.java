/**
 *
 */
package model;

import com.vaadin.server.Resource;
import com.vaadin.ui.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Daniel Draper
 * @version 1.0
 *          This class represents an item that can be put into one of the guides.
 */
@Slf4j
public class Item implements Comparable  {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String formattedName;
    @Getter
    @Setter
    private Image image;

    /**
     * Creates an Item with the name and loads its image.
     *
     * @param name the name for the item.
     * @param file the file for the item's image.
     */
    public Item(final String name, final File file) {
        this.name = name;
        this.formattedName = "\"item_" + name.replace(" ", "_") + "\"";
        if (file != null) {
            image = new Image(file.getAbsolutePath());

        }

    }

    /**
     * Creates an Item without an associated File.
     * @param name the name for the item
     */
    public Item(final String name) {
        this(name, null);
    }

    @Override
    public int compareTo(final Object o) {
        if (!(o instanceof Item)) {
            return -1;
        }
        else {
            return name.compareTo(((Item) o).getName());
        }
    }
}
