/**
 * Moving strategy of an <code>Entity</code>
 */
public abstract class StepStrategy {
    
    protected int numberOfTurns;

    /**
     * Initializes a new <code>StepStrategy</code>
     * @param numOfTurns Turn number for a strategy to run.
     */
    public StepStrategy(int numOfTurns) {
        this.numberOfTurns = numOfTurns;
    }


    /**
     * @return Returns name of the strategy.
     */
    public abstract String getName();

    /**
     * @return Returns <code>true</code> if the entity stepped enough, <code>false</code> otherwise.
     */
    public boolean isFinished(){
        return this.numberOfTurns<=0;
    }

    /**
     * @param e <code>Entity</code> instance to be moved.
     * @param deltaTime time to move.
     */
    public abstract void step(Entity e, double deltaTime);

}