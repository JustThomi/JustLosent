package spells;

import entity.Mob;

public class Spell {

    Mob caster;

    public Spell(Mob caster) {
        this.caster = caster;
    }

    public void use() {

    }

    public boolean isOnCooldown() {
        return false;
    }
}
