package starWars;

import edu.macalester.graphics.*;
import java.awt.Color;

public class Sprite extends GraphicsGroup {

    private final double runSpeed = 5;
    private final double gravity = 0.5;
    private final double jumpStrength = -7;
    private final double maxFallSpeed = 10;
    private double groundY = 700;

    public double vx = 0;
    public double vy = 0;

    public boolean onGround = false;
    private boolean movingLeft, movingRight;

    private double width, height;

    public Sprite(String imageFile, double scale, double x, double y) {
        Ellipse debug = new Ellipse(0, 0, 20, 20);
        debug.setFillColor(Color.RED);
        add(debug);

        width = debug.getWidth();
        height = debug.getHeight();

        setCenter(x, y);

        movingLeft = false;
        movingRight = false;

    }

    public void move(CanvasWindow canvas) {
       double dx = 0;

       if (movingLeft) dx -= runSpeed;
       if (movingRight) dx += runSpeed;

       vy += gravity;
       double dy = vy;

       moveBy(dx,dy);

       if (getY() >= groundY){
           setY(groundY);
           vy = 0;
           onGround = true;
       }
    }

    public void startMoving(String key) {
       if (key.equals("UP_ARROW") || key.equals("W")){
           if (onGround){
               vy = jumpStrength;
               vx = 1;
               onGround = false;
           }
       }
       if (key.equals("DOWN_ARROW") || key.equals("S")){
           if (vy < 0) {
               vy = 0;
           }
       }
       if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = true;
       if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = true;
    }


    public void stopMoving(String key) {
        if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = false;
        if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = false;
    }









    // Collisions with a single element (Breakout-style)
    public void collideWith(Element e) {
        if ("1".equals(e.getType())) return; // ignore background

        // Horizontal
        if (vx > 0 && getRight() > e.getLeft() && getLeft() < e.getLeft()) {
            setCenter(e.getLeft() - width/2, getCenter().getY());
            vx = 0;
        } else if (vx < 0 && getLeft() < e.getRight() && getRight() > e.getRight()) {
            setCenter(e.getRight() + width/2, getCenter().getY());
            vx = 0;
        }

        // Vertical
        if (vy > 0 && getBottom() > e.getTop() && getTop() < e.getTop()) {
            setCenter(getCenter().getX(), e.getTop() - height/2);
            vy = 0;
            onGround = true;
        } else if (vy < 0 && getTop() < e.getBottom() && getBottom() > e.getBottom()) {
            setCenter(getCenter().getX(), e.getBottom() + height/2);
            vy = 0;
        }
    }

    public double getLeft() { return getCenter().getX() - width/2; }
    public double getRight(){ return getCenter().getX() + width/2; }
    public double getTop()  { return getCenter().getY() - height/2; }
    public double getBottom(){ return getCenter().getY() + height/2; }
}

// package starWars;

// import edu.macalester.graphics.CanvasWindow;
// import edu.macalester.graphics.GraphicsGroup;
// import edu.macalester.graphics.Image;
// import edu.macalester.graphics.Ellipse;
// import java.awt.Color;
// import java.util.ArrayList;

// public class Sprite extends GraphicsGroup {

//     private final double runSpeed = 5;
//     private final double gravity = 0.5;
//     private final double jumpStrength = -10;
//     private final double maxFallSpeed = 12;

//     public double vx = 0; //change to have get and set methods
//     public double vy = 0; //change to have get and set methods

//     private boolean movingLeft = false, movingRight = false;
//     public boolean onGround = false; //change to have get and set methods

//     private double width;
//     private double height;

//     public Sprite(String imageFile, double scale, double x, double y) {

//         // *** TEMP debugging shape instead of image ***
//         Ellipse debug = new Ellipse(0, 0, 20, 20);
//         debug.setFillColor(Color.RED);
//         add(debug);
//         width = debug.getWidth();
//         height = debug.getHeight();

//         // If you want your actual image:
//         // image = new Image(imageFile);
//         // image.setScale(scale);
//         // add(image);
//         // width = image.getWidth();
//         // height = image.getHeight();

//         setCenter(x, y);
//     }


