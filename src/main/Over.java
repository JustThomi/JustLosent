package main;

import graphics.Screen;
import input.Mouse;

public class Over {

    public Over() {

    }

    public void handleInput(Screen screen) {
        int screenWidth = screen.getWidth() * 3;
        int screenHeight = screen.getHeight() * 3;

        if (Mouse.getButton() == 1) {
            if (Mouse.getX() > screenWidth / 2 - 100 && Mouse.getX() < screenWidth / 2
                    && Mouse.getY() > screenHeight / 2 - 100 && Mouse.getY() < screenHeight / 2 - 50) {
                Game.currentState = Game.STATES.MENU;
            }
        }
    }

    public void update(Screen screen) {
        handleInput(screen);
    }
}
