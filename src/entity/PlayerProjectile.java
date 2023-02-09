package entity;

import level.Level;

import graphics.Screen;
import graphics.Sprite;
import level.tile.Tile;

public class PlayerProjectile extends Entity {
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
    public PlayerProjectile(int x, int y, Level level, double dir) {
        super(x, y, level);
        this.direction = dir;
        this.speed = 2;
        this.damage = 5;
        this.lifespan = 100;
        this.removed = false;
        // this.sprite = Sprite.bullet;
    }

    /**
     * @return true if bullet is colliding
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
     * @return true if projkectile collides with mob
     */
    public boolean mobHit() {
        boolean hit = false;

        for (int i = 0; i < 4; i++) {
            int xColl = (int) ((this.pos.x) + i % 2 + 5) / 16;
            int yColl = (int) ((this.pos.y) + i / 2 + 13) / 16;

            for (Mob mob : Spawner.getMobs()) {
                if (xColl * 16 > mob.pos.x - 10 && xColl * 16 < mob.pos.x + 10
                        && yColl * 16 > mob.pos.y - 16
                        && yColl * 16 < mob.pos.y + 16) {
                    hit = true;
                    dealDamage(damage, mob);
                }
            }
        }
        return hit;
    }

    /**
     * move projectile
     */
    public void move() {
        if (!this.isColliding() && !this.mobHit()) {
            this.pos.x += speed * Math.cos(direction);
            this.pos.y += speed * Math.sin(direction);
        } else {
            this.removed = true;
        }
    }

    /**
     * Deal damage to mob that collided with bullet
     * 
     * @param dmg
     * @param mob
     */
    public void dealDamage(int dmg, Mob mob) {
        mob.setHealth(mob.getHealth() - dmg);
    }

    /**
     * Timer for bullet lifespen. Removes bullet when timer hits 0
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
        screen.renderTile((int) pos.x, (int) pos.y, Tile.bullet);
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