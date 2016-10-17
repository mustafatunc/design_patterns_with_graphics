import java.awt.Color;

/**
 * <code>SoldierFactory</code> is for creating new <code>Soldier</code> instances.
 */
public class SoldierFactory extends EntityFactory {

    /**
     * Creates a new <code>Soldier</code> instance.
     * @return Returns new <code>Soldier</code> instance with default speed, color and random position.
     * @see AllProperties
     */
    @Override
    public Entity createEntity() {
        Color color = Color.GREEN;
        return new Soldier(color, AllProperties.SPEED_SOLDIER, new Position(AllProperties.RANGE_SOLDIER));
    }

}