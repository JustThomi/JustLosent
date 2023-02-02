package main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import entity.Player;
import entity.Spawner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;
import level.Level;
import level.MainLevel;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    public JFrame frame;
    private boolean running = false;

    private Screen screen;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private BufferedImage menuImage;
    private BufferedImage scoreImage;
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Keyboard keyboard;
    public static Player player;

    private Spawner spawner;

    public static Level level;
    protected Menu menu;
    protected Score scoreScene;
    protected Over overScene;

    public enum STATES {
        MENU,
        RUNNING,
        OVER,
        SCORE,
    }

    public static STATES currentState;

    public Game() throws IOException {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        currentState = STATES.MENU;
        frame = new JFrame();
        screen = new Screen(width, height);
        level = new MainLevel("/assets/level.png");
        keyboard = new Keyboard();
        player = new Player(0, 0, level, 1, keyboard);
        spawner = new Spawner(level);

        // Spawn in the center of the map
        player.setPosition(level.width / 2, level.height / 2);
        addKeyListener(keyboard);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        menu = new Menu(this, screen);
        menuImage = ImageIO.read(new File("src/assets/menu.png"));

        scoreScene = new Score(this, screen);
        scoreImage = ImageIO.read(new File("src/assets/score.png"));

        overScene = new Over(this, screen);
    }

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Timer
        int fps = 144;
        long lastTime = System.nanoTime();
        final double ns = 1_000_000_000.0 / fps;
        double delta = 0;

        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }
            render();
        }
        stop();
    }

    public void update() {
        switch (currentState) {
            case MENU:
                if (!menu.getButton().isShowing()) {
                    menu.showButtons();
                }
                break;

            case RUNNING:
                keyboard.update();
                player.update();
                level.update();
                spawner.update();

                if (keyboard.esc) {
                    player.resetPlayer();
                    Spawner.reset();
                    Game.currentState = Game.STATES.MENU;
                    menu.showButtons();
                }
                break;

            case OVER:
                if (!overScene.getButton().isShowing()) {
                    overScene.showButtons();
                }
                break;

            case SCORE:
                if (!scoreScene.getButton().isShowing()) {
                    scoreScene.showButtons();
                    scoreScene.showList();
                    scoreScene.populateList();
                }
                break;
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        // clear than render screen
        screen.clear();

        // center player
        int xScroll = (int) player.pos.x - (width / 2) + Sprite.player.SIZE;
        int yScroll = (int) player.pos.y - (height / 2) + Sprite.player.SIZE / 2;

        Graphics g = bs.getDrawGraphics();

        switch (currentState) {
            case RUNNING:
                // render stuff
                level.render(xScroll, yScroll, screen);
                player.render(screen);
                spawner.render(screen);

                for (int i = 0; i < pixels.length; i++) {
                    pixels[i] = screen.pixels[i];
                }

                // draw image
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                // set color and fonts
                g.setColor(Color.WHITE);
                g.setFont(new Font("Monocraft", 0, 32));
                // draw stings
                g.drawString("HP: " + player.getHealth(), 20, 50);
                g.drawString("SCORE: " + player.getScore(), 20, 100);
                g.drawString(Integer.toString(player.getWave()), getWidth() / 2 - 50, 50);
                g.drawString(player.getUsername(), 20, 150);

                break;

            case MENU:
                g.drawImage(menuImage, 0, 0, getWidth(), getHeight(), frame);
                break;

            case SCORE:
                g.drawImage(scoreImage, 0, 0, getWidth(), getHeight(), frame);
                break;

            case OVER:
                g.drawImage(scoreImage, 0, 0, getWidth(), getHeight(), frame);
                // set color and fonts
                g.setColor(Color.WHITE);
                g.setFont(new Font("Monocraft", 0, 64));
                g.drawString("GAME OVER", getWidth() / 2 - 200, getHeight() / 2 - 100);

                break;
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Game");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}