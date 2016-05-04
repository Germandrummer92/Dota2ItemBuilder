/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Item;
import model.ItemBuild;
import model.ItemHeadline;

/**
 * @author Daniel Draper
 * @version 1.0
 * The left panel of the Main window responsible for showing the current Build being worked on.
 */
public class WorkingBuildPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contents;
	private Border border;
	private ItemBuild curBuild;
	private JPanel titleLine;
	private JPanel build;
	private ArrayList<JPanel> headlines;
	/**
	 * @param jPanel
	 */
	public WorkingBuildPanel(JPanel jPanel) {
		super(jPanel);
		//setEnabled(true);
		contents = jPanel;
		contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));
		titleLine = new JPanel();
		JPanel title1 = new JPanel();
		title1.add(new JLabel("No Build created yet! Create one in the File menu!"));
		titleLine.add(title1);
		titleLine.setLayout(new BoxLayout(titleLine, BoxLayout.PAGE_AXIS));
		contents.add(titleLine);
		build = new JPanel();
		build.setLayout(new BoxLayout(build, BoxLayout.PAGE_AXIS));
		contents.add(build);
		setPreferredSize(new Dimension(300, 750));
		border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
		addMouseListener(new BuildListener(this));
		headlines = new ArrayList<JPanel>();
	}
	/**
	 * @return the titleLine
	 */
	public JPanel getTitleLine() {
		return titleLine;
	}
	/**
	 * @param titleLine the titleLine to set
	 */
	public void setTitleLine(JPanel titleLine) {
		this.titleLine = titleLine;
	}
	/**
	 * @return the headlines
	 */
	public ArrayList<JPanel> getHeadlines() {
		return headlines;
	}
	/**
	 * @param headlines the headlines to set
	 */
	public void setHeadlines(ArrayList<JPanel> headlines) {
		this.headlines = headlines;
	}
	/**
	 * @param newBuild the new Build to show in the panel
	 */
	public void addItemBuild(ItemBuild newBuild) {
		if (curBuild != null) {
			try {
				MainWindow.getMainWindow().getLeftPanel().getCurBuild().writeToDir("res/files/");
			} catch (FileAlreadyExistsException e) {
				int overwrite = JOptionPane.showConfirmDialog(MainWindow.getMainWindow(), "A Build for this Hero already exists in your Directory! Overwrite?");
				if (overwrite == 0) {
					MainWindow.getMainWindow().getLeftPanel().getCurBuild().writeToDirOverwrite("res/files/");
					titleLine.remove(1);
				}
			}
}
			
		curBuild = newBuild;
		JPanel title2 = new JPanel();
		title2.add(new JLabel("Author: " + curBuild.getAuthor()));
		title2.add(new JLabel("Hero: " + curBuild.getHero().toString()));
		title2.add(new JLabel("Title: " + curBuild.getTitle()));
		titleLine.add(title2);
		headlines = new ArrayList<JPanel>();
		contents.remove(build);
		build = new JPanel();
		build.setLayout(new BoxLayout(build, BoxLayout.PAGE_AXIS));
		contents.add(build);
		MainWindow.getMainWindow().pack();
		
	}
	
	/**
	 * Updates the Jpanel to show newly added items/headlines to the build
	 */
	public void updateBuild() {
		if (curBuild == null) {
			return;
		}
		if (build == null) {
			build = new JPanel();
		}
		else {
			for (ItemHeadline ih : curBuild.getHeadlines()) {
				for (JPanel hl : headlines) {
					build.remove(hl);
					headlines.remove(hl);
				}
				JPanel updatedHL = new JPanel();
				JTextField updatedHLField = new JTextField(ih.getName());
				updatedHLField.getDocument().addDocumentListener(new HeadlineListener(ih, updatedHLField));
				updatedHL.add(updatedHLField);
				for (Item i : ih.getItems()) {
					JLabel l = new JLabel();
					l.setIcon(new ImageIcon(i.getImage().getScaledInstance(50, 35, java.awt.Image.SCALE_DEFAULT)));
					l.setToolTipText(i.getName());
					updatedHL.add(l);
				}
				headlines.add(updatedHL);
				build.add(updatedHL);
			}
		}
		MainWindow.getMainWindow().pack();
		MainWindow.getMainWindow().repaint();
	}
	/**
	 * @return the curBuild
	 */
	public ItemBuild getCurBuild() {
		return curBuild;
	}
	/**
	 * @param curBuild the curBuild to set
	 */
	public void setCurBuild(ItemBuild curBuild) {
		this.curBuild = curBuild;
	}
	/**
	 * Called when a new Item Headline JPanel should be created and added to the current Build (Panel).
	 */
	public void addNewItemHeadline() {
		if (curBuild == null) {
			JOptionPane.showMessageDialog(this, "Please create a Build before trying to create an Item Headline");
			return;
		}
		JPanel newHeadline = new JPanel();
		JPanel newHeadlineItems = new JPanel();
		newHeadlineItems.setLayout(new GridLayout(0, 5));
		JPanel newHeadlineText = new JPanel();
		JTextField newHeadlineField = new JTextField("NEW HEADLINE");
		ItemHeadline hl = new ItemHeadline(newHeadlineField.getText());
		newHeadlineField.getDocument().addDocumentListener(new HeadlineListener(hl, newHeadlineField));
		newHeadline.setLayout(new BoxLayout(newHeadline, BoxLayout.PAGE_AXIS));
		newHeadlineText.add(newHeadlineField);
		curBuild.getHeadlines().add(hl);
		newHeadline.add(newHeadlineText);
		newHeadline.add(newHeadlineItems);
		headlines.add(newHeadline);
		build.add(newHeadline);
		MainWindow.getMainWindow().pack();
		MainWindow.getMainWindow().repaint();

		
	}
}
