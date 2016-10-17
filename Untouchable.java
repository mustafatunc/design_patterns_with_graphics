import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * <code>Untouchable</code> class is a subclass of <code>Entity</code> class.
 * In the drawing pane all <code>Agent</code>s have rectangle indicating their range.
 * Color of the rectangle is Black. There are only 2 <code>Untouchable</code>s have an <code>Image</code> object
 * at the center of its rectangle.
 * <code>Untouchable</code> moves around the pane and nothing affects them. It says some known phrases of them in a time interval.
 * @see Entity
 */
public class Untouchable extends Entity {

    private Random rand = new Random();
    private boolean doITalk = false;
    private int word = 0;
    String[] words = new String[]{
        "Touch This!", "Hmm Upgrades..", "You think that's air..", "Only Human!", "Tank, load the jump program",
        "Good bye, Mr Anderson!",
        "Your weakness is not your technique", "How did I beat you?", "Don't think you are, know you are", 
        "Hit me, if you can..","I know Kung Fu"
    };
    /**
     * Initializes a new <code>Untouchable</code>.
     * @param type type of image. 0 for Untouchable1, for 1 Untouchable2.
     */
    public Untouchable(int type) {
        super(Color.BLACK,10.0d,new Position());
        this.name = "Untouchable";
        this.range = 80;
        this.strategy = new MoveRandom(2);
        try {
            if (type == 0){
                this.image = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Untouchable1.gif"))
                        .getScaledInstance(this.range, this.range, Image.SCALE_SMOOTH);
                enableActionListener();
            }
            else if (type ==1){
                this.image = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Untouchable2.gif"))
                        .getScaledInstance(this.range, this.range, Image.SCALE_SMOOTH);
                enableActionListener();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    
    
    /**
     * Draws the <code>Untouchable</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect((int)this.position.x, (int)this.position.y, this.range, this.range);
        g2d.drawImage(image, (int) this.position.x, (int)this.position.y,null);
        g2d.setColor(Color.BLUE);
        int width = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 15));
        g2d.drawString(name, (int)this.position.x+this.range/2-width/2, (int)this.position.y-5 );
        width = g2d.getFontMetrics().stringWidth(this.strategy.getName()+" "+this.strategy.numberOfTurns);
        g2d.setColor(Color.RED);
        g2d.drawString(this.strategy.getName()+" "+this.strategy.numberOfTurns,
                        (int) this.position.x +this.range/2 -width/2, 
                           (int) this.position.y + this.range + 15);
        
        if(doITalk){
            g2d.setColor(Color.BLACK);
            g2d.drawString(words[word], (int) this.position.x+this.range+5, (int)this.position.y+15);
        }
        
        
    }

    private void enableActionListener() {
        ActionListener timerlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doITalk = !doITalk;
                word = rand.nextInt(10);
            }
        };
        Timer timer = new Timer(2500+(rand.nextInt(1000)), timerlistener);
        timer.start();
        
    }
    

}