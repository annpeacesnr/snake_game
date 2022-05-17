package controller;

import java.awt.event.ActionListener;

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
        detectCollision();   // detect collisions
        gameBoard.getCanvas().repaint(); // redraw the whole screen
        }

        private void detectCollision() {

    }
}