import java.awt.Graphics2D;
import java.awt.Image;

/**
 * <code>BrainWashed</code> class is a subclass of <code>TerroristDecorators</code> class.
 * <code>BrainWashed</code> class is one of the decorators of <code>Terrorist</code> class.
 * Once a <code>Terrorist</code> decorated with <code>BrainWashed</code> class, a brain image is drawn 
 * to the left of the <code>Terrorist</code> decorated.
 * If a <code>RecentlyDeceivedTerrorist</code> reaches a <code>Camp</code> it is decorated with this class.
 * 
 * @see Terrorist
 * @see TerroristDecorator
 * @see Entity
 * @see RecentlyDeceivedTerrorist
 * @see Camp
 */
public class BrainWashed extends TerroristDecorator {

    
    private final Image brain;
    
    /**
     * Initializes a <code>BrainWashed</code> instance.
     * @param t <code>Terrorist</code> instance to be decorated.
     */
    public BrainWashed(Terrorist t) {
        super(t);
        decorated = t;
        this.strategy = t.strategy;
        this.brain = AllProperties.IMAGE_BRAIN;

    }



    /**
     * First draws <code>decorated</code>, later
     * draws the <code>BrainWashed</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here
        decorated.draw(g2d);
        
        int iks = (int) this.position.x-this.imageSize/2 - this.brain.getWidth(null) ;
        g2d.drawImage(this.brain,iks,(int)this.position.y - this.imageSize/2,null);
    }
}