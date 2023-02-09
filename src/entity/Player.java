package entity;

import data.SaveLoad;
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

    /**
     * @param x
     * @param y
     * @param level
     * @param speed
     * @param keyboard
     */
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

    /**
     * @return current wave
     */
    public int getWave() {
        return wave;
    }

    /**
     * Sets wave number
     * 
     * @param i
     */
    public void setWave(int i) {
        this.wave = i;
    }

    /**
     * Sets player user name
     * 
     * @param s
     */
    public void setUsername(String s) {
        this.username = s;
    }

    /**
     * @return player username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set plaer position
     * 
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.pos.x = x * 16;
        this.pos.y = y * 16;
    }

    /**
     * Add point to score counter
     * 
     * @param points
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * @return current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Reset player health username score and wave
     */
    public void resetPlayer() {
        this.health = 100;
        this.username = null;
        this.score = 0;
        this.wave = 0;
    }

    /**
     * kills player to save data and reset
     */
    public void die() {
        try {
            SaveLoad.writeData(username, score);
        } catch (Exception e) {

        }

        resetPlayer();
        Spawner.reset();
        Game.currentState = Game.STATES.OVER;
    }

    /**
     * Handles keyboard and mouse input for player actions
     */
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

    /**
     * @return true if collides
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#update()
     */
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

        // update cooldown
        attackSpell.update();
        healSpell.update();
        specialSpell.update();
    }

    /**
     * Move player
     */
    public void move() {
        this.direction.multiply(speed);
        this.pos.add(direction);
    }

    /*
     * (non-Javadoc)
     * 
     * @see entity.Entity#render(graphics.Screen)
     */
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
