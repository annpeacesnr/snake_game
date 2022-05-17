package model.observerPattern;

import model.Snake;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import view.GameBoard;
import view.Text;

public class SnakeObserver implements Observer {

    private GameBoard gameBoard;

    public SnakeObserver(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void snakeAteFood() {
        int score = gameBoard.getScore();
        ++score;
        gameBoard.setScore(score);
        gameBoard.getScoreDisplay().setText("" + score);
        
    }

    @Override
    public void snakeAtePoison() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void snakeLeftScene() { // observer to check if snake left scene
        gameBoard.getCanvas().getFigures().add(new Text("Game Over - out of bounds!", 100, 200));
        Snake snake = gameBoard.getSnake();
        snake.setMoveStrategy(new SnakeMoveDeadStrategy(snake));
        snake.setRenderStrategy(new SnakeRenderDeadStrategy(snake));
    }

    @Override
    public void snakeSelfCollision() {
        // TODO Auto-generated method stub
        
    }
    
}
