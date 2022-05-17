package model.observerPattern;

import view.GameBoard;

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
    public void snakeLeftScene() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void snakeSelfCollision() {
        // TODO Auto-generated method stub
        
    }
    
}
