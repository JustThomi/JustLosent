package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;
import level.tile.Tile;
// import tools.Vector2;

public class Projectile extends Entity {

    protected double direction;
    protected Sprite sprite;
    protected int speed;
    protected int damage;

    public Projectile(int x, int y, Level level, double dir) {
        super(x, y, level);
        this.direction = dir;
        this.speed = 2;
        this.damage = 5;
        // this.sprite = Sprite.bullet;
    }

    public void move() {
        this.pos.x += speed * Math.cos(direction);
        this.pos.y += speed * Math.sin(direction);
    }

    @Override
    public void render(Screen screen) {
        screen.renderTile((int) pos.x, (int) pos.y, Tile.bullet);
    }

    @Override
    public void update() {
        move();
    }

}
