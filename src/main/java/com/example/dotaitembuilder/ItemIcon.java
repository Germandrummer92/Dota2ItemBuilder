/**
 *
 */
package com.example.dotaitembuilder;

//import java.awt.Point;

import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.MainWindow;
import model.Item;

/**
 * @author Daniel Draper
 * @version 1.0
 */
public class ItemIcon extends JLabel implements DragGestureListener, DragSourceListener, Transferable {

    /**
     * VersionID
     */
    private static final long serialVersionUID = 1L;
    private DragSource dragSource;
    private double startX;
    private double startY;
    Item curItem;

    public ItemIcon(Item i) {
        super();
        curItem = i;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
        //setIcon(new ImageIcon(i.getImage().getScaledInstance(50, 35, java.awt.Image.SCALE_DEFAULT)));
        setToolTipText(i.getName());
    }

    public void dragGestureRecognized(DragGestureEvent evt) {
        Transferable t = this;
        startX = evt.getDragOrigin().getX();
        startY = evt.getDragOrigin().getY();
        dragSource.startDrag(evt, DragSource.DefaultCopyDrop, t, this);
    }

    public void dragEnter(DragSourceDragEvent evt) {
        System.out.println("enters");
    }

    public void dragOver(DragSourceDragEvent evt) {

        System.out.println("over");
    }

    public void dragExit(DragSourceEvent evt) {
        System.out.println("leaves");
    }

    public void dropActionChanged(DragSourceDragEvent evt) {
        System.out.println("changes the drag action between copy or move");
    }

    /**
     * Drops the ImageIcon into the Headline thats adjacent to the drop point.
     *
     * @param evt
     */
    public void dragDropEnd(DragSourceDropEvent evt) {
        System.out.println("x: " + evt.getX() + " y: " + evt.getY());
        int i = 0;
        MainWindow.getMainWindow().pack();
        //Get Position to see if it's on left or right panel for start/end of drag
        double xDifEnd = evt.getX() - MainWindow.getMainWindow().getRightPanel().getLocationOnScreen().getX();
        double xDifStart = startX - MainWindow.getMainWindow().getRightPanel().getLocationOnScreen().getX();
        //if start was on left, end is on right, remove the dragged element
        if (xDifStart < 0 && xDifEnd > 0) {
            for (JPanel p : MainWindow.getMainWindow().getLeftPanel().getHeadlines()) {
                for (int j = 0; j < ((JPanel) p.getComponent(1)).getComponentCount(); j++) {
                    if ((((JPanel) p.getComponent(1)).getComponent(j)).equals(this)) {
                        ((JPanel) p.getComponent(1)).remove(j);
                        MainWindow.getMainWindow().pack();
                        MainWindow.getMainWindow().repaint();
                    }
                }
            }
        }
        System.out.println("x nach rechts: " + xDifEnd);
        //else we want to put it in the corresponding headline
        for (JPanel p : MainWindow.getMainWindow().getLeftPanel().getHeadlines()) {
            System.out.println("x: " + p.getLocationOnScreen().getX() + " y: " + p.getLocationOnScreen().getY());
            double xDif1 = evt.getX() - p.getLocationOnScreen().getX();
            double yDif1 = p.getLocationOnScreen().getY() - evt.getY();
            // double xDif2 = evt.getX() - MainWindow.getMainWindow().getLeftPanel().getHeadlines().get(i+i).getLocationOnScreen().getX();
            //If we have another headline following we have to make sure, its this headline and not the next one
            if (MainWindow.getMainWindow().getLeftPanel().getHeadlines().size() > i + 1 && i != 0) {
                //Distance to headline underneath
                double yDif2 = MainWindow.getMainWindow().getLeftPanel().getHeadlines().get(i + 1).getLocationOnScreen().getY() - evt.getY();
                if (yDif1 < 0 && yDif2 > 0) {
                    try {
                        ((JComponent) p.getComponent(1)).add((ItemIcon) clone());
                        MainWindow.getMainWindow().getLeftPanel().getCurBuild().getHeadlines().get(i).addItem(curItem);
                        MainWindow.getMainWindow().pack();
                        MainWindow.getMainWindow().repaint();
                        return;
                    } catch (ClassCastException e) { //I dont think this should happen but adding for security.
                        e.printStackTrace();
                    }

                }
            } else {
                if (xDif1 <= 500 && yDif1 < 0 && yDif1 >= -100) {
                    try {
                        ((JComponent) p.getComponent(1)).add((ItemIcon) clone());
                        MainWindow.getMainWindow().getLeftPanel().getCurBuild().getHeadlines().get(i).addItem(curItem);
                        MainWindow.getMainWindow().pack();
                        MainWindow.getMainWindow().repaint();
                        return;
                    } catch (ClassCastException e) { //I dont think this should happen but adding for security.
                        e.printStackTrace();
                    }
                }
            }
            i++;
        }

    }

    /* (non-Javadoc)
     * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor dv1 = new DataFlavor(this.getClass(), "ItemIcon");
        DataFlavor[] newDV = {dv1};
        return newDV;
    }

    /* (non-Javadoc)
     * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.DataFlavor)
     */
    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        // TODO Auto-generated method stub
        return this;
    }

    @Override

    public Object clone() {
        return new ItemIcon(curItem);

    }
}