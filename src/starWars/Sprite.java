package starWars;

import edu.macalester.graphics.*;
import java.util.ArrayList;

public class Sprite extends GraphicsGroup {
    // physics
    private final double RUN_ACCEL = 0.6;
    private final double RUN_DECEL = 0.5;
    private final double AIR_ACCEL = 0.2;
    private final double MAX_RUN = 4.0;

    private final double GRAVITY_UP = 0.35;
    private final double GRAVITY_DOWN = 0.575;
    private final double MAX_FALL = 10;

    private final double JUMP_VEL = -9;
    private final double COYOTE_TIME = 0.12;
    private final double JUMP_BUFFER = 0.12;

    public double vx = 0;
    public double vy = 0;

    // timing
    private double coyoteTimer = 0;
    private double jumpBufferTimer = 0;

    // states
    public boolean movingLeft = false;
    public boolean movingRight = false;
    public boolean jumpPressed = false;
    public boolean jumpReleased = false;
    public boolean onGround = false;

    // attributes
    private double width, height;
    private double maxHeight;
    private Image forwardChar;
    private Image leftChar;
    private Image rightChar;
    private Image sLChar;
    private Image sRChar;
    private int num;
    private String imageName;

    public Sprite(String imageName, double scale, double x, double y, int num, double maxHeight) {
        this.num = num;
        this.imageName = imageName;
        this.maxHeight = maxHeight;

        setup();

        setCenter(x, y);

        movingLeft = false;
        movingRight = false;
    }

    /**
     * Sets up two characters
     */
    public void setup(){
        String path = imageName + "Forward.png";
        String pathL = imageName + "L.png";
        String pathR = imageName + "R.png";
        String pathSR = imageName + "SR.png";
        String pathSL = imageName + "SL.png";

        leftChar = new Image(pathL);
        rightChar = new Image(pathR);

        sLChar = new Image(pathSL);
        sRChar = new Image(pathSR);

        forwardChar = new Image(path);
        forwardChar.setMaxHeight(maxHeight);
        add(forwardChar);

        width = forwardChar.getWidth();
        height = forwardChar.getHeight();
    }

    /**
     * Handles sprite movement
     */
    public void move(CanvasWindow canvas, ArrayList<Element> elements) {
        // horizontal
        double accel;

        if (onGround) {
            accel = RUN_ACCEL;
        } else {
            accel = AIR_ACCEL;
        }

        if (movingLeft)  vx -= accel;
        if (movingRight) vx += accel;

        if (!movingLeft && !movingRight) {
            if (onGround) {
                if (vx > 0) vx = Math.max(0, vx - RUN_DECEL);
                if (vx < 0) vx = Math.min(0, vx + RUN_DECEL);
            }
            else {
                vx *= 0.98;
            }
        }

        if (vx > MAX_RUN) vx = MAX_RUN;
        if (vx < -MAX_RUN) vx = -MAX_RUN;

        resolveHorizontal(elements, vx);

        // vertical
        if (vy < 0){
            vy += GRAVITY_UP;
        } else {
            vy += GRAVITY_DOWN;
        }        

        if (vy > MAX_FALL) vy = MAX_FALL;

        if (onGround){
            coyoteTimer = COYOTE_TIME;
        } else {
            coyoteTimer -= 1.0 / 60.0;
        }         

        jumpBufferTimer -= 1.0 / 60.0;

        if (jumpBufferTimer > 0 && coyoteTimer > 0) {
            vy = JUMP_VEL;
            onGround = false;
            jumpBufferTimer = 0;
        }

        if (jumpReleased && vy < 0) {
            vy *= 0.5;
        }

        jumpReleased = false;

        resolveVertical(elements, vy);

        checkBoundaries(canvas);
    }

    /**
     * Called when key is pressed
     */
    public void startMoving(String key) {
        if (this.num == 2){
            if (key.equals("LEFT_ARROW")){
                Image image = (Image) getElementAt(getX(), getY());
                if (image.equals(leftChar)){
                    movingLeft = true;
                } else {
                    removeAll();
                    movingLeft = true;

                    leftChar.setMaxHeight(35);
                    width = leftChar.getWidth();
                    height = leftChar.getHeight();
                    add(leftChar);
                }
            }
            if (key.equals("RIGHT_ARROW")){
                Image image = (Image) getElementAt(getX(), getY());
                if (image.equals(rightChar)){
                    movingRight = true;
                } else {
                    removeAll();
                    movingRight = true;

                    rightChar.setMaxHeight(35);
                    width = rightChar.getWidth();
                    height = rightChar.getHeight();
                    add(rightChar);
                }
            } 

            if (key.equals("UP_ARROW")) {
                jumpPressed = true;
                jumpBufferTimer = JUMP_BUFFER;
            }
        } else {
        if (key.equals("A")){
                Image image = (Image) getElementAt(getX(), getY());
                if (image.equals(leftChar)){
                    movingLeft = true;
                } else {
                    removeAll();
                    movingLeft = true;

                    leftChar.setMaxHeight(35);
                    width = leftChar.getWidth();
                    height = leftChar.getHeight();
                    add(leftChar);
                }
            }
            if (key.equals("D")){
                Image image = (Image) getElementAt(getX(), getY());
                if (image.equals(rightChar)){
                    movingRight = true;
                } else {
                    removeAll();
                    movingRight = true;

                    rightChar.setMaxHeight(35);
                    width = rightChar.getWidth();
                    height = rightChar.getHeight();
                    add(rightChar);
                }
            } 

            if (key.equals("W")) {
                jumpPressed = true;
                jumpBufferTimer = JUMP_BUFFER;
            }
        }
    }

