//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation is a decorator to gain back the reaction to key-press behavior.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * create the KeyPressStoppableAnimation by the key, the animation that will be run and the KeyboardSensor.
     *
     * @param sensor    - the Keyboard Sensor
     * @param key       - the key that pressed
     * @param animation - the animation that should be run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
        this.stop = false;

    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic of the animation in one frame.
     *
     * @param d - the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {

            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }

/*
        animation.doOneFrame(d);
        if(this.sensor.isPressed(this.key)){

            if(!this.isAlreadyPressed){
                this.stop = true;
            }
            else{
                this.isAlreadyPressed = false;
            }
        }

 */
    }

    /**
     * shouldStop() is in charge of stopping condition.
     *
     * @return true if it should stop, otherwise - false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}
