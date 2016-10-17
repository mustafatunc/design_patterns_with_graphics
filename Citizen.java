import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;


/**
 * <code>Citizen</code> class is a subclass of <code>Entity</code> class.
 * In the drawing pane all <code>Citizen</code>s are filled circles with different ranges.
 * @see Entity
 */
public class Citizen extends Entity {

    /**
     * Initializes a new <code>Citizen</code> with random values.
     * @param color color of Citizen
     * @param speed speed of Citizen
     * @param position position of Citizen
     */
    public Citizen(Color color, double speed, Position position) {
        this.color = color;
        this.name = "Citizen";
        this.speed = speed;
        this.position = position;
        this.range = new Random().nextInt(15)+10;
        this.strategy = new MoveLinear(5);
    }

    
    /**
     * Draws the <code>Citizen</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.fillOval((int) this.position.x, (int)this.position.y,this.range,this.range);
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.drawString(this.name,(int) this.position.x +this.range/2 -width/2, (int) this.position.y - 5);
        width = g2d.getFontMetrics().stringWidth(this.strategy.getName());
        g2d.setColor(Color.RED);
        g2d.drawString(this.strategy.getName(),(int) this.position.x +this.range/2 -width/2, (int) this.position.y + this.range + 15);
    }

}