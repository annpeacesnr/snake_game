package model;

//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public abstract class GameElement {

    public int x;
    public int y;
    public Color color;
    public boolean filled;

    public GameElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean collideWith(GameElement another) {
        if (this.x == another.x && this.y == another.y)
        return true;
        else
        return false;
    }
    
    public abstract void render(Graphics2D g2);
    public abstract void move();

    }
    

    