//     public void move(CanvasWindow canva, ArrayList<Element> elements) {

//         // --- Horizontal movement ---
//         if (movingLeft)  vx = -runSpeed;
//         else if (movingRight) vx = runSpeed;
//         else vx = 0;

//         moveBy(vx, 0);
//         horizontalCollisions(elements);

//         // --- Vertical movement ---
//         vy += gravity;
//         if (vy > maxFallSpeed) vy = maxFallSpeed;

//         moveBy(0, vy);
//         verticalCollisions(elements);
//     }

//     public void startMoving(String key) {
//         if (key.equals("UP_ARROW") || key.equals("W")){
//             if (onGround){
//                 vy = jumpStrength;
//                 onGround = false;
//             }
//         } 
//         if (key.equals("DOWN_ARROW") || key.equals("S")){
//             if (vy < 0) {
//                 vy = 0;
//             }
//         }
//         if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = true;
//         if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = true;
//     }

//     public void stopMoving(String key) {
//         if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = false;
//         if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = false;
//     }

//     // Collisions
//     private void horizontalCollisions(ArrayList<Element> elements) {
//         for (Element e : elements) {
//             if (e == null || "1".equals(e.getType())) continue;
//             if (collides(e)) {
//                 if (vx > 0) setRight(e.getLeft());
//                 if (vx < 0) setLeft(e.getRight());
//                 vx = 0;
//             }
//         }
//     }

//     private void verticalCollisions(ArrayList<Element> elements, double nextY) {
//         onGround = false;

//         double nextBottom = getBottom() + vy;
//         double nextTop = getTop() + vy;

//         for (Element e : elements) {
//             if (e == null || "1".equals(e.getType())) continue;

//             // Falling
//             if (vy > 0) {
//                 if (getBottom() <= e.getTop() && nextBottom >= e.getTop()) {
//                     // Land on top
//                     setBottom(e.getTop());
//                     vy = 0;
//                     onGround = true;
//                     break;
//                 }
//             }

//             // Jumping
//             if (vy < 0) {
//                 if (getTop() >= e.getBottom() && nextTop <= e.getBottom()) {
//                     // Hit ceiling
//                     setTop(e.getBottom());
//                     vy = 0;
//                     break;
//                 }
//             }
//         }
//     }


//     // private void verticalCollisions(ArrayList<Element> elements) {
//     //     onGround = false;

//     //     for (Element e : elements) {
//     //         if (e == null || "1".equals(e.getType())) continue;
//     //         if (collides(e)) {
//     //             if (vy > 0) { // falling → land on top
//     //                 setBottom(e.getTop());
//     //                 vy = 0;
//     //                 onGround = true;
//     //             } else if (vy < 0) { // jumping → hit ceiling
//     //                 setTop(e.getBottom());
//     //                 vy = 0;
//     //             }
//     //         }
//     //     }
//     // }


//     private boolean collides(Element e) {
//         return !(getRight() <= e.getLeft() ||
//                  getLeft()  >= e.getRight() ||
//                  getBottom() <= e.getTop() ||
//                  getTop()    >= e.getBottom());
//     }
    
//     public void handleExit(){
//         //nextLevel()
//     }

//     // public void handlePoison(){
//     //     circle.setCenter(startX, startY);
//     // }

//     ////SETTER METHODS

//     public void setRight(double newR){
//         this.setCenter(newR - width/2, this.getY());
//     }

//     public void setLeft(double newL){
//         this.setCenter(newL + width/2, this.getY());
//     }

//     public void setTop(double newT){
//         this.setCenter(this.getX(), newT + height/2);
//     }

//     public void setBottom(double newB){
//         this.setCenter(this.getX(), newB - height/2);
//     }

//     /////GETTER METHODS

//     public double getRight(){
//         return this.getCenter().getX() + this.getWidth()/2;
//     }

//     public double getLeft(){
//         return this.getCenter().getX() - this.getWidth()/2;
//     }

//     public double getTop(){
//         return this.getCenter().getY() - this.getHeight()/2;
//     }

//     public double getBottom(){
//         return this.getCenter().getY() + this.getHeight()/2;
//     }
// }
