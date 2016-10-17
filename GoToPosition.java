/**
 * <code>GoToPosition</code> is a <code>StepStrategy</code> to go to a specified position.
 */
public class GoToPosition extends StepStrategy {

    private Position target;
    
    /**
     * Initializes a new <code>GoToPosition</code>
     * @param numOfTurns  turn number to stay alive for this strategy.
     * @param target Destination position to go.
     */
    public GoToPosition(int numOfTurns,Position target) {
        super(numOfTurns); 
        this.target = target;
        
    }

    /**
     * Gets name of the strategy.
     * 
     * @return Returns the name of the strategy.
     */
    @Override
    public String getName() {
        return "GP";
    }

    /**
     * Changes the position of the given <code>e</code> in the direction of <code>target</code>.
     * 
     * @param e <code>Entitiy</code> to move.
     * @param deltaTime time to move.
     */
    @Override
    public void step(Entity e, double deltaTime) {
        this.numberOfTurns--;
        if(e.position.distanceTo(this.target)<10){
            this.target = new Position();
        }
        e.position.move(e.speed, deltaTime,this.target );
        
    }

}