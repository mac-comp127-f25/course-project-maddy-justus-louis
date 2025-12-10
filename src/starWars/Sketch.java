package starWars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import edu.macalester.graphics.*;

public class Sketch {
    
    final long[] lastTime = { System.nanoTime() };

    // global variables
    public Sprite p1;
    public Sprite p2;
    public static String p1Path;
    public static String p2Path;
    public CanvasWindow canvas;
    public static double startY = 710;
    private static ArrayList<Element> elements = new ArrayList<>();
    public double p1MaxHeight;
    public double p2MaxHeight;
    public int levelNum;
    public boolean levelReady = false;

    public Sketch(String p1Path, String p2Path){
        canvas = new CanvasWindow("Star Wars — Level 1", 780, 780);
        canvas.setBackground(Color.BLACK);

        this.p1Path = p1Path;
        this.p2Path = p2Path;
        
        setup();
        draw();
    }

    public static void main(String[] args) {
        new StartScreen();
    }

    public void setup(){
        Image bg = new Image("levelBackground2.png");
        bg.setScale(1.05);
        bg.setCenter(canvas.getCenter());
        canvas.add(bg);

        // building level
        String[][] level1 = readCSV("res/level1.csv");
        drawLevel(canvas, level1);
        levelNum = 1;
        levelReady = true;

        // adding sprite
        p1MaxHeight = getMaxHeight(p1Path);
        p1 = new Sprite (p1Path, 30, 700, 1, p1MaxHeight);
        canvas.add(p1);
        p2MaxHeight = getMaxHeight(p2Path);
        p2 = new Sprite (p2Path, 750, 700, 2, p2MaxHeight);
        canvas.add(p2);

        setupKeys();
    }

    public void draw() {
        canvas.animate(() -> {

            // (fps testing)
            long now = System.nanoTime();
            double dt = (now - lastTime[0]) / 1e9;
            lastTime[0] = now;

            double fps = 1.0 / dt;

            if (levelReady && p1.setNextLevel && p2.setNextLevel && (levelNum < 4)){
                levelReady = false;
                canvas.removeAll();

                Image bg = new Image("levelBackground2.png");
                bg.setScale(1.05);
                bg.setCenter(canvas.getCenter());
                canvas.add(bg);

                levelNum ++;
                String path = "res/level" + levelNum + ".csv";
                String[][] level = readCSV(path);
                drawLevel(canvas, level);

                readyNextLevel();
            } else if (p1.setNextLevel && p2.setNextLevel && levelNum == 4){
                new ClosingScreen();
            }

            p1.move(canvas, elements);
            p2.move(canvas, elements);
        });
    }

    // called whenever key pressed
    private void setupKeys() {
        canvas.onKeyDown(key -> {
            p1.startMoving(key.getKey().toString(), elements);
            p2.startMoving(key.getKey().toString(), elements);
        });

        canvas.onKeyUp(key -> {
            p1.stopMoving(key.getKey().toString());
            p2.stopMoving(key.getKey().toString());
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
        elements.clear();
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

    public void readyNextLevel(){
        p1.resetSprite();
        p2.resetSprite();
        canvas.add(p2);
        canvas.add(p1);
        p1.move = true;
        p2.move = true;
        p1.setNextLevel = false;
        p2.setNextLevel = false;

        levelReady = true;
    }

    public static Element getImageValue(String value){
        if (value.equals("1")){
            // Element image = new Element("background.png", "1", 1, 19);
            return null;
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

    public double getMaxHeight(String name){
        double maxHeight = 0;

        if (name.equals("leia")){
            return maxHeight = 38;
        } else if (name.equals("luke")){
            return maxHeight = 46;
        } else if (name.equals("chewy")){
            return maxHeight = 44;
        } else if (name.equals("han")){
            return maxHeight = 40;
        } else if (name.equals("c3po")){
            return maxHeight = 48;
        } else {
            maxHeight = 40;
        } 
        return maxHeight;
    }
}