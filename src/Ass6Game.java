//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */

import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import game.LevelOne;
import game.LevelTwo;
import game.LevelThree;
import game.LevelFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class support the running of the game.
 */
public class Ass6Game {
    /**
     * running the game.
     *
     * @param args - the arguments from the command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        List<LevelInformation> levels = getLevels(args);
        GameFlow gf = new GameFlow(ar, gui.getKeyboardSensor());
        gf.runLevels(levels);
        gui.close();
    }

    /**
     * return the levels that should be run according the arguments from the command line.
     *
     * @param args - the arguments from the command line.
     * @return - the levels that should be run
     */
    public static List<LevelInformation> getLevels(String[] args) {
        List<LevelInformation> l = new ArrayList<>();

        if (args.length == 0) {
            for (int b = 1; b <= 4; b++) {
                l.add(getLevel(Integer.toString(b)));
            }
            return l;
        }

        final List<String> names = Arrays.asList("1", "2", "3", "4");
        for (int i = 0; i < args.length; i++) {
            if (names.contains(args[i])) {
                LevelInformation tmp = getLevel(args[i]);
                if (tmp == null) {
                    continue;
                }
                l.add(tmp);
            }
            if (l.isEmpty()) {
                for (int b = 1; b <= 4; b++) {
                    l.add(getLevel(Integer.toString(b)));
                }
            }
        }
        return l;
    }

    /**
     * get the level by its number.
     *
     * @param str - the number of the level.
     * @return - the matching level.
     */
    public static LevelInformation getLevel(String str) {
        switch (str) {
            case "1":
                return new LevelOne();
            case "2":
                return new LevelTwo();
            case "3":
                return new LevelThree();
            case "4":
                return new LevelFour();
            default:
                return null;

        }
    }
}
