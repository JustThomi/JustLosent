package spells;

import entity.Mob;

public class Heal extends Spell {
    protected int power;

    /**
     * @param mob
     * @param cdValue
     */
    public Heal(Mob mob, int cdValue) {
        super(mob, cdValue);
        this.power = 10;
    }

    /*
     * Use ability
     * 
     * @see spells.Spell#use()
     */
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
