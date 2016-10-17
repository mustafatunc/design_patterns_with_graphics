import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
* <code>SupplyStorage</code>s are where the <code>BrainWashed</code> are decorated with <code>Equipped</code>. 
* Only 3 <code>SupplyStorage</code>s are located in the game map.
* @see Equipped
*/
public class SupplyStorage extends Entity {

    /**
     * Initializes a new <code>SupplyStorage</code> instance with default values.
     * @param location Location indicator of a <code>SupplyStorage</code>. 0 for Istanbul, 1 for Konya and 2 for Van.
     */
    public SupplyStorage(int location) {
        super();
        this.color = new Color(185,50,50);//DEGISIK BI KIRMIZI
        this.name = "Storage";
        this.speed = 0;
        this.image = null;
        this.strategy = null;
        switch(location){
            case 0:
                this.position = new Position(AllProperties.MAP_WIDTH*3/12 ,AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT*3/12 ); //ISTANBUL
                this.range = 90; break;
            case 1:
                this.position = new Position(AllProperties.MAP_WIDTH*4/12, AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT*8/12 ); //KONYA
                this.range = 95; break;
            case 2:
                this.position = new Position(AllProperties.MAP_WIDTH*10/12,AllProperties.PANEL_HEIGHT+ AllProperties.MAP_HEIGHT*6/12 ); //VAN'IN ORALAR
                this.range = 110; break;
            default:
                break;
                
        }
    }

    /**
     * Draws the <code>SupplyStorage</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
     
        g2d.setColor(Color.blue);
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        g2d.drawString(this.name,(int)this.position.x-width/2,(int)this.position.y + 4);
        
        g2d.setColor(new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),90));
        g2d.fillOval((int)this.position.x-this.range/2,(int) this.position.y-this.range/2, this.range, this.range);
        
        g2d.setColor(this.color);
        g2d.drawOval((int)this.position.x-this.range/2,(int) this.position.y-this.range/2, this.range, this.range);
        
    }

    @Override
    public void step(double deltaTime) {
        
    }

}