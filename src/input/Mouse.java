package input;

import java.awt.event.MouseEvent;

import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MouseInputListener;

public class Mouse implements MouseInputListener, MenuDragMouseListener {

    private static int x = -1;
    private static int y = -1;
    private static int button = -1;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getButton() {
        return button;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void menuDragMouseEntered(MenuDragMouseEvent e) {
    }

    @Override
    public void menuDragMouseExited(MenuDragMouseEvent e) {
    }

    @Override
    public void menuDragMouseDragged(MenuDragMouseEvent e) {
    }

    @Override
    public void menuDragMouseReleased(MenuDragMouseEvent e) {
    }

}
