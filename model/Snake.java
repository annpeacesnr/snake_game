package model;

import java.awt.Graphics;
import java.util.ArrayList;

import view.GameBoard;

public class Snake extends GameElement {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public ArrayList<GameElement> composite = new ArrayList<>();
    private final int INIT_XLOC = GameBoard.CELL_SIZE * 7;
    private final int INIT_YLOC = GameBoard.CELL_SIZE * 3;
    private final int INIT_BODY_SIZE = 3;

    public Direction direction =  Direction.RIGHT;

    public void init() { //reset the starting conditions
        direction = Direction.RIGHT;
        composite.clear();
        super.x = INIT_XLOC;
        super.y = INIT_YLOC;
        composite.add(new SnakeHead(super.x, super.y));
        for (int i = 1; i <= INIT_BODY_SIZE; i++) {
            int x = INIT_XLOC - i * GameBoard.CELL_SIZE;
            int y = INIT_YLOC;
            composite.add(new SnakeBody(x, y));
        }

    }

    public Snake(int x, int y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

   

    @Override
    public void render(Graphics g2) {
        for (var b: composite) {
            b.render(g2);
        }
        
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }
    
}
