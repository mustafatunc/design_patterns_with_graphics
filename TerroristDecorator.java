import java.awt.Graphics2D;

/**
 * <code>TerroristDecorator</code> is the class for decorating <code>Terrorist</code>s.
 * 
 */
public abstract class TerroristDecorator extends Terrorist {

    protected Terrorist decorated;
    
    /**
     * Initializes new <code>TerroristDecorator</code>
     * @param t <code>Terrorist</code> to be decorated.
     */
    public TerroristDecorator(Terrorist t) {
        super(t.color, t.speed,t.position);
        decorated = t;
        
    }
    
    /**
     * Sets the strategy of the decorator and decorated <code>Terrorist</code>'s strategy.
     * 
     * @param strategy <code>StepStrategy</code> to be set.
     */
    @Override
    public void setStrategy(StepStrategy strategy) {
        this.strategy = strategy;
        this.decorated.setStrategy(strategy);
    }
    
    @Override
    public abstract void draw(Graphics2D g2d);
    

}