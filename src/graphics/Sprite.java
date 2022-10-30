package graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    // Sprite
    public static Sprite ground = new Sprite(16, 1, 4, SpriteSheet.groundSheet);
    public static Sprite crackedGround = new Sprite(16, 2, 4, SpriteSheet.groundSheet);
    public static Sprite chippedGround = new Sprite(16, 3, 4, SpriteSheet.groundSheet);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[size * size];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}