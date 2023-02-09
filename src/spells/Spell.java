package spells;

import entity.Mob;

public class Spell {

    protected Mob caster;
    protected boolean onCooldown;
    protected int cooldownValue;
    protected int timer;

    /**
     * @param caster
     * @param cdValue
     */
    public Spell(Mob caster, int cdValue) {
        this.caster = caster;
        this.cooldownValue = cdValue;
        this.onCooldown = false;
    }

    /**
     * Prototip for using an ability
     */
    public void use() {
    }

    /**
     * Updating ability cooldown
     */
    public void update() {
        cooldown();
    }

    /**
     * Starting and computing cooldown
     */
    public void cooldown() {
        if (isOnCooldown()) {
            timer--;

            if (timer <= 0) {
                onCooldown = false;
            }
        }
    }

    /**
     * @return cooldown time left
     */
    public boolean isOnCooldown() {
        return onCooldown;
    }
}
