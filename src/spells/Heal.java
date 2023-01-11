package spells;

import entity.Mob;

public class Heal extends Spell {
    protected int power;

    public Heal(Mob mob, int cdValue) {
        super(mob, cdValue);
        this.power = 10;
    }

    @Override
    public void use() {
        if (!isOnCooldown()) {
            this.caster.setHealth(caster.getHealth() + power);

            // reste timer
            this.timer = this.cooldownValue;
            this.onCooldown = true;
        }
    }
}
