import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * <code>Equipped</code> class is a subclass of <code>TerroristDecorators</code> class.
 * <code>Equipped</code> class is one of the decorators of <code>Terrorist</code> class.
 * Once a <code>Terrorist</code> decorated with <code>Equipped</code> class, a bomb image is drawn 
 * to the right of the <code>Terrorist</code> decorated.
 * If a <code>BrainWashed</code> reaches a <code>SupplyStorage</code> it is decorated with this class.
 * 
 * @see Terrorist
 * @see TerroristDecorator
 * @see Entity
 * @see BrainWashed
 * @see SupplyStorage
 */
public class Equipped extends TerroristDecorator {

    //private Terrorist decorated;
    private final Image bomb;
    
    /**
     * Initializes a <code>Equipped</code> instance.
     * @param t <code>Terrorist</code> instance to be decorated.
     */
    public Equipped(Terrorist t) {
        super(t);
        decorated = t;
        this.strategy = t.strategy;
        this.bomb = AllProperties.IMAGE_BOMB;
    }

    /**
     * First draws <code>decorated</code>, later
     * draws the <code>Equppied</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here
        decorated.draw(g2d);
        
        g2d.setColor(new Color(255,0,0,40));
        g2d.fillOval((int)this.position.x-this.range/2,(int) this.position.y-this.range/2, this.range, this.range);
        
        
        int iks = (int) this.position.x + this.imageSize - this.bomb.getWidth(null) ;
        g2d.drawImage(this.bomb,iks,(int)this.position.y - this.imageSize/2,null);
    }

}