package spells;

import entity.Mob;
import entity.Player;
import entity.Projectile;
import input.Mouse;
import main.Game;

public class Skullrain extends Spell {

    public Skullrain(Mob caster) {
        super(caster);
    }

    @Override
    public void use() {
        // get direction
        int dx = Mouse.getX() - Game.getWindowWidth() / 2;
        int dy = Mouse.getY() - Game.getWindowHeight() / 2;
        double dir = Math.atan2(dy, dx);

        // create projectile
        Projectile p = new Projectile((int) caster.pos.x, (int) caster.pos.y, caster.level, dir);
        Player.shots.add(p);
        caster.level.addEntity(p);
    }
}
