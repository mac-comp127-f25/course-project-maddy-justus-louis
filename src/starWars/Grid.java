package starWars;

import edu.macalester.graphics.*;
import java.awt.Color;

public class Grid {
    public static void main(String[] args) {
        int padding = 30;
        int canvasSize = 780;
        int cellSize = 18;
        int gridSize = 18*40;
        CanvasWindow canvas = new CanvasWindow("Grid", canvasSize, canvasSize);

        int rows = gridSize/cellSize;
        int cols = gridSize/cellSize;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = col * cellSize;
                double y = row * cellSize;

                Rectangle cell = new Rectangle(x + padding, y + padding, cellSize, cellSize);
                cell.setFilled(false);
                cell.setStrokeColor(Color.BLACK);
                canvas.add(cell);
            }
        }
    }
}
