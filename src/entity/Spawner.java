package entity;

import level.Level;
import java.util.ArrayList;
import java.util.List;

import graphics.Screen;

public class Spawner {
    protected List<Mob> mobs = new ArrayList<Mob>();
    protected Level level;

    public Spawner(Level level) {
        this.level = level;
    }

    public void spawnMob() {
        Mob mob = new ShootingEnemy(50, 100, level);
        addMob(mob);
    }

    public void addMob(Mob mob) {
        mobs.add(mob);
    }

    public void render(Screen screen) {
        // render mobs
        if (mobs.size() > 0) {
            for (Mob mob : mobs) {
                mob.render(screen);
            }
        }
    }

    public void update() {
        // update mobs
        if (mobs.size() > 0) {
            for (Mob mob : mobs) {
                mob.update();
            }
        }

        // remove dead mobs
        mobs.removeIf(m -> (m.getHealth() <= 0));
    }
}
