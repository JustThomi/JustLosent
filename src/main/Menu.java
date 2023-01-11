package main;

import javax.swing.JOptionPane;

import graphics.Screen;
import input.Mouse;

public class Menu {

    public Menu() {

    }

    public void handleInput(Screen screen) {
        int screenWidth = screen.getWidth() * 3;
        int screenHeight = screen.getHeight() * 3;

        if (Mouse.getButton() == 1) {
            // start button
            if (Mouse.getX() > screenWidth / 2 - 100 && Mouse.getX() < screenWidth / 2
                    && Mouse.getY() > screenHeight / 2 && Mouse.getY() < screenHeight / 2 + 50) {
                // get and set username
                String username = JOptionPane.showInputDialog("Enter a username:");
                Game.player.setUsername(username);

                Game.currentState = Game.STATES.RUNNING;
            }

            // score button
            if (Mouse.getX() > screenWidth / 2 - 100 && Mouse.getX() < screenWidth / 2
                    && Mouse.getY() > screenHeight / 2 + 50 && Mouse.getY() < screenHeight / 2 + 100) {
                Game.currentState = Game.STATES.SCORE;
            }
        }
    }

    public void update(Screen screen) {
        handleInput(screen);
    }

    public void render(Screen screen) {
    }
}
