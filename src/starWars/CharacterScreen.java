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
    }
}

