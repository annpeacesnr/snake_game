package view;

import java.awt.Container;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ButtonClickListener;
import controller.KeyController;
import controller.TimerListener;
import model.Food;
import model.Snake;
import model.observerPattern.SnakeObserver;

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
    private int score = 0; // instance member explicitly defined
    private Timer timer;
    private boolean gameOver;

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
        scoreDisplay.setText("" + score);
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

        SnakeObserver observer = new SnakeObserver(this); // snake observer 
        snake.addSnakeListener(observer);

        Timer timer = new Timer(DELAY, new TimerListener(this));
        timer.start();
    }

    public void createFood() {    //generate location randomly according to grid
        Random random = new Random();
        int xloc, yloc;
        do {
            xloc = random.nextInt(GameBoard.WIDTH / GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
            yloc = random.nextInt(GameBoard.HEIGHT / GameBoard.CELL_SIZE) * GameBoard.CELL_SIZE;
        } while (xloc == snake.x && yloc == snake.y);

        Food food = new Food(xloc, yloc, Color.PINK); //avoiding the snake head and body
        canvas.getFigures().add(food);
    
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JLabel getScoreDisplay() {
        return scoreDisplay;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {   // getter for boolean type - check if game is over to prevent looping
        return gameOver;
    }
    
}
