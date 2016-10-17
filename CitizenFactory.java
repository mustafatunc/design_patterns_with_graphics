import java.awt.Color;
import java.util.Random;


/**
 * Factory class that produces <code>Citizen</code>
 */
public class CitizenFactory extends EntityFactory {

    
    /**
     * Creates a new <code>Citizen</code> instance.
     * @return Returns new <code>Citizen</code> instance with default speed and random position and color.
     * @see AllProperties
     */
    @Override
    public Entity createEntity() {
        Random random = new Random();
        Color color = new Color(random.nextInt(225),random.nextInt(225), random.nextInt(225));
        return new Citizen(color, AllProperties.SPEED_CITIZEN, new Position());
    }

    
}