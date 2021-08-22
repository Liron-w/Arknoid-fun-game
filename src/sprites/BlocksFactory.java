//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */

package sprites;

import geometry.Point;
import interfaces.HitListener;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is factory of blocks.
 * we can create one block, and we can create row of blocks.
 */
public class BlocksFactory {

    //fields
    private Point upperRight;
    private double width;
    private double height;
    private HitListener hl;

    //constructor

    /**
     * constructor that create a new block from rectangle.
     *
     * @param upperRight - the upper right corner of the block
     * @param width      - the width of any block
     * @param height     - the height of any block
     * @param hl         - the hit listener.
     */
    public BlocksFactory(Point upperRight, double width, double height, HitListener hl) {
        this.upperRight = upperRight;
        this.width = width;
        this.height = height;
        this.hl = hl;
    }

    /**
     * constructor.
     * @param upperRight - up right point
     * @param width - width
     * @param height - height
     */
    public BlocksFactory(Point upperRight, double width, double height) {
        this.upperRight = upperRight;
        this.width = width;
        this.height = height;
    }

    /**
     * this method create one block.
     *
     * @param upperLeft - the upper left corner of the block
     * @param myWidth   - the width of the block
     * @param myHeight  - the height of the block
     * @param color     - the color of the block
     * @return a new block
     */
    public Block createBlock(Point upperLeft, double myWidth, double myHeight, java.awt.Color color) {
        Block b = new Block(upperLeft, myWidth, myHeight, color);
        //b.addHitListener(this.hl);
        return b;
    }

    /**
     * the method create row of blocks in the same color.
     *
     * @param numOfBlocks - the number of blocks in the row
     * @param color       - the color of the blocks in the row
     * @return a list of bocks (the row of the blocks)
     */
    public List<Block> createRowOfBlocks(int numOfBlocks, java.awt.Color color) {

        return createRowOfBlocks(this.upperRight, numOfBlocks, this.width, this.height, color);
    }

    /**
     * the method create row of blocks in the same color.
     *
     * @param upRight     - the upper right corner of the row of blocks
     * @param numOfBlocks - the number of the blocks in this row
     * @param myWidth     - the width of any block
     * @param myHeight    - the height of any block
     * @param color       - the color of the blocks in this row
     * @return a list of the blocks that in the row
     */
    public List<Block> createRowOfBlocks(Point upRight, int numOfBlocks, double myWidth, double myHeight,
                                         java.awt.Color color) {
        List<Block> blockList = new ArrayList<>();
        //Point firstUpperLeft = new Point(upperRight.getX()- width,upperRight.getY());
        for (int i = 1; i <= numOfBlocks; i++) {
            Point upperLeftPoint = new Point(upRight.getX() - i * myWidth, upRight.getY());
            blockList.add(this.createBlock(upperLeftPoint, myWidth, myHeight, color));

        }
        return blockList;
    }
}

