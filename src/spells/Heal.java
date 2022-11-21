package spells;

import entity.Mob;

public class Heal extends Spell {

    Mob mob;
    protected int power = 10;

    public Heal(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void use() {
        this.mob.setHealth(mob.getHealth() + power);
    }
}
