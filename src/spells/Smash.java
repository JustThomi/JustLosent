package spells;

import entity.Mob;
import entity.PlayerProjectile;

public class Smash extends Spell {

    public Smash(Mob caster, int cdValue) {
        super(caster, cdValue);
    }

    public void spawnProjectile(double dir) {
        PlayerProjectile p = new PlayerProjectile((int) caster.pos.x, (int) caster.pos.y, caster.level, dir);
        caster.shots.add(p);
        caster.level.addEntity(p);
    }

    @Override
    public void use() {
        // testing some ideas
        if (!isOnCooldown()) {
            spawnProjectile(0);
            spawnProjectile(45);
            spawnProjectile(90);
            spawnProjectile(135);
            spawnProjectile(180);
            spawnProjectile(225);

            // reset cooldown
            this.timer = this.cooldownValue;
            this.onCooldown = true;
        }

        this.cooldown();
    }
}
