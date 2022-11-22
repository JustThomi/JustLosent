package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;
import level.tile.Tile;
// import tools.Vector2;

public class Projectile extends Entity {

    protected Sprite sprite;
    // stats
    protected double direction;
    protected int speed;
    protected int damage;

    // range/lifespan of bullet
    protected int lifespan;

    public Projectile(int x, int y, Level level, double dir) {
        super(x, y, level);
        this.direction = dir;
        this.speed = 2;
        this.damage = 5;
        this.lifespan = 100;
        this.removed = false;
        // this.sprite = Sprite.bullet;
    }

    public void move() {
        this.pos.x += speed * Math.cos(direction);
        this.pos.y += speed * Math.sin(direction);
    }

    public void timer(){
        lifespan--;
        if (lifespan <= 0){
            this.remove();
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderTile((int) pos.x, (int) pos.y, Tile.bullet);
    }

    @Override
    public void update() {
        move();
        timer();
    }

}
