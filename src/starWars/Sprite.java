package starWars;

import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;

public class Sprite extends GraphicsGroup {
    private final double runSpeed = 5;
    private final double gravity = 0.5;
    private final double jumpStrength = -9.8;
    private final double maxFallSpeed = 10;
    private final double groundY = 710;

    public double vx = 0;
    public double vy = 0;

    public boolean onGround = false;
    private boolean movingLeft, movingRight;

    private double width, height;

    // keep a visible debug shape so width/height are well-defined
    private Ellipse debug;

    public Sprite(String imageFile, double scale, double x, double y) {
        debug = new Ellipse(0, 0, 20, 20);
        debug.setFillColor(Color.RED);
        add(debug);

        // width/height used by collision math (match debug size)
        width = debug.getWidth();
        height = debug.getHeight();

        setCenter(x, y);

        movingLeft = false;
        movingRight = false;
    }

    // Main move function: call this from Sketch.animate: p.move(canvas, elements);
    public void move(CanvasWindow canvas, ArrayList<Element> elements) {
        // Horizontal intent from input flags
        double dx = 0;
        if (movingLeft)  dx -= runSpeed;
        if (movingRight) dx += runSpeed;
        vx = dx;

        // Predictive horizontal resolution (commits movement if allowed)
        resolveHorizontal(elements, dx);

        // Vertical physics
        vy += gravity;
        if (vy > maxFallSpeed) vy = maxFallSpeed;
        // Predictive vertical resolution
        resolveVertical(elements, vy);

        if (getY() >= groundY){
          setY(groundY);
          vy = 0;
          onGround = true;
      }
    }

    // Call when key pressed
    public void startMoving(String key) {
        if (key.equals("UP_ARROW") || key.equals("W")) {
            if (onGround) {
                vy = jumpStrength;
                onGround = false;
            }
        }
        if (key.equals("DOWN_ARROW") || key.equals("S")) {
            if (vy < 0) vy = 0;
        }
        if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = true;
        if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = true;
    }

    // Call when key released
    public void stopMoving(String key) {
        if (key.equals("LEFT_ARROW") || key.equals("A")) movingLeft = false;
        if (key.equals("RIGHT_ARROW") || key.equals("D")) movingRight = false;
    }

    // Predictive horizontal collision resolution
    private void resolveHorizontal(ArrayList<Element> elements, double dx) {
        if (dx == 0) return;

        double nextLeft  = getLeft() + dx;
        double nextRight = getRight() + dx;

        for (Element e : elements) {
            if (e == null) continue;
            if (e.getType().equals("1")) continue; // skip background

            // Only consider elements that vertically overlap the sprite right now
            boolean overlapY = !(getBottom() <= e.getTop() || getTop() >= e.getBottom());
            if (!overlapY) continue;

            // moving right and would cross into the block
            if (dx > 0 && nextRight > e.getLeft() && getRight() <= e.getLeft()) {
                // snap exactly to left edge
                setCenter(e.getLeft() - width/2, getCenter().getY());
                vx = 0;
                return;
            }

            // moving left and would cross into the block
            if (dx < 0 && nextLeft < e.getRight() && getLeft() >= e.getRight()) {
                setCenter(e.getRight() + width/2, getCenter().getY());
                vx = 0;
                return;
            }
        }

        // no blocking collision — commit horizontal movement
        moveBy(dx, 0);
    }

    // Predictive vertical collision resolution
    private void resolveVertical(ArrayList<Element> elements, double dy) {
        if (dy == 0) return;

        double nextTop = getTop() + dy;
        double nextBottom = getBottom() + dy;

        // assume not on ground; landing will set this true
        onGround = false;

        for (Element e : elements) {
            if (e == null) continue;
            if (e.getType().equals("1")) continue;

            // Only consider elements that horizontally overlap the sprite right now
            boolean overlapX = !(getRight() <= e.getLeft() || getLeft() >= e.getRight());
            if (!overlapX) continue;

            // Falling → landing
            if (dy > 0 && nextBottom > e.getTop() && getBottom() <= e.getTop()) {
                setCenter(getCenter().getX(), e.getTop() - height/2);
                vy = 0;
                onGround = true;
                return;
            }

            // Rising → hit ceiling
            if (dy < 0 && nextTop < e.getBottom() && getTop() >= e.getBottom()) {
                setCenter(getCenter().getX(), e.getBottom() + height/2);
                vy = 0;
                return;
            }
        }

        // no vertical collision → commit
        moveBy(0, dy);
    }

    private boolean overlaps(Element e) {
        return !(getRight() <= e.getLeft() ||
                 getLeft()  >= e.getRight() ||
                 getBottom() <= e.getTop() ||
                 getTop()    >= e.getBottom());
    }

    public double getLeft()  { return getCenter().getX() - width/2; }
    public double getRight() { return getCenter().getX() + width/2; }
    public double getTop()   { return getCenter().getY() - height/2; }
    public double getBottom(){ return getCenter().getY() + height/2; }
}
