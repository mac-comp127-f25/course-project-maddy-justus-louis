package starWars;

import edu.macalester.graphics.*;
import java.util.ArrayList;

public class CharacterScreen {

    private CanvasWindow window;
    public int index = 0;

    public ArrayList<GraphicsGroup> charOptions = new ArrayList<>();
    public static GraphicsGroup selected;
    public Image selectedLabel;
    public String p1Path;
    public String p2Path;

    public CharacterScreen() {

        window = new CanvasWindow("Star Wars — Character Choice Screen", 780, 780);
        Image bg = new Image("screenBackground.png");
        bg.setScale(1.05);
        bg.setCenter(window.getCenter().getX(), window.getCenter().getY() - 15);
        window.add(bg);

        /**
         *  labels
         */
        Image falcon = new Image("falcon.png");
        falcon.setScale(0.2);
        falcon.setCenter(623, 600);
        falcon.setRotation(10);
        window.add(falcon);

        /**
         *  labels
         */
        Image title = new Image("characterTitle.png");
        title.setScale(0.6);
        title.setCenter(390, 100);
        window.add(title);

        selectedLabel = new Image("selectedLabel.png");
        selectedLabel.setScale(0.25);
        selectedLabel.setCenter(390, 200);

        /**
         *  character groups (charOptions and selected)
         */

        // luke and leia
        GraphicsGroup lukeLeiaGroup = new GraphicsGroup();
        Image luke = new Image("lukeForward.png");
        luke.setMaxHeight(210);
        double lukeY = 400 - luke.getHeight()/2;
        luke.setCenter(490, lukeY);
        lukeLeiaGroup.add(luke);

        Image leia = new Image("leiaForward.png");
        leia.setMaxHeight(180);
        double leiaY = 400 - leia.getHeight()/2;
        leia.setCenter(290, leiaY);
        lukeLeiaGroup.add(leia);

        charOptions.add(lukeLeiaGroup);

        // han and chewy
        GraphicsGroup hanChewyGroup = new GraphicsGroup();
        Image han = new Image("hanForward.png");
        han.setMaxHeight(190);
        double hanY = 400 - han.getHeight()/2;
        // han.setPosition(290 + han.getWidth(), (600 + 190));
        han.setCenter(490, hanY);
        hanChewyGroup.add(han);

        Image chewy = new Image("chewyForward.png");
        chewy.setMaxHeight(190);
        double chewyY = 400 - chewy.getHeight()/2;
        // chewy.setPosition(290 + chewy.getWidth(), (600 + 190));
        chewy.setCenter(290, chewyY);
        hanChewyGroup.add(chewy);

        charOptions.add(hanChewyGroup);

        // c3po and r2d2
        GraphicsGroup c3poR2D2Group = new GraphicsGroup();
        Image c3po = new Image("c3poForward.png");
        c3po.setMaxHeight(200);
        double c3poY = 400 - c3po.getHeight()/2;
        // c3po.setPosition(290 + c3po.getWidth(), (600 + 210));
        c3po.setCenter(490, c3poY);
        c3poR2D2Group.add(c3po);

        Image r2d2 = new Image("r2d2Forward.png");
        r2d2.setMaxHeight(180);
        double r2d2Y = 400 - r2d2.getHeight()/2;
        // r2d2.setPosition(290 + r2d2.getWidth(), (600 + 210));
        r2d2.setCenter(290, r2d2Y);
        c3poR2D2Group.add(r2d2);

        charOptions.add(c3poR2D2Group);

        // udpate selected
        selected = charOptions.get(0);
        window.add(selected);
        selected.add(selectedLabel);
        p1Path = "leia";
        p2Path = "luke";
        System.out.println(p1Path + "   " + p2Path);

        /**
         *  start button
         */
        GraphicsGroup startButtonGroup = new GraphicsGroup();
        Image startButton = new Image("startGameButton.png");
        startButton.setCenter(390, 710);
        startButton.setScale(0.6);
        startButtonGroup.add(startButton);
        window.add(startButtonGroup);

        /**
         *  select button
         */
        GraphicsGroup selectButtonGroup = new GraphicsGroup();
        Image selectButton = new Image("selectButton.png");
        selectButton.setCenter(390, 450);
        selectButton.setScale(0.45);
        selectButtonGroup.add(selectButton);
        window.add(selectButtonGroup);

        /**
         *  arrows
         */
        GraphicsGroup rightArrowGroup = new GraphicsGroup();
        Image rightArrow = new Image("arrowR.png");
        rightArrow.setCenter(635, 300);
        rightArrow.setScale(0.9);
        rightArrowGroup.add(rightArrow);
        window.add(rightArrowGroup);

        GraphicsGroup leftArrowGroup = new GraphicsGroup();
        Image leftArrow = new Image("arrowL.png");
        leftArrow.setCenter(145, 300);
        leftArrow.setScale(0.9);
        leftArrowGroup.add(leftArrow);
        window.add(leftArrowGroup);

        /**
         *  click events
         */
        window.onClick(event -> {

            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            /**
             * start button
             */
            if (startButtonGroup.testHitInLocalCoordinates(x, y)) {
                window.closeWindow();
                new Sketch(p1Path, p2Path);
                return;
            }

            /**
             * select button
             */
            if (selectButtonGroup.testHitInLocalCoordinates(x, y)) {    
                selected.remove(selectedLabel); 
                selected = charOptions.get(index);
                selected.add(selectedLabel); 

                if (index == 0){
                    p1Path = "leia";
                    p2Path = "luke";
                } else if (index == 1){
                    p1Path = "chewy";
                    p2Path = "han";
                } else {
                    p1Path = "c3po";
                    p2Path = "r2d2";
                }
                return;
            }

            /**
             * right arrow
             */
            if (rightArrowGroup.testHitInLocalCoordinates(x, y)) {
                window.remove(charOptions.get(index));
                index = (index == charOptions.size() - 1) ? 0 : index + 1;
                window.add(charOptions.get(index));
                System.out.println(p1Path + "   " + p2Path);
                return;
            }

            /**
             * left arrow
             */
            if (leftArrowGroup.testHitInLocalCoordinates(x, y)) {
                window.remove(charOptions.get(index));
                index = (index == 0) ? charOptions.size() - 1 : index - 1;
                window.add(charOptions.get(index));
                System.out.println(p1Path + "   " + p2Path);
            }

        });

        /**
         * hover effects
         */
        window.onMouseMove(event -> {

            double x = event.getPosition().getX();
            double y = event.getPosition().getY();

            if (startButtonGroup.testHitInLocalCoordinates(x, y)) {
                startButtonGroup.setScale(1.05);
            } else {
                startButtonGroup.setScale(1);
            }

            if (selectButtonGroup.testHitInLocalCoordinates(x, y)) {
                selectButtonGroup.setScale(1.05);
            } else {
                selectButtonGroup.setScale(1);
            }

            if (rightArrowGroup.testHitInLocalCoordinates(x, y)) {
                rightArrowGroup.setScale(1.05);
            } else {
                rightArrowGroup.setScale(1);
            }

            if (leftArrowGroup.testHitInLocalCoordinates(x, y)) {
                leftArrowGroup.setScale(1.05);
            } else {
                leftArrowGroup.setScale(1);
            }
        });
    }

    // private void updatePaths() {
    //     if (index == 0){
    //         Sketch.p1Path = "leia";
    //         Sketch.p2Path = "luke";
    //     } else if (index == 1){
    //         Sketch.p1Path = "chewy";
    //         Sketch.p2Path = "han";
    //     } else {
    //         Sketch.p1Path = "c3po";
    //         Sketch.p2Path = "r2d2";
    //     }
    //     System.out.println("UPDATED: " + Sketch.p1Path + "   " + Sketch.p2Path);
    // }
}