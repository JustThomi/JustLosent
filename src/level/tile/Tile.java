package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    protected int x, y;
    public Sprite sprite;
    private boolean solid;

    // Ground tiles
    public static Tile voidTile = new Tile(Sprite.voidSprite, false);
    public static Tile ground = new Tile(Sprite.ground, false);
    public static Tile crackedGround = new Tile(Sprite.crackedGround, false);
    public static Tile chippedGround = new Tile(Sprite.chippedGround, false);
    public static Tile holeInGround = new Tile(Sprite.holeInGround, false);

    // wall tiles
    public static Tile wall = new Tile(Sprite.wall, true);
    public static Tile sideWall = new Tile(Sprite.sideWall, true);

    // Entity tiles
    public static Tile bullet = new Tile(Sprite.bullet, false);
    public static Tile knife = new Tile(Sprite.knife, false);

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean isSolid() {
        return solid;
    }
}
