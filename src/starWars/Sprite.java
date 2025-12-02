package starWars;

import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;

public class Sprite extends GraphicsGroup {
    // physics
    private final double RUN_ACCEL = 0.6;
    private final double RUN_DECEL = 0.5;
    private final double AIR_ACCEL = 0.3;
    private final double MAX_RUN = 4.0;

    private final double GRAVITY_UP = 0.35;
    private final double GRAVITY_DOWN = 0.55;
    private final double MAX_FALL = 10;

    private final double JUMP_VEL = -8.5;
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
    private Ellipse debug;

    public Sprite(String imageFile, double scale, double x, double y) {
        debug = new Ellipse(0, 0, 20, 20);
        debug.setFillColor(Color.RED);
        add(debug);

        width = debug.getWidth(); // replace with image
        height = debug.getHeight(); // replace with image

        setCenter(x, y);

        movingLeft = false;
        movingRight = false;
    }

    public void move(CanvasWindow canvas, ArrayList<Element> elements) {
        // horizontal
        double accel = onGround ? RUN_ACCEL : AIR_ACCEL;

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
        if (vy < 0) vy += GRAVITY_UP;
        else        vy += GRAVITY_DOWN;

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
    }

    // called whenever key pressed
    public void startMoving(String key) {
        if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = true;
        if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = true;

        if (key.equals("UP_ARROW") || key.equals("W")) {
            jumpPressed = true;
            jumpBufferTimer = JUMP_BUFFER;
        }
    }

    // called whenever key pressed
    public void stopMoving(String key) {
        if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = false;
        if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = false;

        if (key.equals("UP_ARROW") || key.equals("W")) {
            jumpReleased = true;
        }
    }

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

        moveBy(vx, 0);
    }

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

        moveBy(0, vy);
    }

    // getter methods
    public double getLeft()  { return getCenter().getX() - width/2; }
    public double getRight() { return getCenter().getX() + width/2; }
    public double getTop()   { return getCenter().getY() - height/2; }
    public double getBottom(){ return getCenter().getY() + height/2; }
}
