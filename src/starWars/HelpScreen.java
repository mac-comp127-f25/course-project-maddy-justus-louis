package starWars;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

public class HelpScreen {
    private CanvasWindow window;

    public HelpScreen() {
        window = new CanvasWindow("Star Wars — Help Screen", 780, 780);
        page1();
    }

    public void page1(){
        Image bg = new Image("screenBackground.png");
        bg.setScale(.9);
        bg.setCenter(window.getCenter().getX(), window.getCenter().getY() - 15);
        window.add(bg);

        Image title = new Image("howToPlayLabel.png");
        title.setScale(0.7);
        title.setCenter(390, 100);
        window.add(title);

        Image descript = new Image("descript.png");
        descript.setScale(0.2);
        descript.setCenter(390, 150);
        window.add(descript);

        Image player1 = new Image("player1.png");
        player1.setScale(0.5);
        player1.setCenter(195, 240);
        window.add(player1);

        Image player2 = new Image("player2.png");
        player2.setScale(0.5);
        player2.setCenter(585, 240);
        window.add(player2);

        Image playerOpt1 = new Image("playerOptLabel.png");
        playerOpt1.setScale(0.2);
        playerOpt1.setCenter(195, 300);
        window.add(playerOpt1);

        Image playerOpt2 = new Image("playerOptLabel.png");
        playerOpt2.setScale(0.2);
        playerOpt2.setCenter(585, 300);
        window.add(playerOpt2);

        GraphicsGroup p1Options = new GraphicsGroup();
        Image luke = new Image("lukeForward.png");
        luke.setMaxHeight(50);
        luke.setCenter(130, 360);
        p1Options.add(luke);
        Image han = new Image("hanForward.png");
        han.setMaxHeight(50);
        han.setCenter(195, 360);
        p1Options.add(han);
        Image r2d2 = new Image("r2d2Forward.png");
        r2d2.setMaxHeight(50);
        r2d2.setCenter(260, 360);
        p1Options.add(r2d2);
        window.add(p1Options);

        GraphicsGroup p2Options = new GraphicsGroup();
        Image leia = new Image("leiaForward.png");
        leia.setMaxHeight(50);
        leia.setCenter(520, 360);
        p2Options.add(leia);
        Image chewy = new Image("chewyForward.png");
        chewy.setMaxHeight(50);
        chewy.setCenter(585, 360);
        p2Options.add(chewy);
        Image c3po = new Image("c3poForward.png");
        c3po.setMaxHeight(50);
        c3po.setCenter(650, 360);
        p2Options.add(c3po);
        window.add(p2Options);

        Image playerBlock1 = new Image("blockLabel.png");
        playerBlock1.setScale(0.2);
        playerBlock1.setCenter(195, 440);
        window.add(playerBlock1);

        Image playerBlock2 = new Image("blockLabel.png");
        playerBlock2.setScale(0.2);
        playerBlock2.setCenter(585, 440);
        window.add(playerBlock2);

        Image p1Block = new Image("p1Block.png");
        p1Block.setMaxHeight(80);
        p1Block.setCenter(195, 480);
        window.add(p1Block);

        Image p2Block = new Image("p2Block.png");
        p2Block.setMaxHeight(80);
        p2Block.setCenter(585, 480);
        window.add(p2Block);

        Image playerArrows1 = new Image("keysLabel.png");
        playerArrows1.setScale(0.2);
        playerArrows1.setCenter(195, 535);
        window.add(playerArrows1);

        Image playerArrows2 = new Image("keysLabel.png");
        playerArrows2.setScale(0.2);
        playerArrows2.setCenter(585, 535);
        window.add(playerArrows2);

        Image p1Arrows = new Image("p1Arrows.png");
        p1Arrows.setScale(0.2);
        p1Arrows.setCenter(195, 585);
        window.add(p1Arrows);

        Image p2Arrows = new Image("p2Arrows.png");
        p2Arrows.setScale(0.2);
        p2Arrows.setCenter(585, 585);
        window.add(p2Arrows);

        GraphicsGroup nextButtonGroup = new GraphicsGroup();
        Image nextButton = new Image("nextButton.png");
        nextButton.setScale(0.5);
        nextButton.setCenter(390, 710);
        nextButtonGroup.add(nextButton);
        window.add(nextButtonGroup);

        window.onClick(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (nextButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.removeAll();
                page2();
            }
        });

        window.onMouseMove(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (nextButtonGroup.testHitInLocalCoordinates(x, y)) {
                nextButtonGroup.setScale(1.05);
            } else {
                nextButtonGroup.setScale(1);
            }
        });
    }
    
    public void page2(){
        Image bg = new Image("screenBackground.png");
        bg.setScale(.9);
        bg.setCenter(window.getCenter().getX(), window.getCenter().getY() - 15);
        window.add(bg);

        Image title = new Image("howToPlayLabel.png");
        title.setScale(0.7);
        title.setCenter(390, 100);
        window.add(title);

        Image rules = new Image("rulesLabel.png");
        rules.setScale(0.4);
        rules.setCenter(390, 190);
        window.add(rules);

        Image rule1 = new Image("rule1.png");
        rule1.setScale(0.3);
        rule1.setCenter(390, 300);
        window.add(rule1);

        Image p1 = new Image("p1Label.png");
        p1.setScale(0.3);
        p1.setCenter(195, 360);
        window.add(p1);

        Image p2Block = new Image("p2Block.png");
        p2Block.setMaxHeight(80);
        p2Block.setCenter(220, 360);
        window.add(p2Block);

        Image xImg1 = new Image("X.png");
        xImg1.setMaxHeight(80);
        xImg1.setCenter(220, 360);
        window.add(xImg1);

        Image p2 = new Image("p2Label.png");
        p2.setScale(0.3);
        p2.setCenter(250, 360);
        window.add(p2);

        Image p1Block = new Image("p1Block.png");
        p1Block.setMaxHeight(80);
        p1Block.setCenter(275, 360);
        window.add(p1Block);

        Image xImg2 = new Image("X.png");
        xImg2.setMaxHeight(80);
        xImg2.setCenter(275, 360);
        window.add(xImg2);

        Image rule2 = new Image("rule2.png");
        rule2.setScale(0.3);
        rule2.setCenter(390, 420);
        window.add(rule2);

        Image poison = new Image("poison.png");
        poison.setMaxHeight(70);
        poison.setCenter(390, 480);
        window.add(poison);

        Image rule3 = new Image("rule3.png");
        rule3.setScale(0.3);
        rule3.setCenter(390, 540);
        window.add(rule3);

        Image exit = new Image("exit.png");
        exit.setScale(0.1);
        exit.setCenter(390, 610);
        window.add(exit);

        GraphicsGroup homeButtonGroup = new GraphicsGroup();
        Image homeButton = new Image("returnToHomeButton.png");
        homeButton.setScale(0.5);
        homeButton.setCenter(390, 710);
        homeButtonGroup.add(homeButton);
        window.add(homeButtonGroup);

        window.onClick(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (homeButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
                new StartScreen();
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
        });
    }
}
