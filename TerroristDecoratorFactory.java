/**
 * Helper class for decorating <code>Terrorist</code>s.
 * @author mustafa
 */
public class TerroristDecoratorFactory {
    
    public static final int BRAIN_WASHED = 0;
    public static final int EQUIPPED = 1;
    public static final int EXPLODED = 2;
    
    /**
     * Creates a <code>TerroristDecorator</code> for the given <code>Terrorist</code> and <code>type</code>.
     * 
     * @param t <code>Terrorist</code> to be decorated.
     * @param type decorator indicator. 1 for <code>BrainWashed</code>, 2 for <code>Equipped</code>, 2 for <code>Exploded</code>.
     * @return 
     */
    public static TerroristDecorator createDecorator(Terrorist t, int type){
        switch(type){
            case BRAIN_WASHED:
                return new BrainWashed(t);
            case EQUIPPED:
                return new Equipped(t);
            case EXPLODED:
                return new Exploded(t);
            default:
                return null;
        }
    }
    
}
