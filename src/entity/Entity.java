package entity;

import java.util.Random;
import level.Level;
import graphics.Screen;
import tools.Vector2;

public class Entity {
    public Vector2 pos;
    protected boolean removed = false;
    public Level level;
    protected final Random RNG = new Random();

    /**
     * @param x
     * @param y
     * @param level
     */
    public Entity(int x, int y, Level level) {
        this.pos = new Vector2(x, y);
        this.level = level;
    }

    /**
     * Prototype for update
     */
    public void update() {

    }

    /**
     * Prototype for redering
     * 
     * @param screen
     */
    public void render(Screen screen) {

    }

    /**
     * Set removed tag to true
     */
    public void remove() {
        this.removed = true;
    }

    /**
     * @return true if entitie is removed
     */
    public boolean isRemoved() {
        return removed;
    }
}
