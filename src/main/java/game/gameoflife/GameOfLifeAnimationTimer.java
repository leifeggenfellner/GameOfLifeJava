package game.gameoflife;

import javafx.animation.AnimationTimer;
import java.util.concurrent.TimeUnit;

public class GameOfLifeAnimationTimer extends AnimationTimer {
    private final GameOfLifeCanvas gameOfLifeCanvas;
    private final GameOfLife gameOfLife;
    private long lastUpdateTime = 0;
    private final long timeStep = TimeUnit.MILLISECONDS.toNanos(100);

    public GameOfLifeAnimationTimer(GameOfLifeCanvas gameOfLifeCanvas, GameOfLife gameOfLife) {
        this.gameOfLifeCanvas = gameOfLifeCanvas;
        this.gameOfLife = gameOfLife;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdateTime >= timeStep) {
            gameOfLife.generateNextGeneration();
            gameOfLifeCanvas.draw(gameOfLife.getGrid());
            lastUpdateTime = now;
        }
    }
}
