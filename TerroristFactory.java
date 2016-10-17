import java.awt.Color;

/**
 * Factory class that produces <code>RecentlyDeceivedTerrorist</code>
 */
public class TerroristFactory extends EntityFactory {

    /**
     * Creates a new <code>RecentlyDeceivedTerrorist</code> instance.
     * @return Returns new <code>RecentlyDeceivedTerrorist</code> instance with default speed, color and random position.
     * @see AllProperties
     */
    @Override
    public Entity createEntity() {
       return new RecentlyDeceivedTerrorist(Color.RED,AllProperties.SPEED_TERRORIST,new Position(AllProperties.RANGE_TERRORIST));
    }

}