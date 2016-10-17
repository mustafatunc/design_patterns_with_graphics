/**
 * <code>MoveRandom</code> is a <code>StepStrategy</code> to go to a random position.
 */
public class MoveRandom extends StepStrategy {

    /**
     * Initializes a new <code>MoveRandom</code>
     * @param numOfTurns  turn number to stay alive for this strategy.
     */
    public MoveRandom(int numOfTurns) {
        super(numOfTurns);
    }
    

    /**
     * Gets name of the strategy.
     * 
     * @return Returns the name of the strategy.
     */
    @Override
    public String getName() {
        return "MR";
    }


    /**
     * Changes the position of the given <code>e</code> in the direction of a random position.
     * 
     * @param e <code>Entitiy</code> to move.
     * @param deltaTime time to move.
     */
    @Override
    public void step(Entity e, double deltaTime) {
        this.numberOfTurns--;
         e.position.move(e.speed, deltaTime,new Position() );
        
    }

}