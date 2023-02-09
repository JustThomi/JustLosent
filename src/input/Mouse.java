package input;

import java.awt.event.MouseEvent;

import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MouseInputListener;

public class Mouse implements MouseInputListener, MenuDragMouseListener {

    private static int x = -1;
    private static int y = -1;
    private static int button = -1;

    /**
     * @return x coordonate
     */
    public static int getX() {
        return x;
    }

    /**
     * @return y coordonate
     */
    public static int getY() {
        return y;
    }

    /**
     * @return button
     */
    public static int getButton() {
        return button;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.event.MenuDragMouseListener#menuDragMouseEntered(javax.swing.
     * event.MenuDragMouseEvent)
     */
    @Override
    public void menuDragMouseEntered(MenuDragMouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.event.MenuDragMouseListener#menuDragMouseExited(javax.swing.event
     * .MenuDragMouseEvent)
     */
    @Override
    public void menuDragMouseExited(MenuDragMouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.event.MenuDragMouseListener#menuDragMouseDragged(javax.swing.
     * event.MenuDragMouseEvent)
     */
    @Override
    public void menuDragMouseDragged(MenuDragMouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.event.MenuDragMouseListener#menuDragMouseReleased(javax.swing.
     * event.MenuDragMouseEvent)
     */
    @Override
    public void menuDragMouseReleased(MenuDragMouseEvent e) {
    }

}
