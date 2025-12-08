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
        title.setScale(0.2);
        title.setCenter(390, 100);
        window.add(title);

        Image descript = new Image("descript.png");
        descript.setScale(0.2);
        descript.setCenter(390, 150);
        window.add(descript);

        Image player1 = new Image("player1.png");
        player1.setScale(0.2);
        player1.setCenter(195, 195);
        window.add(player1);

        Image player2 = new Image("player2.png");
        player2.setScale(0.2);
        player2.setCenter(585, 195);
        window.add(player2);

        GraphicsGroup p1Options = new GraphicsGroup();
        Image luke = new Image("lukeForward.png");
        luke.setScale(0.2);
        luke.setCenter(130, 360);
        p1Options.add(luke);
        Image han = new Image("hanForward.png");
        han.setScale(0.2);
        han.setCenter(195, 360);
        p1Options.add(han);
        Image r2d2 = new Image("r2d2Forward.png");
        r2d2.setScale(0.2);
        r2d2.setCenter(260, 360);
        p1Options.add(r2d2);
        window.add(p1Options);

        GraphicsGroup p2Options = new GraphicsGroup();
        Image leia = new Image("leiaForward.png");
        leia.setScale(0.2);
        leia.setCenter(520, 360);
        p2Options.add(leia);
        Image chewy = new Image("chewyForward.png");
        chewy.setScale(0.2);
        chewy.setCenter(585, 360);
        p2Options.add(chewy);
        Image c3po = new Image("c3poForward.png");
        c3po.setScale(0.2);
        c3po.setCenter(650, 360);
        p2Options.add(c3po);
        window.add(p2Options);

        Image p1Block = new Image("p1Block.png");
        p1Block.setScale(0.2);
        p1Block.setCenter(195, 480);
        window.add(p1Block);

        Image p2Block = new Image("player2Block.png");
        p2Block.setScale(0.2);
        p2Block.setCenter(585, 480);
        window.add(p2Block);

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
        nextButton.setScale(0.2);
        nextButton.setCenter(585, 585);
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
        title.setScale(0.2);
        title.setCenter(390, 100);
        window.add(title);

        Image rules = new Image("rulesLabel.png");
        rules.setScale(0.2);
        rules.setCenter(390, 160);
        window.add(rules);

        Image rule1 = new Image("rule1.png");
        rule1.setScale(0.2);
        rule1.setCenter(195, 195);
        window.add(rule1);

        Image rule2 = new Image("rule2.png");
        rule2.setScale(0.2);
        rule2.setCenter(195, 195);
        window.add(rule2);

        Image rule3 = new Image("rule3.png");
        rule3.setScale(0.2);
        rule3.setCenter(195, 195);
        window.add(rule3);

        Image p1Block = new Image("p1Block.png");
        p1Block.setScale(0.2);
        p1Block.setCenter(195, 480);
        window.add(p1Block);

        Image p2Block = new Image("player2Block.png");
        p2Block.setScale(0.2);
        p2Block.setCenter(585, 480);
        window.add(p2Block);

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
        nextButton.setScale(0.2);
        nextButton.setCenter(585, 585);
        nextButtonGroup.add(nextButton);
        window.add(nextButtonGroup);

        window.onClick(event -> {
            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (nextButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
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
}
