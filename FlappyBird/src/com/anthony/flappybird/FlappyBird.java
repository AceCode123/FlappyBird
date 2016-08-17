package com.anthony.flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anthony on 7/26/2016.
 */
public class FlappyBird implements ActionListener, KeyListener {

    public static final int WIDTH = 850;
    public static final int HEIGHT = 705;
    public JFrame frame;
    public Dimension dim;
    public static FlappyBird game;
    public Timer timer = new Timer(20, this);
    public int ticks = 0, score = 0;
    public Point bird = new Point(0, 0);
    public Point pipe;
    public boolean paused = false, gameover = false;
    public RenderPane pane;
    public int level = 1;
    public int scale = 10;


    public FlappyBird() {
        frame = new JFrame();
        frame.setTitle("FlappyBird | Score: " + score + " | " + "Level: " + level);
        frame.add(pane = new RenderPane());
        frame.pack();
        frame.addKeyListener(this);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        startGame();
        timer.start();
    }

    public static void main(String[] args) {
        game = new FlappyBird();

    }

    public void startGame() {
        ticks = 0;
        paused = false;
        gameover = false;
        score = 10;
        level = 1;
        Random r = new Random();
        pipe = new Point(randomIntFromInterval(250, 700), randomIntFromInterval(75, 250));
        bird = new Point(25, 150);
    }

    public int randomIntFromInterval(int min, int max)
    {
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    public void nextLevel() {
        score+=10;
        level++;
        frame.setTitle("FlappyBird | Score: " + score + " | " + "Level: " + level);
        Random r = new Random();
        pipe = new Point(randomIntFromInterval(250, 700), randomIntFromInterval(75, 250));
        bird = new Point(0, 0);
    }

    public void gameOver() {
        gameover = true;
        frame.setTitle("GameOver! Score: " + score + " | Level: " + level);
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pane.repaint();
        ticks++;
        if(gameover == false) {
            if (ticks % 2 == 0) {
                if(bird.y + 1 <= 0) {
                    gameOver();
                }
                bird = new Point(bird.x + 5, bird.y + 3);
                if (bird.x > 700) {
                    nextLevel();
                }
                if (bird.x == pipe.x || bird.x + 1 == pipe.x || bird.x + 2 == pipe.x || bird.x + 3 == pipe.x) {
                   if(bird.y >= pipe.y) {
                       System.out.println("Y Matched");
                    gameOver();
                    }
                }
            }
        }




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird = new Point(bird.x, bird.y-15);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
