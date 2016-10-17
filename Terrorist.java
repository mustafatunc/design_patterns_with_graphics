import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <code>Terrorist</code> class is a subclass of <code>Entity</code> class.
 * In the drawing pane all <code>Terrorist</code>s have dashed circle indicating their range.
 * Color of the dashed circle is Red. All <code>Terrorist</code>s have an <code>Image</code> object
 * at the center of its circle.
 * <code>Terrorist</code> moves around the pane and if a <code>Soldier</code> or <code>Agent</code> is in its range,
 * the <code>Terrorist</code> is caught. 
 * @see Entity
 * @see Soldier
 * @see Agent
 */
public abstract class Terrorist extends Entity {

    /**
     * Initializes a new <code>Terrorist</code> instance with default values.
     * @param color color of Terrorist
     * @param speed speed of Terrorist
     * @param position position of Terrorist
     */
    public Terrorist(Color color, double speed, Position position) {
        this.color = color;
        this.speed = speed;
        this.position = position;
        this.name = "Terrorist";
        this.range = AllProperties.RANGE_TERRORIST;
        this.image = AllProperties.IMAGE_TERRORIST;
    }

    /**
     * Gets the strategy of the <code>Terrorist</code>
     * 
     * @return Returns strategy of the <code>Terrorist</code>
     */
    public StepStrategy getStrategy() {
        return strategy;
    }

    
    
    @Override
    public abstract void draw(Graphics2D g2d);

}