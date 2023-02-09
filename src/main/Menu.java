package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.SaveLoad;
import graphics.Screen;

public class Menu {
    private Game game;
    private Screen screen;
    private JButton startButton;
    private JButton scoreButton;

    /**
     * @param game
     * @param screen
     */
    public Menu(Game game, Screen screen) {
        this.game = game;
        this.screen = screen;
        initButtons();
    }

    /**
     * @return startButton
     */
    public JButton getButton() {
        return startButton;
    }

    /**
     * Hides buttons
     */
    public void hideButtons() {
        startButton.setVisible(false);
        scoreButton.setVisible(false);
    }

    /**
     * Shows Buttons
     */
    public void showButtons() {
        startButton.setVisible(true);
        scoreButton.setVisible(true);

    }

    /**
     * Initiate button look and feel
     */
    public void initButtons() {
        this.startButton = new JButton("Start");
        this.startButton.setBounds((screen.width * 3) / 2 - 100, (screen.height * 3) / 2, 200, 30);
        this.startButton.setFont(new Font("Monocraft", Font.BOLD, 12));
        this.startButton.setForeground(new Color(255, 255, 255));
        this.startButton.setBackground(new Color(24, 24, 24));
        this.startButton.setFocusPainted(false);

        this.scoreButton = new JButton("Score");
        this.scoreButton.setBounds((screen.width * 3) / 2 - 100, (screen.height * 3) / 2 + 50, 200, 30);
        this.scoreButton.setFont(new Font("Monocraft", Font.BOLD, 12));
        this.scoreButton.setForeground(new Color(255, 255, 255));
        this.scoreButton.setBackground(new Color(24, 24, 24));
        this.scoreButton.setFocusPainted(false);

        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter a username:");
                if (username == null || username == "") {
                    username = "USER";
                }
                Game.player.setUsername(username);
                Game.currentState = Game.STATES.RUNNING;
                hideButtons();
            }
        });

        this.scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveLoad.readData();
                } catch (Exception exeption) {
                    System.out.println(exeption);
                }

                Game.currentState = Game.STATES.SCORE;
                hideButtons();
            }
        });

        game.frame.add(startButton);
        game.frame.add(scoreButton);
    }
}
