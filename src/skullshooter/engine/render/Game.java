package skullshooter.engine.render;

import skullshooter.engine.init.GameWindow;
import skullshooter.engine.physics.PhysicsManager;
import skullshooter.engine.scenes.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private final Color bgColor;

    public static final String TITLE = "SkullShooter";
    public static final int WIDTH = 1280, HEIGHT = 720;

    public Game() {
        bgColor = new Color(0);

        setName(TITLE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        new GameWindow(this);

        Timer timer = new Timer(1000 / 120, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        SceneManager.render((Graphics2D) g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SceneManager.update();
        PhysicsManager.update();

        repaint();
    }
}