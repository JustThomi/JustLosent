package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[150];
    public boolean w, a, s, d;
    public boolean space, esc;

    public void update() {
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
