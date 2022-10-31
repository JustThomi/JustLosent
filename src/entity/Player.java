package entity;

import graphics.Sprite;
import input.Keyboard;
import level.Level;
import tools.Vector2;

public class Player extends Mob {

    protected int speed;
    protected Keyboard input;

    public Player(int x, int y, Level level, Sprite sprite, int speed, Keyboard keyboard) {
        super(x, y, level, sprite);
        this.input = keyboard;
        this.speed = speed;
    }

    public void handleInput() {
        if (input.w)
            this.pos.y--;
        if (input.a)
            this.pos.x--;
        if (input.s)
            this.pos.y++;
        if (input.d)
            this.pos.x++;
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void move(Vector2 dir) {
        this.direction = dir;
        this.direction.normalize();
        this.direction.multiply(speed);

        if (!collision()) {
            this.pos.add(direction);
        }
    }

    public void render() {

    }
}
