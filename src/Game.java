import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import graphics.Screen;
import input.Keyboard;
import level.Level;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Keyboard keyboard;
    private int xOffset = 0, yOffset = 0;

    public static Level level;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        frame = new JFrame();
        screen = new Screen(width, height);
        level = new Level(64, 64);
        keyboard = new Keyboard();
        addKeyListener(keyboard);
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

    public void handleInput() {
        if (keyboard.w)
            yOffset--;
        if (keyboard.a)
            xOffset--;
        if (keyboard.s)
            yOffset++;
        if (keyboard.d)
            xOffset++;
    }

    public void update() {
        keyboard.update();
        handleInput();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        // clear than render screen
        screen.clear();
        level.render(xOffset, yOffset, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        // draw image
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
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