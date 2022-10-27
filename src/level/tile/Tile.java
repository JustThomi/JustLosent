package level.tile;

import graphics.Screen;
import graphics.Sprite;

public abstract class Tile {
    protected int x, y;
    public Sprite sprite;

    public static Tile ground = new GroundTile(Sprite.ground);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solind() {
        return false;
    }
}
