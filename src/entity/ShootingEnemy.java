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

    public ShootingEnemy(int x, int y, Level level) {
        super(x, y, level);
        this.sprite = Sprite.shootingMob;
        this.attackSpell = new ThrowKnife(this, 100, Game.player);
        this.health = 100;
        this.points = 10;
    }

    public void die() {
        removed = true;
        Game.player.addScore(points);
    }

    public void update() {
        attackSpell.use();

        if (health <= 0) {
            die();
        }
    }

    public void render(Screen screen) {
        screen.renderSprite((int) this.pos.x, (int) this.pos.y, this.sprite);
    }

}
