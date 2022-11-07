package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    protected int x, y;
    public Sprite sprite;

    // Ground tiles
    public static Tile voidTile = new Tile(Sprite.voidSprite);
    public static Tile ground = new Tile(Sprite.ground);
    public static Tile crackedGround = new Tile(Sprite.crackedGround);
    public static Tile chippedGround = new Tile(Sprite.chippedGround);
    public static Tile holeInGround = new Tile(Sprite.holeInGround);

    // wall tiles
    public static Tile wall = new Tile(Sprite.wall);
    public static Tile sideWall = new Tile(Sprite.sideWall);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solind() {
        return false;
    }
}
