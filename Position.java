import java.util.Random;

/**
 *  <code>Position</code> class handles the 2D positions in the game. 
 *  Calculations for <code>Position</code>s are made in this class.
 */
public class Position {
    
    public double x;
    public double y;

    /**
     * Initializes a <code>Position</code> instance with random coordinates.
     */
    public Position() {
        this.x = (new Random()).nextDouble()*AllProperties.MAP_WIDTH;
        this.y = (new Random()).nextDouble()*AllProperties.MAP_HEIGHT + AllProperties.PANEL_HEIGHT;
    }
    
    /**
     * Initializes a <code>Position</code> instance with the given coordinates.
     * @param x position on the x-coordinate
     * @param y position on the y-coordinate
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;// + AllProperties.PANEL_HEIGHT;
    }
 
    /**
     * Copy constructor.
     * 
     * @param p position to be copied.
     */
    public Position(Position p ){
        this.x = p.x;
        this.y= p.y;
    }

    /**
     * Initializes a <code>Position</code> instance with the given range.
     * Used for adjusting <code>Entity</code>'s centers. 
     * 
     * @param range 
     */
    public Position(int range){
        this.x = (new Random()).nextDouble()*AllProperties.MAP_WIDTH +range/2;
        this.y = (new Random()).nextDouble()*AllProperties.MAP_HEIGHT + AllProperties.PANEL_HEIGHT + range/2;
    }
    

    /**
     * @param other Other <code>Position</code> instance whose distance is calculated.
     * @return Distance to the <code>other</code> instance.
     */
    public double distanceTo(Position other) {
        double deltaX = x - other.x ;
        double deltaY = y - other.y ;
        return Math.sqrt( deltaX*deltaX + deltaY*deltaY ) ;
    }
    
    /**
     * Changes the x y coordinates according the given values. The formula used is basically <code>X = V.t</code>
     * 
     * @param speed Speed of <code>Entity</code> instance
     * @param deltaTime time to move
     * @param destination position to go
     * @see Entity
     */
    public void move(double speed,double deltaTime, Position destination){
        Position unitDirection = this.unitDirectionVector(destination);
        this.x += unitDirection.x * speed * deltaTime ;
        this.y += unitDirection.y * speed * deltaTime ;
    }
    
    /**
     * Calculates the unit vectors in the direction of a <code>destination</code>.
     * 
     * @param destination <code>Position</code> that unit vector is calculated against. 
     * @return Returns the unit vector calculated.
     */
    public Position unitDirectionVector(Position destination){
        double distance = this.distanceTo(destination);
        return new Position((destination.x-this.x)/distance, (destination.y - this.y)/distance);
    }

    
    
    
    
    
    
}