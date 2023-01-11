package main;

import graphics.Screen;
import graphics.Sprite;
import input.Mouse;

public class Menu {
    protected Mouse mouse;

    public Menu(Mouse mouse){
        this.mouse = mouse;
    }

    public void handleInput(){
        if (Mouse.getButton() == 1) {
            Game.currentState = Game.STATES.RUNNING;
        }
    }

    public void update(){
        handleInput();
    }

    public void render(Screen screen){
        screen.renderSprite(0, 0, new Sprite(screen.height * screen.width, 0xFFFFFF));
    }
}
