/**
 * <code>MoveLinear</code> is a <code>StepStrategy</code> to go to a predefined random position.
 */
public class MoveLinear extends StepStrategy {

    private Position destination;
    
    /**
     * Initializes a new <code>MoveLinear</code>
     * @param numOfTurns  turn number to stay alive for this strategy.
     */
    public MoveLinear(int numOfTurns) {
        super(numOfTurns);
        this.destination = new Position();
    }

    /**
     * Gets name of the strategy.
     * 
     * @return Returns the name of the strategy.
     */
    @Override
    public String getName() {
        return "ML";
    }

    /**
     * Changes the position of the given <code>e</code> in the direction of predefined random position.
     * 
     * @param e <code>Entitiy</code> to move.
     * @param deltaTime time to move.
     */
    @Override
    public void step(Entity e, double deltaTime) {
        this.numberOfTurns--;
        if(e.position.distanceTo(destination)<10){
            this.destination = new Position();
        }
        e.position.move(e.speed, deltaTime,destination );
    }

}