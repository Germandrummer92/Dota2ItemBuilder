/**
 *
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import com.example.dotaitembuilder.ItemIcon;
import model.Item;

/**
 * @author Daniel Draper
 * @version 1.0
 */
public class ImagePanel extends JScrollPane {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<Item> allItems;
    private JPanel contents;

    /**
     * @param jPanel
     */
    public ImagePanel(JPanel jPanel) {
        super(jPanel);
        this.contents = jPanel;
        allItems = new ArrayList<>();
        Border border = BorderFactory.createLineBorder(Color.black);
        setPreferredSize(new Dimension(400, 750));
        LayoutManager layout = new GridLayout(0, 5);
        contents.setLayout(layout);
        setBorder(border);
        buildItemPanel();
    }

    /**
     */
    private void buildItemPanel() {

        File[] itemFolder;
        itemFolder = new File(ImagePanel.class.getResource("/images").getFile()).listFiles();
        if (itemFolder != null) {
            for (File f : itemFolder) {
                String item = f.getName();
                item = item.replace(".png", "");
                item = item.replace("_lg", "");
                item = item.replace("_", " ");
                allItems.add(new Item(item, f));
            }
            for (Item i : allItems) {
                ItemIcon l = new ItemIcon(i);
                contents.add(l);
            }
        }
    }
}
