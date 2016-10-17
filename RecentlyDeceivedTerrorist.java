import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * <code>RecentlyDeceivedTerrorist</code> class is a subclass of <code>Terrorist</code> class.
 * In the drawing pane all <code>RecentlyDeceivedTerrorist</code>s have dashed circle indicating their range.
 * Color of the dashed circle is Red. All <code>RecentlyDeceivedTerrorist</code>s have an <code>Image</code> object
 * at the center of its circle. * 
 * @see Entity
 * @see Terrorist
 */
public class RecentlyDeceivedTerrorist extends Terrorist{

    
    /**
     * Initializes a new <code>RecentlyDeceivedTerrorist</code> instance with default values.
     * @param color color of Terrorist
     * @param speed speed of Terrorist
     * @param position position of Terrorist
     */
    public RecentlyDeceivedTerrorist(Color color, double speed, Position position) {
        super(color,speed,position);
        this.strategy = new StandStill(10);
    }

    /**
     * Draws the <code>RecentlyDeceivedTerrorist</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        
        g2d.setColor(this.color);
        Stroke dashed = new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND, 0 ,new float[]{10},0);
        g2d.setStroke(dashed);
        g2d.drawOval((int) this.position.x-this.range/2, (int)this.position.y-this.range/2,this.range,this.range);
        g2d.drawImage(image, (int) this.position.x-this.imageSize/2, (int)this.position.y-this.imageSize/2,null);
        
        g2d.setColor(Color.BLUE);
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        g2d.drawString(this.name,(int) this.position.x-width/2, (int) this.position.y-this.range/2 -5); 
        
        // STRATEJI YAZACAZ
        width = g2d.getFontMetrics().stringWidth(this.strategy.getName());
        g2d.setColor(Color.RED);
        g2d.drawString(this.strategy.getName(),
                       (int) this.position.x  -width/2, 
                       (int) this.position.y +this.image.getWidth(null)/4+3);
        
        //NUMBERoFtURNS YAZACAK
        width = g2d.getFontMetrics().stringWidth(this.strategy.numberOfTurns+"");
        g2d.drawString(""+this.strategy.numberOfTurns,
                       (int) this.position.x  - width/2, 
                       (int) this.position.y +this.image.getWidth(null)/4 + 30);
        
    }
}