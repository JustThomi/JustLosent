package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;
import main.Game;
import spells.Spell;
import spells.ThrowKnife;

public class ShootingEnemy extends Mob {
    protected Sprite sprite;
    protected Spell attackSpell;
    protected int points;

    /**
     * @param x
     * @param y
     * @param level
     */
    public ShootingEnemy(int x, int y, Level level) {
        super(x, y, level);
        this.sprite = Sprite.shootingMob;
        this.attackSpell = new ThrowKnife(this, 200, Game.player);
        this.health = 100;
        this.points = 10;
    }

    /**
     * Remove enemy when health is equal or less than 0
     */
    public void die() {
        removed = true;
        Game.player.addScore(points);
    }

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#update()
     */
    public void update() {
        attackSpell.use();
        attackSpell.update();

        if (health <= 0) {
            die();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#render(graphics.Screen)
     */
    public void render(Screen screen) {
        screen.renderSprite((int) this.pos.x, (int) this.pos.y, this.sprite);
    }

}
