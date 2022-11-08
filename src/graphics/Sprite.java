package graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    // Env sprites
    public static Sprite voidSprite = new Sprite(16, 0, 0, SpriteSheet.groundSheet);
    public static Sprite ground = new Sprite(16, 1, 4, SpriteSheet.groundSheet);
    public static Sprite crackedGround = new Sprite(16, 2, 4, SpriteSheet.groundSheet);
    public static Sprite chippedGround = new Sprite(16, 3, 4, SpriteSheet.groundSheet);
    public static Sprite holeInGround = new Sprite(16, 1, 5, SpriteSheet.groundSheet);

    // Wall sprites
    public static Sprite wall = new Sprite(16, 2, 1, SpriteSheet.groundSheet);
    public static Sprite sideWall = new Sprite(16, 1, 9, SpriteSheet.groundSheet);

    // Player sprites
    public static Sprite player = new Sprite(16, 8, 5, SpriteSheet.groundSheet);
    public static Sprite playerHead = new Sprite(16, 8, 4, SpriteSheet.groundSheet);

    public static Sprite player1 = new Sprite(16, 9, 5, SpriteSheet.groundSheet);
    public static Sprite playerHead1 = new Sprite(16, 9, 4, SpriteSheet.groundSheet);

    public static Sprite player2 = new Sprite(16, 10, 5, SpriteSheet.groundSheet);
    public static Sprite playerHead2 = new Sprite(16, 10, 4, SpriteSheet.groundSheet);

    public static Sprite player3 = new Sprite(16, 11, 5, SpriteSheet.groundSheet);
    public static Sprite playerHead3 = new Sprite(16, 11, 4, SpriteSheet.groundSheet);

    public Sprite(
            int size,
            int x,
            int y, SpriteSheet sheet) {
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
