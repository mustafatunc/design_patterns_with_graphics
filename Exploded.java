import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

/**
 * <code>Exploded</code> class is a subclass of <code>TerroristDecorators</code> class.
 * <code>Exploded</code> class is one of the decorators of <code>Terrorist</code> class.
 * Once a <code>Terrorist</code> decorated with <code>Exploded</code> class, an explosion image is drawn 
 * to random positions in the range of the <code>Terrorist</code> decorated.
 * If a <code>Equipped</code> hits a 0.1 probability,  it is decorated with this class.
 * 
 * @see Terrorist
 * @see TerroristDecorator
 * @see Entity
 * @see Equipped
 */
public class Exploded extends TerroristDecorator {

    private final Image explosion;
    private int animationLevel = 40;
    private final Random rand = new Random();

    /**
     * Initializes a <code>Exploded</code> instance.
     * @param t <code>Terrorist</code> instance to be decorated.
     */
    public Exploded(Terrorist t) {
        super(t);
        decorated = t;
        this.strategy = t.strategy;
        this.explosion = AllProperties.IMAGE_EXPLOSION;
    }

    /**
     * Checks whether explosion animation is completed.
     * 
     * @return <code>true</code> if explosion animation is completed. 
     */
    public boolean isCompleted() {
        return this.animationLevel <= 0;
    }

    /**
     * First draws <code>decorated</code>, later
     * draws the <code>Exploded</code> instance onto <code>g2d</code>
     * 
     * @param g2d The pane to be drawn on it.
     */
    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here
        decorated.draw(g2d);
        if (!isCompleted()) {
            int x = (int) this.position.x + rand.nextInt(this.range / 2) * (rand.nextBoolean() ? 1 : -1);
            int y = (int) this.position.y + rand.nextInt(this.range / 2) * (rand.nextBoolean() ? 1 : -1);
            g2d.drawImage(this.explosion, x, y, null);
            this.animationLevel--;

        }

    }

}
