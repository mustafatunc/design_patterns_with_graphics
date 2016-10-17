
import java.util.Random;

/**
 * <code>StandStill</code> is a <code>StepStrategy</code> for not changing position.
 */
public class StandStill extends StepStrategy {

    private final int probability = new Random().nextInt(10); 
    
    /**
     * Initializes a new <code>MoveLinear</code>
     * @param numOfTurns  turn number to stay alive for this strategy.
     */
    public StandStill(int numOfTurns) {
        super(numOfTurns);
    }

    
    /**
     * Gets name of the strategy.
     * 
     * @return Returns the name of the strategy.
     */
    @Override
    public String getName() {
        return "SS";
    }

    /**
     * Gets probability that is randomly set by.
     * 
     * @return probability.
     */
    
    public int getProbability() {
        return probability;
    }

    /**
     * Only spends time.
     * 
     * @param e <code>Entitiy</code> to stand.
     * @param deltaTime time to stand.
     */
    @Override
    public void step(Entity e, double deltaTime) {
        this.numberOfTurns--;
    }

}