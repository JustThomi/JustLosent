package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;
import level.tile.Tile;
import main.Game;

public class Projectile extends Entity {

    protected Sprite sprite;
    // stats
    protected double direction;
    protected int speed;
    protected int damage;

    // range/lifespan of bullet
    protected int lifespan;

    /**
     * @param x
     * @param y
     * @param level
     * @param dir
     */
    public Projectile(int x, int y, Level level, double dir) {
        super(x, y, level);
        this.direction = dir;
        this.speed = 1;
        this.damage = 5;
        this.lifespan = 100;
        this.removed = false;
    }

    /**
     * @return true if colliding
     */
    public boolean isColliding() {
        boolean colliding = false;

        for (int i = 0; i < 4; i++) {
            int xColl = (int) ((this.pos.x) + i % 2 + 5) / 16;
            int yColl = (int) ((this.pos.y) + i / 2 + 13) / 16;

            if (level.getTile(xColl, yColl).isSolid()) {
                colliding = true;
            }
        }
        return colliding;
    }

    /**
     * @return true if player is hit
     */
    public boolean playerHit() {
        boolean hit = false;

        for (int i = 0; i < 4; i++) {
            int xColl = (int) ((this.pos.x) + i % 2 + 5) / 16;
            int yColl = (int) ((this.pos.y) + i / 2 + 13) / 16;

            if (xColl * 16 > Game.player.pos.x - 16 && xColl * 16 < Game.player.pos.x + 16
                    && yColl * 16 > Game.player.pos.y - 16
                    && yColl * 16 < Game.player.pos.y + 16) {
                hit = true;
            }
        }
        return hit;
    }

    /**
     * Move bullet
     */
    public void move() {
        if (!this.isColliding() && !this.playerHit()) {
            this.pos.x += speed * Math.cos(direction);
            this.pos.y += speed * Math.sin(direction);
        } else if (this.playerHit()) {
            dealDamage(damage);
            this.removed = true;
        } else {
            this.removed = true;
        }
    }

    /**
     * @param dmg deal damage to player if hit
     */
    public void dealDamage(int dmg) {
        Game.player.setHealth(Game.player.getHealth() - dmg);
    }

    /**
     * Update bullet timer. Remove bullet if lifespan is zero
     */
    public void timer() {
        lifespan--;
        if (lifespan <= 0) {
            this.remove();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#render(graphics.Screen)
     */
    @Override
    public void render(Screen screen) {
        screen.renderTile((int) pos.x, (int) pos.y, Tile.knife);
    }

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#update()
     */
    @Override
    public void update() {
        move();
        timer();
    }

}
