package main;

import graphics.Screen;
import input.Mouse;

public class Score {
    public Score() {

    }

    public void handleInput(Screen screen) {
        int screenWidth = screen.getWidth() * 3;

        if (Mouse.getButton() == 1) {
            if (Mouse.getButton() == 1) {
                if (Mouse.getX() > screenWidth - 100 && Mouse.getY() < 50) {
                    Game.currentState = Game.STATES.MENU;
                }
            }
        }
    }

    public void update(Screen screen) {
        handleInput(screen);
    }

    public void render(Screen screen) {
    }
}
