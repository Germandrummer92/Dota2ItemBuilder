/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Item;

/**
 * @author Daniel Draper
 * @version 1.0
 *
 */
public class MainWindow extends JFrame {
	
	private Container contents;
	private MenuBar menuBar;
	private WorkingBuildPanel leftPanel;
	private ImagePanel rightPanel;
	private Dimension preferredSize;
	private static MainWindow singleton;
	private static final long serialVersionUID = 1L;

	/**
	 * @return the contents
	 */
	public Container getContents() {
		return contents;
	}

	/**
	 * @return the menuBar
	 */
	public MenuBar getMainMenuBar() {
		return menuBar;
	}

	/**
	 * @return the leftPanel
	 */
	public WorkingBuildPanel getLeftPanel() {
		return leftPanel;
	}

	/**
	 * @return the rightPanel
	 */
	public ImagePanel getRightPanel() {
		return rightPanel;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(Container contents) {
		this.contents = contents;
	}

	/**
	 * @param menuBar the menuBar to set
	 */
	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * @param leftPanel the leftPanel to set
	 */
	public void setLeftPanel(WorkingBuildPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	/**
	 * @param rightPanel the rightPanel to set
	 */
	public void setRightPanel(ImagePanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	/**
	 * Creates and paints the Main Window used by the Item Builder
	 */
	private MainWindow() {
		super("Dota2ItemBuilder");
		contents = getContentPane();
		leftPanel = new WorkingBuildPanel(new JPanel());
		rightPanel = new ImagePanel(new JPanel());
		menuBar = new MenuBar();
		preferredSize = new Dimension(750, 750);
		setPreferredSize(preferredSize);
		setLocation(375, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contents.add(menuBar, BorderLayout.NORTH);
		contents.add(leftPanel, BorderLayout.WEST);
		contents.add(rightPanel, BorderLayout.EAST);
		pack();
		setVisible(true);
	}
	
	public static MainWindow getMainWindow() {
		if (singleton == null) {
			singleton = new MainWindow();
			return singleton;
		}
		else {
			return singleton;
		}
	}
}
