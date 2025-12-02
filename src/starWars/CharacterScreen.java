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



        Image continueButton = new Image("startGameButton");
        continueButton.setCenter(390, 510);
        continueButton.setScale(1.5);
        window.add(continueButton);

        // canvas.onClick(event -> {
        //     if (startButton.contains(event.getPosition())) {
        //         System.out.println("Start button clicked!");
        //         // Do something, like start game
        //         canvas.close();  // for example, close menu
        //         new Sketch();    // launch your game
        //     }
        // });

        window.onClick(() -> {
            if (continueButton.contains(mouse.getPosition())) {
                //getX + width
                //gety +height
                // check if mouse click is in bounds of image and if so then
                // create inBounds helper methods
                System.out.println("Start button clicked!");
                // Do something, like start game
                window.closeWindow();
                new Sketch();
            }
            window.closeWindow();
            new Sketch();
        });
    }
}

