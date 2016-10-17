import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * <code>Entity</code> is an abstract class for the concrete <code>Entity</code> classes like <code>Citizen</code>.
 * @author mustafa
 * @see Citizen
 * @see Agent
 * @see Soldier
 * @see Terrorist
 * @see Camp
 * @see SupplyStorage
 * 
 */
public abstract class Entity {

    protected StepStrategy strategy;
    protected Color color;
    protected String name;
    protected double speed;
    protected Position position;
    protected Image image;
    protected int range;
    protected final int imageSize = 50;
    
    /**
     * Default constructor
     */
    public Entity() {
    }
    
    /**
     * Initializes new <code>Entity</code> with the given parameters
     * 
     * @param color Color of the <code>Entity</code>
     * @param speed Speed of the <code>Entity</code>
     * @param position Position of the <code>Entity</code>
     */
    public Entity(Color color,double speed, Position position){
        this.color = color;
        this.speed = speed;
        this.position = position;
    }
    

    /**
     * Gets position of the <code>Entity</code>
     * 
     * @return Return the <code>Position</code> of the <code>Entity</code>.
     */
    public Position getPosition(){
        return position;
    }

    /**
     * Sets the strategy of the <code>Entity</code>.
     * 
     * @param strategy <code>StepStrategy</code> to be set.
     */
    public void setStrategy(StepStrategy strategy) {
        this.strategy = strategy;
    }

    
    /**
     * Moves the <code>Entity</code> with the given <code>deltaTime</code>.
     * @param deltaTime time to move.
     */
    public void step(double deltaTime){
        this.strategy.step(this, deltaTime);
    }

    /**
     * @param g2d 
     */
    public abstract void draw(Graphics2D g2d);

    
    /**
     * Checks whether the given <code>Entity</code>'s center is in the range of vision.
     * 
     * @param entity <code>Entity</code> instance to be compared
     * @return Returns <code>true</code> if <code>entity</code>'s center coordinates are in the range, <code>false</code> otherwise.
     */
    public boolean isInTheRange(Entity entity){
        
        double meX=this.position.x;
        double meY=this.position.y;
        
        double itX=entity.position.x;
        double itY=entity.position.y;
        
        return Math.sqrt( (itX-meX)*(itX-meX) + (itY-meY)*(itY-meY) )<entity.range/2;
    }
}