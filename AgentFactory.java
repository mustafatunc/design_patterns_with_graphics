import java.awt.Color;


/**
 * <code>AgentFactory</code> is for creating new <code>Agent</code> instances.
 */
public class AgentFactory extends EntityFactory {

    /**
     * Creates a new <code>Agent</code> instance.
     * @return Returns new <code>Agent</code> instance with default color,speed and random position.
     * @see AllProperties
     */
    @Override
    public Entity createEntity() {
        return new Agent(Color.BLUE,AllProperties.SPEED_AGENT, new Position(AllProperties.RANGE_AGENT));
    }

}