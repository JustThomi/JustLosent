package graphics;

public class Animator {
    public Sprite[] frames;
    protected int current;
    private int animationClock;
    private int animationSpeed;

    public Animator(Sprite[] frames, int speed) {
        this.frames = frames;
        this.animationSpeed = speed;

        this.current = 0;
        this.animationClock = 0;
    }

    public void handleAnimation() {
        if (animationClock > 10000)
            animationClock = 0;
        else
            animationClock++;

        if (animationClock % animationSpeed == 0) {
            this.nextSprite();
        }
    }

    public void nextSprite() {
        if (current >= frames.length - 1)
            current = 0;
        else
            current++;
    }

    public Sprite getCurrentSprite() {
        return frames[current];
    }
}
