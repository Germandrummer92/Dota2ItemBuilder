/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Hero;
import model.ItemBuild;

/**
 * @author Daniel Draper
 * @version 1.0
 *
 */
public class NewBuildWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container contents;
	private JLabel titlePrompt;
	private JLabel heroPrompt;
	private JLabel userPrompt;
	private JTextField titleField;
	private JTextField userField;
	private JComboBox heroField;
	
	public NewBuildWindow() {
		super("Create a new Item Build");
		Dimension preferredSize = new Dimension(550, 250);
		setPreferredSize(preferredSize);
		setLocation(350, 100);
		contents = getContentPane();
		//Set up contents
		titlePrompt = new JLabel("Please enter the title of the new build:");
		titlePrompt.setBorder(BorderFactory.createLineBorder(Color.black));
		heroPrompt = new JLabel("Please select the name of the hero this build should be for:");
		heroPrompt.setBorder(BorderFactory.createLineBorder(Color.black));
		userPrompt = new JLabel("Please enter the username who created this build:");
		userPrompt.setBorder(BorderFactory.createLineBorder(Color.black));
		titleField = new JTextField(10);
		titleField.setBounds(titleField.getX(), titleField.getY(), 1, 1);
		userField = new JTextField(10);
		userField.setBounds(userField.getX(), userField.getY(), 1, 1);
		heroField = new JComboBox<String>(Hero.getHeroStrings());
		JPanel promptPane = new JPanel();
		promptPane.setLayout(new BoxLayout(promptPane,BoxLayout.PAGE_AXIS));
		promptPane.add(titlePrompt);
		promptPane.add(Box.createRigidArea(new Dimension(100, 100)));
		promptPane.add(heroPrompt);
		promptPane.add(Box.createRigidArea(new Dimension(100, 100)));
		promptPane.add(userPrompt);
		contents.add(promptPane, BorderLayout.WEST);
		JPanel fieldPane = new JPanel();
		fieldPane.setLayout(new BoxLayout(fieldPane, BoxLayout.PAGE_AXIS));
		fieldPane.add(titleField);
		fieldPane.add(Box.createRigidArea(new Dimension(100, 100)));
		fieldPane.add(heroField);
		fieldPane.add(Box.createRigidArea(new Dimension(50, 50)));
		fieldPane.add(userField);
		contents.add(fieldPane, BorderLayout.EAST);
		JPanel bot1 = new JPanel();
		bot1.setLayout(new GridLayout(0, 2));
		JButton create = new JButton("Create");
		JButton cancel = new JButton("Cancel");
		bot1.add(create);
		bot1.add(cancel);
		//Set up Buttons
		cancel.addMouseListener(new MouseAdapter()
		{
		    public void mousePressed(MouseEvent arg0)
		    {
		       setVisible(false);
		    }
		});
		create.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent arg0)
			{
				ItemBuild newBuild = new ItemBuild(userField.getText(), (String)heroField.getSelectedItem(), titleField.getText()); //Create Itembuild
				((JLabel)((JPanel)MainWindow.getMainWindow().getLeftPanel().getTitleLine().getComponent(0)).getComponent(0)).setText("The current Build is:");
				MainWindow.getMainWindow().getLeftPanel().addItemBuild(newBuild); // show itembuild
				setVisible(false); //close window
			}
		});
		contents.add(bot1, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

}
