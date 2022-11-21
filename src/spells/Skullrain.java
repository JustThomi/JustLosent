package spells;

import entity.Player;
import entity.Projectile;
import input.Mouse;
import main.Game;

public class Skullrain extends Spell {
    protected Player player;

    public Skullrain(Player player) {
        this.player = player;
    }

    @Override
    public void use() {
        // get direction
        int dx = Mouse.getX() - Game.getWindowWidth() / 2;
        int dy = Mouse.getY() - Game.getWindowHeight() / 2;
        double dir = Math.atan2(dy, dx);

        // create projectile
        Projectile p = new Projectile((int) player.pos.x, (int) player.pos.y, player.level, dir);
        Player.shots.add(p);
        player.level.addEntity(p);
    }
}
