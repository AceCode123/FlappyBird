package com.anthony.flappybird;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Anthony on 7/26/2016.
 */
public class RenderPane extends JPanel {

    public static final Color BACKGROUND = new Color(8904186);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        g.setColor(Color.YELLOW);
        FlappyBird  bird = FlappyBird.game;
        g.fillRect(bird.pipe.x, bird.pipe.y, 50, bird.frame.getHeight()+50);
        g.setColor(Color.GREEN);
        g.fillRect(bird.bird.x, bird.bird.y, 25, 25);

    }
}
