package spells;

import entity.Mob;

public class Spell {

    protected Mob caster;
    protected boolean onCooldown;
    protected int cooldownValue;
    protected int timer;

    public Spell(Mob caster, int cdValue) {
        this.caster = caster;
        this.cooldownValue = cdValue;
        this.onCooldown = false;
    }

    public void use() {
    }

    public void update() {
        cooldown();
    }

    public void cooldown() {
        if (isOnCooldown()) {
            timer--;

            if (timer <= 0) {
                onCooldown = false;
            }
        }
    }

    public boolean isOnCooldown() {
        return onCooldown;
    }
}
