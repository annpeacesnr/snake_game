package model;

import java.awt.Graphics;

import view.GameBoard;

public class SnakeBody extends GameElement {

    public SnakeBody(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g2) { // rendering snakes body
        g2.setColor(super.color);
        if (super.filled)
        g2.fillRect(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
    else
        g2.drawRect(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
    }
        

    @Override
    public void move() {
        
    }
    
}
