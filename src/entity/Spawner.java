package entity;

import level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import graphics.Screen;

public class Spawner {
    protected static List<Mob> mobs = new ArrayList<Mob>();
    protected Level level;
    private static Random RNG = new Random();

    public Spawner(Level level) {
        this.level = level;
    }

    public static List<Mob> getMobs() {
        return mobs;
    }

    public void spawnWave() {
        int padding = 3 * 16;
        for (int i = 0; i < 5; i++) {
            spawnMob(RNG.nextInt(padding, level.width * 16 - padding),
                    RNG.nextInt(padding, level.height * 16 - padding));
        }
    }

    public void spawnMob(int x, int y) {
        Mob mob = new ShootingEnemy(x, y, level);
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
        } else {
            spawnWave();
        }

        // remove dead mobs
        mobs.removeIf(m -> (m.removed == true));
    }
}
