package starWars;

import edu.macalester.graphics.*;

import java.awt.Color;

public class CharacterScreen {
    private CanvasWindow window;

    public CharacterScreen() {
        window = new CanvasWindow("Star Wars — Character Choice Screen", 780, 780);
        window.setBackground(Color.BLACK);

        Image title = new Image("characterTitle.png");
        title.setScale(0.6);
        title.setCenter(390, 220);
        window.add(title);

        GraphicsGroup startButtonGroup = new GraphicsGroup();
        Image startButton = new Image("startGameButton.png");
        startButton.setCenter(390, 710);
        startButton.setScale(0.5);
        startButtonGroup.add(startButton);
        window.add(startButtonGroup);

        window.onClick(event -> {
            if (startButtonGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                window.closeWindow(); 
                new Sketch();     
            }
        });

        window.onMouseMove(event -> {
            if (startButtonGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                startButtonGroup.setScale(1.05);
            } else {
                startButtonGroup.setScale(1);
            }
        });

        GraphicsGroup rightArrowGroup = new GraphicsGroup();
        Image rightArrow = new Image("arrowR.png");
        rightArrow.setCenter(585, 600);
        rightArrow.setScale(0.5);
        rightArrowGroup.add(rightArrow);
        window.add(rightArrowGroup);

        window.onClick(event -> {
            if (rightArrowGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                //  replace with switching to next character set   
            }
        });

        window.onMouseMove(event -> {
            if (rightArrowGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                rightArrowGroup.setScale(1.05);
            } else {
                rightArrowGroup.setScale(1);
            }
        });

        GraphicsGroup leftArrowGroup = new GraphicsGroup();
        Image leftArrow = new Image("arrowL.png");
        leftArrow.setCenter(195, 600);
        leftArrow.setScale(0.5);
        leftArrowGroup.add(leftArrow);
        window.add(leftArrowGroup);

        window.onClick(event -> {
            if (leftArrowGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                //  replace with switching to next character set   
            }
        });

        window.onMouseMove(event -> {
            if (leftArrowGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                leftArrowGroup.setScale(1.05);
            } else {
                leftArrowGroup.setScale(1);
            }
        });
    }
}

