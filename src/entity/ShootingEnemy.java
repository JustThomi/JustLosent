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

    public ShootingEnemy(int x, int y, Level level) {
        super(x, y, level);
        this.health = 100;
        this.sprite = Sprite.shootingMob;
        this.attackSpell = new ThrowKnife(this, 50, Game.player);
    }

    public void update() {
        attackSpell.use();
    }

    public void render(Screen screen) {
        screen.renderSprite((int) this.pos.x, (int) this.pos.y, this.sprite);
    }

}
