package starWars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import edu.macalester.graphics.*;

public class Sketch {

    // final long[] lastTime = { System.nanoTime() }; (fps testing)

    // global variables
    Sprite p;
    CanvasWindow canvas;
    public static double startY = 710;
    private static ArrayList<Element> elements = new ArrayList<>();

    public Sketch(){
        canvas = new CanvasWindow("Star Wars — Level 1", 780, 780);
        canvas.setBackground(Color.BLACK);
        setup();
        draw();
    }

    public static void main(String[] args) {
        //new Sketch();
        new StartScreen();
    }

    public void setup(){
        String[][] level = readCSV("res/level1.csv");
        drawLevel(canvas, level);

        p = new Sprite ("leia.png", .08, 30, 700);
        canvas.add(p);

        setupKeys();
    }

    public void draw() {
        canvas.animate(() -> {

            // (fps testing)
            // long now = System.nanoTime();
            // double dt = (now - lastTime[0]) / 1e9;
            // lastTime[0] = now;

            // double fps = 1.0 / dt;
            // System.out.println("FPS = " + fps);

            p.move(canvas, elements);
        });
    }

    // called whenever key pressed
    private void setupKeys() {
        canvas.onKeyDown(key -> {
            p.startMoving(key.getKey().toString());
            System.out.println(key.getKey().toString());
        });

        canvas.onKeyUp(key -> {
            p.stopMoving(key.getKey().toString());
        });
    }

    public static String[][] readCSV(String filename) {
        String[][] result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int rowCount = 0;

            int cols = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (cols == 0) {
                    cols = values.length;
                }
                rowCount++;
            }

            result = new String[rowCount][cols];

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int col = 0; col < values.length; col++) {
                    result[row][col] = values[col].trim();
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());
        return result;
    }

    public static void drawLevel(CanvasWindow canvas, String[][] level) {
        double currentX = 10;
        double currentY = 10;
        double side = 19;

        for (int row = 0; row < level.length; row++) {
            for (int col = 0; col < level[row].length; col++) {
                String value = level[row][col];

                Element img = getImageValue(value);

                if (img != null) {
                    img.setCenter(currentX + side/2, currentY + side/2);
                    canvas.add(img);
                    elements.add(img);
                }
                currentX += side;
            }
            currentY += side;
            currentX = 10;
        }
    }

    public static Element getImageValue(String value){
        if (value.equals("1")){
            Element image = new Element("background.png", "1", 1, 19);
            return image;
        } else if (value.equals("2")) {
            Element image = new Element("walls.png", "2", 1.2, 19);
            return image;
        } else if (value.equals("3a")) {
            Element image = new Element("exitA.png", "3a", 1.1, 19);
            return image;
        } else if (value.equals("3b")) {
            Element image = new Element("exitB.png", "3b", 1.1, 19);
            return image;
        } else if (value.equals("3c")) {
            Element image = new Element("exitC.png", "3c", 1.1, 19);
            return image;
        } else if (value.equals("3d")) {
            Element image = new Element("exitD.png", "3d", 1.1, 19);
            return image;
        } else if (value.equals("4")) {
            Element image = new Element("blue.png", "4", 1.7, 19);
            return image;
        } else if (value.equals("5")) {
            Element image = new Element("yellow.png", "5", 1.7, 19);
            return image;
        } else if (value.equals("6")) {
            Element image = new Element("ground.png", "6", 1.3, 19);
            return image;
        } else {
            Element image = new Element("poison.png", "7", 1.5, 19);
            return image;
        }
    }
}
