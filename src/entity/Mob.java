package entity;

import level.Level;
import tools.Vector2;

public class Mob extends Entity {
    protected Vector2 direction;
    protected boolean isMoving = false;

    public Mob(int x, int y, Level level) {
        super(x, y, level);
    }

    public void update() {
    }

    public void move() {
    }

    public boolean collision() {
        // collision code later
        return false;
    }

    public void render() {
    }
}
