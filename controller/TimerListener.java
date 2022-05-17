package controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Food;
import model.GameElement;
import model.Snake;
import model.SnakeBody;
import model.Snake.Event;
import view.GameBoard;

import java.awt.event.ActionEvent;

public class TimerListener implements ActionListener {

    private GameBoard gameBoard;

    public TimerListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    // game loop
    @Override
    public void actionPerformed(ActionEvent e) {
        for (var f: gameBoard.getCanvas().getFigures()) {
            f.move(); // move method to move each element accross screen 
        }
        if (!gameBoard.isGameOver()) // if game is not over 
        detectCollision();   // detect collisions

        gameBoard.getCanvas().repaint(); // redraw the whole screen
        }

        private void detectCollision() {
        var figures = gameBoard.getCanvas().getFigures();
        Snake snake = null;
        for (var f: figures) { // find snake on game board
            if (f instanceof Snake) {
                snake = (Snake) f;
                break;
            }
        }
        if (snake == null) return;

        // check if snake left game scene
        if (snake.x < 0 || snake.x >= GameBoard.WIDTH || snake.y < 0 || snake.y >= GameBoard.HEIGHT) {
            snake.notifyObservers(Event.LeftScene);
            gameBoard.setGameOver(true);
        }

        // snake self collision - head collides with body
        if (snake.selfCollision())
            snake.notifyObservers(Event.SelfCollision);
            gameBoard.setGameOver(true);


        // snake vs food
        var removeFoods = new ArrayList<GameElement>();
        for (var f: figures) {
            if (f instanceof Snake) continue;
            if (snake.collideWith(f)) {  // if snake colides with game element
            if (f instanceof Food) {
                removeFoods.add(f); // remove food
                snake.getComposite().add(new SnakeBody(-100, -100)); // increase snake body size
                snake.notifyObservers(Event.AteFood);
            }              
            }
        }

        if (removeFoods.size() > 0) {
            figures.removeAll(removeFoods);
            gameBoard.createFood(); // new food created if all removed or empty arraylist
        }

    }
}