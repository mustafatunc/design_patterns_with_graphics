import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 * Includes all default properties about the game. One can reach <code>Entity</code>;s counts, ranges and speed.
 * Also includes <code>Image</code>s used in the game and width and height of the game pane.
 * @author mustafa
 */
public final class AllProperties {
    public static Image IMAGE_TERRORIST, IMAGE_SOLDIER, IMAGE_AGENT,IMAGE_BOMB,IMAGE_BRAIN, IMAGE_EXPLOSION;
    public static int MAP_WIDTH, MAP_HEIGHT, PANEL_HEIGHT;
    public static int CITIZENS, SOLDIERS, AGENTS, TERRORISTS;
    public static double SPEED_CITIZEN,SPEED_SOLDIER, SPEED_AGENT,SPEED_TERRORIST;
    public static int RANGE_AGENT, RANGE_SOLDIER, RANGE_TERRORIST;
    
    
    /**
     * Initializes all static variables used in the game.
     * Default values are read from the <code>defaultsettings</code> file.
     * This method should be called once in the sake of efficiency.
     */
    public static void initialize() {
        Properties prop = new Properties();
        InputStream in ;//= CountryRunner.class.getClassLoader().getResourceAsStream("/raw/defaultsettings.properties");
        System.out.println("Read all properties from file");
        try {
            in = CountryRunner.class.getResourceAsStream("/raw/defaultsettings.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MAP_WIDTH = Integer.parseInt(prop.getProperty("map_width"));
        MAP_HEIGHT = Integer.parseInt(prop.getProperty("map_height"));
        PANEL_HEIGHT = Integer.parseInt(prop.getProperty("panelheight"));
        
        CITIZENS = Integer.parseInt(prop.getProperty("citizens"));
        AGENTS = Integer.parseInt(prop.getProperty("agents"));
        TERRORISTS = Integer.parseInt(prop.getProperty("terrorists"));
        SOLDIERS = Integer.parseInt(prop.getProperty("soldiers"));
        
        SPEED_SOLDIER = Double.parseDouble(prop.getProperty("speed_soldier"));
        SPEED_AGENT = Double.parseDouble(prop.getProperty("speed_agent"));
        SPEED_CITIZEN = Double.parseDouble(prop.getProperty("speed_citizen"));
        SPEED_TERRORIST = Double.parseDouble(prop.getProperty("speed_terrorist"));
        
        RANGE_AGENT = Integer.parseInt(prop.getProperty("range_agent"));
        RANGE_SOLDIER = Integer.parseInt(prop.getProperty("range_soldier"));
        RANGE_TERRORIST = Integer.parseInt(prop.getProperty("range_terrorist"));
        
        try {
            IMAGE_AGENT = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Agent.gif"))
                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            IMAGE_BOMB = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Bomb.gif"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            IMAGE_BRAIN = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Brain.gif"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            IMAGE_EXPLOSION = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Explosion.gif"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            IMAGE_SOLDIER = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Soldier.gif"))
                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            IMAGE_TERRORIST = ImageIO.read(CountryRunner.class.getResourceAsStream("/raw/Terrorist.gif"))
                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
