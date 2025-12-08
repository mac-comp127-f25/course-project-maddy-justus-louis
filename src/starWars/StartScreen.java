package starWars;

import edu.macalester.graphics.*;

public class StartScreen {
    private CanvasWindow window;

    public StartScreen() {
        window = new CanvasWindow("Star Wars — Start Screen", 780, 780);
        Image bg = new Image("screenBackground.png");
        bg.setScale(.9);
        bg.setCenter(window.getCenter().getX(), window.getCenter().getY() - 15);
        window.add(bg);

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

        GraphicsGroup howToPlayButtonGroup = new GraphicsGroup();
        Image howToPlayButton = new Image("howToPlayButton.png");
        howToPlayButton.setCenter(390, 475);
        howToPlayButton.setScale(0.3);
        howToPlayButtonGroup.add(howToPlayButton);
        window.add(howToPlayButtonGroup);


        window.onClick(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (continueButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
                new CharacterScreen();
            }
            if (howToPlayButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
                new HelpScreen();
            }
        });

        window.onMouseMove(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (continueButtonGroup.testHitInLocalCoordinates(x, y)) {
                continueButtonGroup.setScale(1.05);
            } else {
                continueButtonGroup.setScale(1);
            }

            if (howToPlayButtonGroup.testHitInLocalCoordinates(x, y)) {
                howToPlayButtonGroup.setScale(1.05);
            } else {
                howToPlayButtonGroup.setScale(1);
            }
        });
    }
}
