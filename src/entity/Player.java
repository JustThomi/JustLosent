package entity;

import graphics.Animator;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import level.Level;
import tools.Vector2;

public class Player extends Mob {
    protected int speed;
    protected Keyboard input;

    private int animationSpeed;
    protected Animator animatorBody;
    protected Animator animatorHead;

    public Player(int x, int y, Level level, int speed, Keyboard keyboard) {
        super(x, y, level);
        this.input = keyboard;
        this.speed = speed;
        this.direction = new Vector2();

        // hard coded so animation speed can match walking speed
        this.animationSpeed = 500;
        this.animatorBody = new Animator(
                new Sprite[] { Sprite.player, Sprite.player1 }, animationSpeed);
        this.animatorHead = new Animator(
                new Sprite[] { Sprite.playerHead, Sprite.playerHead1 }, animationSpeed);
    }

    public void handleInput() {
        this.direction.x = 0;
        this.direction.y = 0;

        if (input.w)
            this.direction.y--;
        if (input.a)
            this.direction.x--;
        if (input.s)
            this.direction.y++;
        if (input.d)
            this.direction.x++;
    }

    @Override
    public void update() {
        handleInput();
        move();
    }

    @Override
    public void move() {
        // this.direction.normalize();
        this.direction.multiply(speed);
        this.pos.add(direction);
    }

    public void render(Screen screen) {
        boolean flip;

        // flip sprite if we move left
        if (this.direction.x < 0)
            flip = true;
        else
            flip = false;

        // Animate sprite
        if (!this.direction.isNull()) {
            screen.renderPlayer((int) this.pos.x, (int) this.pos.y - 16, animatorHead.getCurrentSprite(), flip);
            screen.renderPlayer((int) this.pos.x, (int) this.pos.y, animatorBody.getCurrentSprite(), flip);

            animatorHead.handleAnimation();
            animatorBody.handleAnimation();

        } else {
            screen.renderPlayer((int) this.pos.x, (int) this.pos.y - 16, Sprite.playerHead, flip);
            screen.renderPlayer((int) this.pos.x, (int) this.pos.y, Sprite.player, flip);
        }
    }
}
