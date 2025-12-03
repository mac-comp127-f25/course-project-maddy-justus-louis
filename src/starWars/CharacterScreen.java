package starWars;

import edu.macalester.graphics.*;
import java.util.ArrayList;

import java.awt.Color;

public class CharacterScreen {
    private CanvasWindow window;
    public int index = 0;
    public ArrayList<GraphicsGroup> charOptions = new ArrayList<>();

    public CharacterScreen() {
        window = new CanvasWindow("Star Wars — Character Choice Screen", 780, 780);
        window.setBackground(Color.BLACK);

        Image title = new Image("characterTitle.png");
        title.setScale(0.6);
        title.setCenter(390, 220);
        window.add(title);

        // character groups
        // luke and leia
        GraphicsGroup lukeLeiaGroup = new GraphicsGroup();
        Image luke = new Image("lukeForward.png");
        luke.setMaxHeight(110);
        luke.setCenter(390+50, 595);
        lukeLeiaGroup.add(luke);
        Image leia = new Image("leiaForward.png");
        leia.setMaxHeight(85);
        leia.setCenter(390-50, 600);
        lukeLeiaGroup.add(leia);
        window.add(lukeLeiaGroup);
        charOptions.add(lukeLeiaGroup);

        // han and chewy
        GraphicsGroup hanChewyGroup = new GraphicsGroup();
        Image han = new Image("hanForward.png");
        han.setMaxHeight(85);
        han.setCenter(390+50, 595);
        hanChewyGroup.add(han);
        Image chewy = new Image("chewyForward.png");
        chewy.setMaxHeight(85);
        chewy.setCenter(390-50, 600);
        hanChewyGroup.add(chewy);
        charOptions.add(hanChewyGroup);

        // c3po and r2d2
        GraphicsGroup c3poR2D2Group = new GraphicsGroup();
        Image c3po = new Image("c3poForward.png");
        c3po.setMaxHeight(85);
        c3po.setCenter(390+50, 595);
        c3poR2D2Group.add(c3po);
        Image r2d2 = new Image("r2d2Forward.png");
        r2d2.setMaxHeight(85);
        r2d2.setCenter(390-50, 600);
        c3poR2D2Group.add(r2d2);
        charOptions.add(c3poR2D2Group);


        // start button
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

        // arrow (R/L) button
        GraphicsGroup rightArrowGroup = new GraphicsGroup();
        Image rightArrow = new Image("arrowR.png");
        rightArrow.setCenter(585, 600);
        rightArrow.setScale(0.5);
        rightArrowGroup.add(rightArrow);
        window.add(rightArrowGroup);

        window.onClick(event -> {
            if (rightArrowGroup.testHitInLocalCoordinates(event.getPosition().getX(), event.getPosition().getY())) {
                System.out.println(index);
                if (index == charOptions.size()-1){
                    window.remove(charOptions.get(index));
                    index = 0;
                    window.add(charOptions.get(index));
                } else {
                    window.remove(charOptions.get(index));
                    window.add(charOptions.get(index+1));
                    index++;
                }
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
                if (index == 0){
                    window.remove(charOptions.get(0));
                    index = charOptions.size()-1;
                    window.add(charOptions.get(index));
                } else {
                    window.remove(charOptions.get(index));
                    window.add(charOptions.get(index-1));
                    index--;
                }
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

