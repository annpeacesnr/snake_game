package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.observerPattern.Observer;
import model.observerPattern.Subject;
import model.strategyPattern.SnakeMoveAliveStrategy;
import model.strategyPattern.SnakeMoveDeadStrategy;
import model.strategyPattern.SnakeMoveStrategy;
import model.strategyPattern.SnakeRenderAliveStrategy;
import model.strategyPattern.SnakeRenderDeadStrategy;
import model.strategyPattern.SnakeRenderStrategy;
import view.GameBoard;

public class Snake extends GameElement implements Subject {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public enum Event {
        AteFood, AtePoison, LeftScene, SelfCollision
    }

    public ArrayList<GameElement> composite = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();
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

    public void setRenderStrategy(SnakeRenderStrategy renderStrategy) {
        this.renderStrategy = renderStrategy;
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
        this.moveStrategy.moveAlgorithm(); //move strategy
        
    }

    @Override
    public void addSnakeListener(Observer o) {
        observers.add(o);
        
    }

    @Override
    public void removeSnakeListener(Observer o) {
        observers.remove(o);
        
    }

    @Override
    public void notifyObservers(Event event) {
        switch (event) {
            case AteFood:
                for (var o: observers) {
                    o.snakeAteFood();
                }
                break;
            case AtePoison:
                break;
            case LeftScene:
            for (var o: observers) {
                o.snakeLeftScene();
            }
                break;
            case SelfCollision:
            for (var o: observers) {
                o.snakeSelfCollision();
            }
                break;
        }
        
    }

    public boolean selfCollision() { // checks whether head collides with body
        GameElement head = composite.get(0);
        for (int i = 1; i < composite.size(); i++) {
            var body = composite.get(i);
            if (head.collideWith(body)) return true;

    }
    return false;
}
}
