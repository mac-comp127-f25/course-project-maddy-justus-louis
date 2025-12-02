package starWars;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

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

        Button continueButton = new Button("Continue");
        continueButton.setCenter(390, 510);
        continueButton.setScale(1.5);
        window.add(continueButton);

        continueButton.onClick(() -> {
            window.closeWindow();
            new Sketch();
        });
    }
}

