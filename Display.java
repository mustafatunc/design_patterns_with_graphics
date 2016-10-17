import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * <code>Display</code> is the <code>JPanel</code> where all the drawings happen.
 */
public class Display extends JPanel implements KeyListener,MouseListener{

    
    public Image LOGO,MAP;
    public final Environment environment = new Environment();
    private int turn=0;
    private boolean isSonVisible = false;
    private int sonX=0,sonY=0;
    private Image SON;
    /**
     * Initializes a new <code>Display</code> instance with white background, a logo and a map.
     */
    public Display() {
        super();
        setBackground(Color.WHITE);
        try {
            LOGO = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Logo.gif"))
                    .getScaledInstance(AllProperties.PANEL_HEIGHT, AllProperties.PANEL_HEIGHT, Image.SCALE_SMOOTH);
            MAP = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Map.gif"))
                    .getScaledInstance(AllProperties.MAP_WIDTH, AllProperties.MAP_HEIGHT, Image.SCALE_SMOOTH);
            SON = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Lover.gif"))
                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(AllProperties.MAP_WIDTH,AllProperties.MAP_HEIGHT+AllProperties.PANEL_HEIGHT);
    }

    
    /**
     * Paints all <code>Enitiy</code>s, statics, map and logo.
     * 
     * @param g the pane to be drown on it.
     */
    @Override
    public void paintComponent(Graphics g) {
        // TODO implement here
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // DRAW LOGO AND MAP
        g2d.drawImage(LOGO,AllProperties.MAP_WIDTH - LOGO.getWidth(null),0,null);
        g2d.drawImage(MAP,0,AllProperties.PANEL_HEIGHT+2,null);
        
        // DRAW TOP STATICS PANEL
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(0, AllProperties.PANEL_HEIGHT, AllProperties.MAP_WIDTH, AllProperties.PANEL_HEIGHT);
       
        // DRAW STATICS
        int space = AllProperties.PANEL_HEIGHT/7;
        int dist = (AllProperties.MAP_WIDTH-LOGO.getWidth(null))/3;
        g2d.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        g2d.setColor(Color.magenta);
        g2d.drawString("Death Toll    : ", 20, space*2);
        g2d.setColor(Color.cyan);
        g2d.drawString("Citizens      : "+environment.deadCitizen, 20, space*4);
        g2d.setColor(Color.green);
        g2d.drawString("Soldiers      : "+environment.deadSoldier, 20, space*6);
        
        g2d.setColor(Color.blue);
        g2d.drawString("Agents        : "+environment.deadAgent, dist+20, space*4);
        g2d.setColor(Color.red);
        g2d.drawString("Terrorists    : "+environment.deadTerrorist, dist+20, space*6);
        
        g2d.setColor(Color.black);
        g2d.drawString("Step               : "+turn, dist*2, space*2);
        g2d.setColor(Color.red);
        g2d.drawString("Caught by Agents   : "+environment.caughtByAgents, dist*2, space*4);
        g2d.drawString("Caught by Soldiers : "+environment.caughtBySoldiers, dist*2, space*6);
        
        
        for (int i = 0 ; i<environment.entities.size();i++)
            environment.entities.get(i).draw(g2d);
        
        g2d.setColor(Color.BLACK);
        if(isSonVisible){
            g2d.drawImage(SON, sonX,sonY, this);
            double distance = Math.sqrt(Math.pow(environment.entities.get(6).position.x - sonX, 2)+
                                        Math.pow(environment.entities.get(6).position.y - sonY, 2)
                    );
            if (distance < 50.0){
                g2d.drawString("Oh NEO!",sonX,sonY-5);
            }
        }
        
    }

    /**
     * Main loop of the game. It always runs with a 100 msecs intervals and repaint the pane.
     * Also calls garbage collector at every 250 steps.
     * 
     */
    public void redrawThings(){
        int gcCount=0;
        while(true){
            turn++;
            environment.stepAll(0.6);
            repaint();
            gcCount++;
                if(gcCount>=250){
                    gcCount=0;
                    System.gc();
                }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                sonX-=5;
                break;
            case KeyEvent.VK_RIGHT:
                sonX += 5;
                break;
            case KeyEvent.VK_UP:
                sonY -= 5;
                break;
            case KeyEvent.VK_DOWN:
                sonY += 5;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        sonX = me.getPoint().x;
        sonY = me.getPoint().y;
        isSonVisible = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
    
    
    
}