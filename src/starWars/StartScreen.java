package starWars;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;

public class StartScreen {
    private CanvasWindow window;

    public StartScreen() {
        window = new CanvasWindow("Star Wars — Start Screen", 780, 780);
        window.setBackground(Color.BLACK);

        Image title = new Image("starWars.png");
        title.setScale(0.2);
        title.setCenter(390, 220);
        window.add(title);

        Image name = new Image("gameName.png");
        name.setScale(0.65);
        name.setCenter(390, 400);
        window.add(name);

        Image gameSublabel = new Image("gameSublabel.png");
        gameSublabel.setScale(0.2);
        gameSublabel.setCenter(390, 440);
        window.add(gameSublabel);

        GraphicsGroup continueButtonGroup = new GraphicsGroup();
        Image continueButton = new Image("continueButton.png");
        continueButton.setCenter(390, 710);
        continueButton.setScale(0.5);
        continueButtonGroup.add(continueButton);
        window.add(continueButtonGroup);

        window.onClick(event -> {
            if (continueButtonGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                window.closeWindow();
                new CharacterScreen();
            }
        });

        window.onMouseMove(event -> {
            if (continueButtonGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                continueButtonGroup.setScale(1.05);
            } else {
                continueButtonGroup.setScale(1);
            }
        });
    }
}
