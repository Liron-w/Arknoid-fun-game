//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 * It implement the Sprite and the Collidable interfaces.
 * It also know how to move to the left and to the right.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private geometry.Rectangle paddleRectangle;
    private Color color;
    private int rightBorder;
    private int leftBorder;
    private int step;

    //constructors:

    /**
     * constructor that create the paddle by a rectangle and color and color, the borders and the keyboard of the game.
     *
     * @param paddleRectangle - the rectangle of the paddle
     * @param color           - the color of the paddle
     * @param keyboard        - the keyboard the the paddle use with.
     */
    public Paddle(geometry.Rectangle paddleRectangle, Color color, biuoop.KeyboardSensor keyboard) {
        this.paddleRectangle = paddleRectangle;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * constructor that create the paddle by a rectangle and color and color, the borders and the keyboard of the game.
     *
     * @param upperLeft   - the upper left point of the rectangle of the paddle
     * @param width       - the width of the paddle
     * @param height      - the height of the paddle
     * @param color       - the color of the paddle
     * @param keyboard    - the keyboard the the paddle use with.
     * @param step - the velocity of the paddle
     */
    public Paddle(geometry.Point upperLeft, double width, double height, int step, Color color,
                  biuoop.KeyboardSensor keyboard) {
        this.color = color;
        this.paddleRectangle = new geometry.Rectangle(upperLeft, width, height);
        this.keyboard = keyboard;
        this.leftBorder = 20;
        this.rightBorder = 780;
        this.step = step;
    }

    /**
     * The function moves the paddle to the left if he presses the left key,
     * but makes sure he does not cross the boundaries.
     */
    public void moveLeft() {
        double upperLeftX = this.paddleRectangle.getUpperLeft().getX();
        double upperLeftY = this.paddleRectangle.getUpperLeft().getY();
        double width = this.paddleRectangle.getWidth();
        double height = this.paddleRectangle.getHeight();
        if (upperLeftX - step >= this.leftBorder) {
            this.paddleRectangle = new geometry.Rectangle(new geometry.Point(upperLeftX - step, upperLeftY),
                    width, height);
        } else { // we arrive to the border
            this.paddleRectangle = new geometry.Rectangle(new geometry.Point(this.leftBorder, upperLeftY),
                    width, height);
        }
    }

    /**
     * The function moves the paddle to the right if he presses the right key,
     * but makes sure he does not cross the boundaries.
     */
    public void moveRight() {
        double upperLeftX = this.paddleRectangle.getUpperLeft().getX();
        double upperLeftY = this.paddleRectangle.getUpperLeft().getY();
        double width = this.paddleRectangle.getWidth();
        double height = this.paddleRectangle.getHeight();
        if (upperLeftX + width + step <= this.rightBorder) {
            this.paddleRectangle = new geometry.Rectangle(new geometry.Point(upperLeftX + step, upperLeftY),
                    width, height);
        } else { // we arrive to the border
            this.paddleRectangle = new geometry.Rectangle(new geometry.Point(this.rightBorder - width, upperLeftY),
                    width, height);
        }
    }

    /**
     * timePassed method should check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * this method draw the paddle on the draw surface.
     *
     * @param d - the draw surface
     */
    public void drawOn(DrawSurface d) {
        if (this.color == null) {
            this.color = Color.BLACK;
        }
        d.setColor(this.color);
        d.fillRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(), (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(), (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
    }


    /**
     * this method return the paddle rectangle if there is a collision with it.
     *
     * @return the paddle rectangle.
     */
    public geometry.Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * the method otify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point between the paddle and the ball
     * @param currentVelocity - the current velocity of the ball
     * @param hitter - the ball that make the hitting
     * @return the new velocity expected after the hit
     */
    public Velocity hit(Ball hitter, geometry.Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        geometry.Point upperLeft = this.paddleRectangle.getUpperLeft();
        geometry.Point upperRight = new geometry.Point(this.paddleRectangle.getUpperLeft().getX()
                + this.paddleRectangle.getWidth(), this.paddleRectangle.getUpperLeft().getY());
        geometry.Point bottomLeft = new geometry.Point(this.paddleRectangle.getUpperLeft().getX(),
                this.paddleRectangle.getUpperLeft().getY() + this.paddleRectangle.getHeight());
        geometry.Point bottomRight = new geometry.Point(this.paddleRectangle.getUpperLeft().getX()
                + this.paddleRectangle.getWidth(), this.paddleRectangle.getUpperLeft().getY()
                + this.paddleRectangle.getHeight());

        //the line of the rectangle of the paddle
        Line up = new Line(upperLeft, upperRight);
        Line bottom = new Line(bottomLeft, bottomRight);
        Line left = new Line(upperLeft, bottomLeft);
        Line right = new Line(bottomRight, upperRight);
        //if the collision point is on one of the corners - the velocity should changed in the both directions
        if (left.isThePointOnTheLine(collisionPoint) || right.isThePointOnTheLine(collisionPoint)) {
            currentVelocity.setVelocity(new Velocity(-1 * dx, dy));
            return currentVelocity;
        }

        int numOfDivision = 5;
        int i;
        Line[] upLines = new Line[numOfDivision];
        for (i = 0; i < numOfDivision; i++) {
            double xUpLeftDividing = this.paddleRectangle.getUpperLeft().getX()
                    + i * (this.paddleRectangle.getWidth() / numOfDivision);
            double yUpLeftDividing = this.paddleRectangle.getUpperLeft().getY();
            geometry.Point upLeftDividing = new geometry.Point(xUpLeftDividing, yUpLeftDividing);
            geometry.Point upRightDividing = new Point(xUpLeftDividing + (
                    this.paddleRectangle.getWidth() / numOfDivision),
                    yUpLeftDividing);
            upLines[i] = new Line(upLeftDividing, upRightDividing);
            if (upLines[i].isThePointOnTheLine(collisionPoint)) {
                break;
            }
        }
        int angle = 0;
        if (i == 0) {
            angle = 300;
        } else if (i == 1) {
            angle = 330;
        } else if (i == 2) {
            return new Velocity(dx, -1 * dy);
        } else if (i == 3) {
            angle = 30;
        } else if (i == 4) {
            angle = 60;
        }
        double speed = currentVelocity.getSpeed();
        currentVelocity.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }


}
