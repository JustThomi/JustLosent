package entity;

import java.util.ArrayList;

import level.Level;
import tools.Vector2;

public class Mob extends Entity {
    protected Vector2 direction;
    protected boolean isMoving = false;

    protected int health;

    public ArrayList<Entity> shots = new ArrayList<Entity>();

    public Mob(int x, int y, Level level) {
        super(x, y, level);
    }

    public void setHealth(int nr) {
        this.health = nr;
    }

    public int getHealth() {
        return this.health;
    }
}
