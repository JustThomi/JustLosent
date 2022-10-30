package level;


public class RandomLevel extends Level {

    // private static final Random RNG = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
        generateLevel();
    }

    // @Override
    // protected void generateLevel() {
    //     for (int y = 0; y < height; y++) {
    //         for (int x = 0; x < width; x++) {
    //             tiles[x + y * width] = RNG.nextInt(2);
    //         }
    //     }
    // }   

    @Override
    protected void time() {
    }

    @Override
    public void update() {
    }
}
