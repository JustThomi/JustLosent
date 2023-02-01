package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import data.SaveLoad;
import graphics.Screen;

public class Score {

    private Game game;
    private Screen screen;
    private JButton backButton;
    protected JList<String> scores;
    protected DefaultListModel<String> listModel;

    public Score(Game game, Screen screen) {
        this.game = game;
        this.screen = screen;
        this.listModel = new DefaultListModel<>();
        this.scores = new JList<String>(listModel);
        initList();
        initButtons();
        hideButtons();
    }

    public void populateList() {
        String[] values = SaveLoad.getScores();
        listModel.clear();

        for (String s : values) {
            listModel.addElement(s);
        }
    }

    public JButton getButton() {
        return backButton;
    }

    public void showList() {
        this.scores.setVisible(true);
    }

    public void hideList() {
        this.scores.setVisible(false);
    }

    public void hideButtons() {
        backButton.setVisible(false);
    }

    public void showButtons() {
        backButton.setVisible(true);
    }

    public void initList() {
        this.scores.setBounds((screen.width * 3) / 2 - 250, 10, 500, 500);
        this.scores.setForeground(new Color(255, 255, 255));
        this.scores.setBackground(new Color(72, 59, 58));
        this.scores.setFont(new Font("Monocraft", Font.BOLD, 32));

        hideList();
        game.frame.add(this.scores);
    }

    public void initButtons() {
        this.backButton = new JButton("Back");
        this.backButton.setBounds((screen.width * 3) - 110, 10, 100, 30);
        this.backButton.setFont(new Font("Monocraft", Font.BOLD, 12));
        this.backButton.setForeground(new Color(255, 255, 255));
        this.backButton.setBackground(new Color(24, 24, 24));
        this.backButton.setFocusPainted(false);

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.currentState = Game.STATES.MENU;
                hideList();
                hideButtons();
            }
        });

        game.frame.add(backButton);
    }

}
