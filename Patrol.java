import java.util.Random;
/**
 * <code>Patrol</code> is a <code>StepStrategy</code> to patrol between to position.
 */
public class Patrol extends StepStrategy {
    
    private final Position firstPost;
    private Position secondPost;
    private boolean isGoingToSecondPost=true;
    
    /**
     * Initializes a new <code>Patrol</code>
     * @param numOfTurns turn number to stay alive for this strategy.s
     * @param currentPositon Current position of a <code>Entity</code> instance. This position is assigned to the first post of the soldier
     * who patrols between two posts.
     */
    public Patrol(int numOfTurns,Position currentPositon) {
        super(numOfTurns);
        this.firstPost = new Position(currentPositon);
        selectSecondPost();
    }
    
    
    /**
     * Gets name of the strategy.
     * 
     * @return Returns the name of the strategy.
     */
    @Override
    public String getName() {
        return "P";
    }

    
    /**
     * Changes the position of the given <code>e</code> between <code>currentPositon</code> and a random position in a range.
     * 
     * @param e <code>Entitiy</code> to move.
     * @param deltaTime time to move.
     */
    @Override
    public void step(Entity e, double deltaTime) {
        this.numberOfTurns--;
        if(this.isGoingToSecondPost){
            //firstPost dan secondPost a gidiyorum
            e.position.move(e.speed, deltaTime,this.secondPost );
            if(e.position.distanceTo(this.secondPost)<5){
                this.isGoingToSecondPost =false;
            }
        }
        else {
            //secindPost dan firstPost a gidiyorum
            e.position.move(e.speed, deltaTime,this.firstPost );
            if(e.position.distanceTo(this.firstPost)<5){
                this.isGoingToSecondPost =true;
            }
        }
    }

    /**
     * Selects a second post in a range to shuttle.
     * 
     */
    private void selectSecondPost() {
        Random rand = new Random();
        double randomY = firstPost.y + rand.nextDouble()*(rand.nextInt(250)+150)*(rand.nextBoolean()?1:-1);
        double randomX = firstPost.x + rand.nextDouble()*(rand.nextInt(250)+150)*(rand.nextBoolean()?1:-1);
        
        if(randomY<AllProperties.PANEL_HEIGHT)randomY=AllProperties.PANEL_HEIGHT;
        if(randomY>AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT)randomY = AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT;
        if(randomX<AllProperties.PANEL_HEIGHT)randomX=AllProperties.PANEL_HEIGHT;
        if(randomX>AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT)randomX = AllProperties.PANEL_HEIGHT+AllProperties.MAP_HEIGHT;
        
        this.secondPost = new Position(randomX, randomY);
    }
    
    
}