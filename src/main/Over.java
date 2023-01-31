package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import graphics.Screen;

public class Over {
    private Game game;
    private Screen screen;
    private JButton backButton;

    public Over(Game game, Screen screen) {
        this.game = game;
        this.screen = screen;
        initButtons();
        hideButtons();
    }
    
    public JButton getButton(){
        return backButton;
    }

    public void hideButtons(){
        backButton.setVisible(false);
    }

    public void showButtons(){
        backButton.setVisible(true);
    }

    public void initButtons(){
        this.backButton = new JButton("Back");
        this.backButton.setBounds((screen.width * 3) / 2 - 100, (screen.height * 3) / 2, 200, 30);
        this.backButton.setFont(new Font("Monocraft", Font.BOLD, 12));
        this.backButton.setForeground(new Color(255, 255, 255));
        this.backButton.setBackground(new Color(24, 24, 24));
        this.backButton.setFocusPainted(false);

        this.backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.currentState = Game.STATES.MENU;
                hideButtons();
            }
        });

        game.frame.add(backButton);
    }
}
