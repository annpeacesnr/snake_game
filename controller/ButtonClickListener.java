package controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.GameBoard;

public class ButtonClickListener implements ActionListener {


    private GameBoard gameBoard;

    public ButtonClickListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameBoard.getStartButton()) {
            gameBoard.getCanvas().getFigures().clear();
            gameBoard.getSnake().init();
            gameBoard.getCanvas().getFigures().add(gameBoard.getSnake());
            gameBoard.createFood(); 
            gameBoard.setScore(0);
        }

    }
    
}
