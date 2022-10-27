package level;

import java.util.Random;

import graphics.Screen;

public class RandomLevel extends Level {

    private final Random RNG = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = RNG.nextInt(4);
            }
        }
    }

    @Override
    protected void time() {
    }

    @Override
    public void render(int xScroll, int yScroll, Screen screen) {
    }

    @Override
    public void update() {
    }
}
