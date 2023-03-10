package skullshooter.engine.init;

import skullshooter.engine.input.Input;
import skullshooter.engine.render.Game;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(Game game) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(game.getName());
        setMinimumSize(game.getMinimumSize());
        setMaximumSize(game.getMaximumSize());
        add(game);
        pack();

        Input input = new Input();
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}