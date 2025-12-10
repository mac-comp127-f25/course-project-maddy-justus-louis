package starWars;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

public class ClosingScreen {
    private CanvasWindow window;

    public ClosingScreen(){
        window = new CanvasWindow("Star Wars — Closing Screen", 780, 780);

        Image bg = new Image("screenBackground.png");
        bg.setScale(.9);
        bg.setCenter(window.getCenter().getX(), window.getCenter().getY() - 15);
        window.add(bg);

        Image youWon = new Image("youWonLabel.png");
        youWon.setMaxHeight(200);
        youWon.setCenter(390, 200);
        window.add(youWon);

        Image falcon = new Image("falconExit.png");
        falcon.setMaxHeight(350);
        falcon.setCenter(390, 450);
        window.add(falcon);

        GraphicsGroup homeButtonGroup = new GraphicsGroup();
        Image homeButton = new Image("returnToHomeButton.png");
        homeButton.setScale(0.5);
        homeButton.setCenter(390, 640);
        homeButtonGroup.add(homeButton);
        window.add(homeButtonGroup);

        GraphicsGroup exitButtonGroup = new GraphicsGroup();
        Image exitButton = new Image("exitButton.png");
        exitButton.setScale(0.5);
        exitButton.setCenter(390, 710);
        exitButtonGroup.add(exitButton);
        window.add(exitButtonGroup);

        window.onClick(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (homeButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
                new StartScreen();
            }

            if (exitButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
            }
        });

        window.onMouseMove(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (homeButtonGroup.testHitInLocalCoordinates(x, y)) {
                homeButtonGroup.setScale(1.05);
            } else {
                homeButtonGroup.setScale(1);
            }

            if (exitButtonGroup.testHitInLocalCoordinates(x, y)) {
                exitButtonGroup.setScale(1.05);
            } else {
                exitButtonGroup.setScale(1);
            }
        });
    }

}
