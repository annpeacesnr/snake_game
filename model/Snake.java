package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.strategyPattern.SnakeMoveAliveStrategy;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeMoveStrategy;
import model.strategyPattern.SnakeRenderAliveStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import model.strategyPattern.SnakeRenderStrategy;
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

    private SnakeMoveStrategy moveStrategy;
    private SnakeRenderStrategy renderStrategy;

    public void init() { //reset the starting conditions
        direction = Direction.RIGHT; //starting direction of snake
        composite.clear();
        super.x = INIT_XLOC;
        super.y = INIT_YLOC;
        var head = new SnakeHead(super.x, super.y);
        head.color = Color.YELLOW;
        composite.add(head);
        for (int i = 1; i <= INIT_BODY_SIZE; i++) {
            int x = INIT_XLOC - i * GameBoard.CELL_SIZE;
            int y = INIT_YLOC;
            var body = new SnakeBody(x, y);
            body.color = Color.white;
            composite.add(body);
        }

        //snake strategy or state 
        moveStrategy = new SnakeMoveAliveStrategy(this);
        //moveStrategy = new SnakeMoveDeadStrategy(this);
        renderStrategy = new SnakeRenderAliveStrategy(this);
        //renderStrategy = new SnakeRenderDeadStrategy(this);

    }

    public Snake(int x, int y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

   
    public void setMoveStrategy(SnakeMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public ArrayList<GameElement> getComposite() {
        return composite;
    }
    

    @Override
    public void render(Graphics g2) {
        this.renderStrategy.renderAlgorithm(g2);
        }
        
    

    @Override
    public void move() {
        this.moveStrategy.moveAlgorithm(); //alive strategy
        
    }
}