    /**
     * Called when key is released
     */
    public void stopMoving(String key) {
        if (this.num == 2){   
            if (key.equals("LEFT_ARROW")){
                    removeAll();
                    movingLeft = false;

                    sLChar.setMaxHeight(35);
                    width = sLChar.getWidth();
                    height = sLChar.getHeight();
                    add(sLChar);
            }
            if (key.equals("RIGHT_ARROW")){
                    removeAll();
                    movingRight = false;

                    sRChar.setMaxHeight(35);
                    width = sRChar.getWidth();
                    height = sRChar.getHeight();
                    add(sRChar);
            }
            if (key.equals("UP_ARROW") || key.equals("W")) {
                jumpReleased = true;
            }
        } else {
            if (key.equals("A")){
                    removeAll();
                    movingLeft = false;

                    sLChar.setMaxHeight(35);
                    width = sLChar.getWidth();
                    height = sLChar.getHeight();
                    add(sLChar);
            }
            if (key.equals("D")){
                    removeAll();
                    movingRight = false;

                    sRChar.setMaxHeight(35);
                    width = sRChar.getWidth();
                    height = sRChar.getHeight();
                    add(sRChar);
            }
            if (key.equals("W")) {
                jumpReleased = true;
            }
        }
    }

    /**
     * Handles horizontal collisions
     */
    private void resolveHorizontal(ArrayList<Element> elements, double dx) {
        if (dx == 0) return;

        double nextLeft  = getLeft() + dx;
        double nextRight = getRight() + dx;

        for (Element e : elements) {
            if (e == null) continue;
            if (e.getType().equals("1")) continue;

            boolean overlapY = !(getBottom() <= e.getTop() || getTop() >= e.getBottom());
            if (!overlapY) continue;

            if (dx > 0 && nextRight > e.getLeft() && getRight() <= e.getLeft()) {
                setCenter(e.getLeft() - width/2, getCenter().getY());
                vx = 0;
                return;
            }

            if (dx < 0 && nextLeft < e.getRight() && getLeft() >= e.getRight()) {
                setCenter(e.getRight() + width/2, getCenter().getY());
                vx = 0;
                return;
            }
        }

        moveBy(dx, 0);
    }

    /**
     * Handles vertical collisions
     */
    private void resolveVertical(ArrayList<Element> elements, double dy) {
        if (dy == 0) return;

        double nextTop = getTop() + dy;
        double nextBottom = getBottom() + dy;

        onGround = false;

        for (Element e : elements) {
            if (e == null) continue;
            if (e.getType().equals("1")) continue;

            boolean overlapX = !(getRight() <= e.getLeft() || getLeft() >= e.getRight());
            if (!overlapX) continue;

            if (dy > 0 && nextBottom > e.getTop() && getBottom() <= e.getTop()) {
                setCenter(getCenter().getX(), e.getTop() - height/2);
                vy = 0;
                onGround = true;
                return;
            }

            if (dy < 0 && nextTop < e.getBottom() && getTop() >= e.getBottom()) {
                setCenter(getCenter().getX(), e.getBottom() + height/2);
                vy = 0;
                return;
            }
        }

        moveBy(0, dy);
    }

    /**
     * Returns left x of sprite
     */
    private void checkBoundaries(CanvasWindow canvas){
        double left = getLeft();
        double right = getRight();
        double top = getTop();
        double bottom = getBottom();

        double winW = canvas.getWidth();
        double winH = canvas.getHeight();

        // left wall
        if (left < 0) {
            setCenter(width / 2, getCenter().getY());
            vx = 0;
        }

        // right wall
        if (right > winW) {
            setCenter(winW - width / 2, getCenter().getY());
            vx = 0;
        }

        // top wall
        if (top < 0) {
            setCenter(getCenter().getX(), height / 2);
            vy = 0;
        }

        // bottom wall
        if (bottom > winH) {
            setCenter(getCenter().getX(), winH - height / 2);
            vy = 0;
            onGround = true;
        }
    }


    /**
     * Returns left x of sprite
     */
    public double getLeft()  { return getCenter().getX() - width/2; }
    /**
     * Returns right x of sprite
     */
    public double getRight() { return getCenter().getX() + width/2; }
    /**
     * Returns top y of sprite
     */
    public double getTop()   { return getCenter().getY() - height/2; }
    /**
     * Returns bottom y of sprite
     */
    public double getBottom(){ return getCenter().getY() + height/2; }
}
