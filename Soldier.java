import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;


/**
 * <code>Soldier</code> class is a subclass of <code>Entity</code> class.
 * In the drawing pane all <code>Soldier</code>s have dashed circle indicating their range.
 * Color of the dashed circle is Green. All <code>Soldier</code>s have an <code>Image</code> object
 * at the center of its circle.
 * <code>Soldier</code> moves around the pane and if a <code>Terrorist</code> is in its range,
 * the <code>Soldier</code> kills that <code>Terrorist</code>.
 * @see Entity
 * @see Terrorist
 */
public class Soldier extends Entity {

    
    /**
     * Initializes a new <code>Soldier</code> instance with default values.
     * @param color color of Soldier
     * @param speed speed of Soldier
     * @param position position of Soldier
     */
    public Soldier(Color color, double speed, Position position) {
        super(color,speed,position);
        this.name = "Soldier";
        this.range = AllProperties.RANGE_SOLDIER;
        this.strategy = new Patrol(3,position);
        this.image = AllProperties.IMAGE_SOLDIER;
    }

    
    
    /**
     * Draws the <code>Soldier</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        
        // CEMBER VE RESIM
        g2d.setColor(this.color);
        Stroke dashed = new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND, 0 ,new float[]{10},0);
        g2d.setStroke(dashed);
        g2d.drawOval((int) this.position.x-this.range/2, (int)this.position.y-this.range/2,this.range,this.range);
        g2d.drawImage(image, (int) this.position.x-this.imageSize/2, (int)this.position.y-this.imageSize/2,null);
        
        // ISIM YAZACAK
        g2d.setColor(Color.BLUE);
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        g2d.drawString(this.name,(int) this.position.x - width/2, (int) this.position.y-this.range/2 -5);
        
        // STRATEJI YAZACAK
        g2d.setColor(Color.RED);
        width = g2d.getFontMetrics().stringWidth(this.strategy.getName()+" "+this.strategy.numberOfTurns);
        g2d.drawString(this.strategy.getName()+" "+this.strategy.numberOfTurns,
                        (int) this.position.x - width/2,
                        (int) this.position.y + this.range/2 + 15);
        
    }

}