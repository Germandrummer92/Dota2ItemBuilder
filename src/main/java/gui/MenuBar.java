package gui; /**
 *
 */

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.ItemBuild;

/**
 * @author Daniel Draper
 * @version 1.0
 */
public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MenuBar() {
        super();
        createNewFileMenu();
        add(fileMenu);

    }

    /**
     * Set-Up Method to create the file menu
     */
    private void createNewFileMenu() {
        JMenuItem newBuild;
        newBuild = new JMenuItem("New Build");
        newBuild.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                new NewBuildWindow();
            }
        });
        JMenuItem saveBuild;
        saveBuild = new JMenuItem("Save Build");
        saveBuild.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                try {
                    if (MainWindow.getMainWindow().getLeftPanel().getCurBuild() != null) {
                        try {
                            MainWindow.getMainWindow().getLeftPanel().getCurBuild().writeToDir("res/files/");
                        } catch (FileAlreadyExistsException e) {
                            int overwrite = JOptionPane.showConfirmDialog(MainWindow.getMainWindow(), "A Build for this Hero already exists in your Directory! Overwrite?");
                            if (overwrite == 0) {
                                MainWindow.getMainWindow().getLeftPanel().getCurBuild().writeToDirOverwrite("res/files/");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainWindow.getMainWindow(), "You can't save a non-existant Build!");
                    }
                } catch (HeadlessException e) { //Can't Happen

                    e.printStackTrace();
                }
            }
        });
        JMenuItem openBuild;
        openBuild = new JMenuItem("Open Build");
        //needs Check for real ItemBuild File
        openBuild.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("/res/files"));
                int returnVal = fc.showOpenDialog(MainWindow.getMainWindow());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File opened = fc.getSelectedFile();
                    MainWindow.getMainWindow().getLeftPanel().addItemBuild(new ItemBuild(opened));
                }
            }
        });
        fileMenu = new JMenu("File");
        fileMenu.add(newBuild);
        fileMenu.add(saveBuild);
        fileMenu.add(openBuild);

    }
}
