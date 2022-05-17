package model;

import java.awt.Graphics;

import javax.lang.model.util.ElementScanner14;

import view.GameBoard;

public class SnakeHead extends GameElement {

    public SnakeHead(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g2) { // drawing the snakes head
        g2.setColor(super.color);
        if (super.filled)
        g2.fillOval(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
    else
        g2.drawOval(x, y, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }
    
}
