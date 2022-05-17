package view;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ButtonClickListener;
import controller.KeyController;
import controller.TimerListener;
import model.Snake;

import java.awt.BorderLayout;
import java.awt.Color;

public class GameBoard {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int FPS = 4; //frames per second snake pace
    public static final int DELAY = 1000 / FPS; //milliseconds
    public static final int CELL_SIZE = 20;

    private JFrame window;
    private MyCanvas canvas;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton exitButton = new JButton("Exit");
    private JLabel scoreDisplay = new JLabel();
    private Timer timer;

    private Snake snake = new Snake(0, 0); // create snake object

    public GameBoard(JFrame window) {
        this.window = window;
    }

    public void init() {
        Container cp = window.getContentPane();

        canvas = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);

        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Score: ");
        northPanel.add(label);
        northPanel.add(scoreDisplay);
        cp.add(BorderLayout.NORTH, northPanel);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(stopButton);
        southPanel.add(exitButton);
        cp.add(BorderLayout.SOUTH, southPanel);

        Text t1 = new Text("Click <start> to play", 100, 100);
        t1.color = Color.YELLOW;
        canvas.getFigures().add(t1);

        ButtonClickListener buttonListener = new ButtonClickListener(this);
        startButton.addActionListener(buttonListener);

        KeyController keyController = new KeyController(this);
        canvas.addKeyListener(keyController);
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);

        //disable focusable in other component otherwise focus is lost on click of buttons 
        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        exitButton.setFocusable(false);
        label.setFocusable(false);
        scoreDisplay.setFocusable(false);
        //safety component for every component apart from game canvas

        Timer timer = new Timer(DELAY, new TimerListener(this));
        timer.start();
    }


    public MyCanvas getCanvas() {
        return canvas;
    }

    public Snake getSnake() {
        return snake;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
    
}
