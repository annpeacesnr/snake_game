package model.strategyPattern;
import java.awt.Graphics;
import java.awt.Color;

import model.Snake;

public class SnakeRenderDeadStrategy implements SnakeRenderStrategy {

    private Snake snake;

    public SnakeRenderDeadStrategy(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void renderAlgorithm(Graphics g2) {
        var composite = snake.getComposite();
        for (var s: composite) {
            s.color = Color.gray;
            s.filled = false;
            s.render(g2);
        }
    }

}


