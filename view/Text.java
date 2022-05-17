package view;

import model.GameElement;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Text extends GameElement {

    private String message;

    public Text(String message, int x, int y) {
        super(x, y);
        this.message = message;
    }

    @Override
    public void render(Graphics g2) {
        g2.setColor(super.color);
        g2.setFont(new Font("Courier", Font.BOLD, 30));
        g2.drawString(message, super.x, super.y);

    }
    
    @Override
    public void move() { // move method for startup text
        ++x;
        ++y;
        
    }
}
