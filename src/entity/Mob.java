package entity;

import level.Level;
import graphics.Sprite;
import tools.Vector2;

public class Mob extends Entity {
    protected Sprite sprite;
    protected Vector2 direction;
    protected boolean isMoving = false;

    public Mob(int x, int y, Level level, Sprite sprite) {
        super(x, y, level);
        this.sprite = sprite;
    }

    public void update() {
    }

    public void move(Vector2 dir) {
    }

    public boolean collision() {
        // collision code later
        return false;
    }

    public void render() {
    }
}
