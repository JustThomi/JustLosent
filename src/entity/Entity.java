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

    public Entity(int x, int y, Level level) {
        this.pos = new Vector2(x, y);
        this.level = level;
    }

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        // rm from level code later
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
