/**
 * 
 */
package gui;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.ItemHeadline;

/**
 * @author Daniel Draper
 * @version 1.0
 *
 */
public class HeadlineListener implements DocumentListener {

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	private ItemHeadline hl;
	private JTextField text;
	
	public HeadlineListener(ItemHeadline hl, JTextField text) {
		this.hl = hl;
		this.text = text;
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		warn();
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		warn();

	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		warn();

	}

	private void warn() {
		try {
			hl.setName(text.getText());
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		//MainWindow.getMainWindow().getLeftPanel().updateBuild();
	}
}
