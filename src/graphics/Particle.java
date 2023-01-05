package graphics;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import level.Level;

public class Particle extends Entity {

    protected List<Particle> particles = new ArrayList<Particle>();
    protected int life;
    protected Sprite sprite;

    protected double xGauss, yGauss;

    public Particle(int x, int y, Level level, int life) {
        super(x, y, level);
        this.life = life;
        this.sprite = Sprite.basicParticle;
        this.xGauss = RNG.nextGaussian();
        this.yGauss = RNG.nextGaussian();
    }

    public Particle(int x, int y, Level level, int life, int amount) {
        this(x, y, level, life);
        particles.add(this);
        for (int i = 0; i < amount - 1; i++) {
            particles.add(new Particle(x, y, level, life));
        }
    }

    public void update() {
        this.pos.x += xGauss;
        this.pos.y += yGauss;
    }

    public void render(Screen screen) {
        screen.renderSprite((int) this.pos.x, (int) this.pos.y, this.sprite);
    }
}
