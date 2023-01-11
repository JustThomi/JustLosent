package entity;

import graphics.Animator;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;
import level.Level;
import main.Game;
import tools.Vector2;
import spells.*;

public class Player extends Mob {
    protected int speed;
    protected Keyboard input;
    protected int score;
    protected String username;
    protected int wave;

    private int animationSpeed;
    protected Animator animatorBody;
    protected Animator animatorHead;

    protected Spell attackSpell = new Skullrain(this, 30);
    protected Spell specialSpell = new Smash(this, 100);
    protected Spell healSpell = new Heal(this, 400);

    public Player(int x, int y, Level level, int speed, Keyboard keyboard) {
        super(x, y, level);
        this.input = keyboard;
        this.speed = speed;
        this.direction = new Vector2();
        this.health = 100;
        this.score = 0;
        this.wave = 0;

        // hard coded so animation speed can match walking speed
        this.animationSpeed = 500;
        this.animatorBody = new Animator(
                new Sprite[] { Sprite.player, Sprite.player1 }, animationSpeed);
        this.animatorHead = new Animator(
                new Sprite[] { Sprite.playerHead, Sprite.playerHead1 }, animationSpeed);
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int i) {
        this.wave = i;
    }

    public void setUsername(String s) {
        this.username = s;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPosition(int x, int y) {
        this.pos.x = x * 16;
        this.pos.y = y * 16;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return this.score;
    }

    public void resetPlayer() {
        this.health = 100;
        this.username = null;
        this.score = 0;
        this.wave = 0;
    }

    public void die() {
        resetPlayer();
        Spawner.reset();
        Game.currentState = Game.STATES.OVER;
    }

    public void handleInput() {
        this.direction.x = 0;
        this.direction.y = 0;

        // movement
        if (input.w)
            this.direction.y--;
        if (input.a)
            this.direction.x--;
        if (input.s)
            this.direction.y++;
        if (input.d)
            this.direction.x++;

        // actions
        if (Mouse.getButton() == 1) {
            attackSpell.use();
        }

        if (Mouse.getButton() == 2) {
            specialSpell.use();
        }

        if (Mouse.getButton() == 3) {
            healSpell.use();
        }
    }

    public boolean isColliding() {
        boolean colliding = false;

        for (int i = 0; i < 4; i++) {
            int xColl = (int) ((this.pos.x + this.direction.x) + i % 2 + 5) / 16;
            int yColl = (int) ((this.pos.y + this.direction.y) + i / 2 + 13) / 16;

            if (level.getTile(xColl, yColl).isSolid()) {
                colliding = true;
            }
        }
        return colliding;
    }

    @Override
    public void update() {
        handleInput();
        if (!isColliding()) {
            move();
        }

        if (this.health <= 0) {
            die();
        }

        // remove when lifespan is over
        shots.removeIf(e -> (e.isRemoved()));
    }

    public void move() {
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
