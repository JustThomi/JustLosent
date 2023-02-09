package entity;

import level.Level;
import main.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import graphics.Screen;

public class Spawner {
    protected static List<Mob> mobs = new ArrayList<Mob>();
    protected Level level;
    private static Random RNG = new Random();

    /**
     * Enemy manager
     * 
     * @param level
     */
    public Spawner(Level level) {
        this.level = level;
    }

    /**
     * @return list of all mobs
     */
    public static List<Mob> getMobs() {
        return mobs;
    }

    /**
     * Resets mob array
     */
    public static void reset() {
        mobs = new ArrayList<Mob>();
    }

    /**
     * Spawn 5 enemies when new waves starts
     */
    public void spawnWave() {
        int padding = 3 * 16;
        Game.player.setWave(Game.player.getWave() + 1);

        for (int i = 0; i < 5; i++) {
            spawnMob(RNG.nextInt(padding, level.width * 16 - padding),
                    RNG.nextInt(padding, level.height * 16 - padding));
        }
    }

    /**
     * Spawn mob
     * 
     * @param x
     * @param y
     */
    public void spawnMob(int x, int y) {
        Mob mob = new ShootingEnemy(x, y, level);
        addMob(mob);
    }

    /**
     * Add mob to mobs list
     * 
     * @param mob
     */
    public void addMob(Mob mob) {
        mobs.add(mob);
    }

    /**
     * Renders all mobs to screen
     * 
     * @param screen
     */
    public void render(Screen screen) {
        // render mobs
        if (mobs.size() > 0) {
            for (Mob mob : mobs) {
                mob.render(screen);
            }
        }
    }

    /**
     * Updates all mobs from list. Remove dead enemies from list
     */
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
