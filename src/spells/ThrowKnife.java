package spells;

import entity.Mob;
import entity.Player;
import entity.Projectile;

public class ThrowKnife extends Spell {
    protected Player target;

    /**
     * @param caster
     * @param cdValue
     * @param player
     */
    public ThrowKnife(Mob caster, int cdValue, Player player) {
        super(caster, cdValue);
        this.target = player;
        this.onCooldown = true;
    }

    /*
     * Use ability
     * 
     * @see spells.Spell#use()
     */
    @Override
    public void use() {
        if (!isOnCooldown()) {
            // get direction
            int dx = (int) target.pos.x - (int) caster.pos.x;
            int dy = (int) target.pos.y - (int) caster.pos.y;
            double dir = Math.atan2(dy, dx);

            // create projectile
            Projectile p = new Projectile((int) caster.pos.x, (int) caster.pos.y, caster.level, dir);
            caster.shots.add(p);
            caster.level.addEntity(p);

            // reset cooldown
            this.timer = this.cooldownValue;
            this.onCooldown = true;
        }
    }
}
