import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * <code>Camp</code>s are where the <code>RecentlyDeceivedTerrorist</code> are decorated with <code>BrainWashed</code>. 
 * Only 3 <code>Camp</code>s are located in the game map.
 * @see BrainWashed
 */
public class Camp extends Entity {

    /**
     * Initializes a new <code>Camp</code> instance with default values.
     * @param location Location indicator of a <code>Camp</code>. 0 for Syria, 1 for Iraq and 2 for Georgia.
     */
    public Camp(int location) {
        this.color = new Color(11,160,16);//KOYU YESIL
        this.name = "Camp";
        this.speed = 0;
        this.image = null;
        this.strategy = null;
        switch(location){
            case 0:
                this.position = new Position(AllProperties.MAP_WIDTH*7/12 ,AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT*10/12 ); //SURIYE
                this.range = 100; break;
            case 1:
                this.position = new Position(AllProperties.MAP_WIDTH*10/12, AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT*10/12 ); //IRAK
                this.range = 120; break;
            case 2:
                this.position = new Position(AllProperties.MAP_WIDTH*11/12, AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT*2/12 ); //GURCISTAN
                this.range = 90; break;
            default:
                break;
                
        }
    }

    /**
     * Draws the <code>Camp</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.blue);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.drawString(this.name,(int)this.position.x-width/2,(int)this.position.y + 3);
        
        g2d.setColor(new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),90));
        g2d.fillOval((int)this.position.x-this.range/2,(int) this.position.y-this.range/2, this.range, this.range);
        
        g2d.setColor(this.color);
        g2d.drawOval((int)this.position.x-this.range/2,(int) this.position.y-this.range/2, this.range, this.range);
        
    }

    @Override
    public void step(double deltaTime) {
        
    }

}