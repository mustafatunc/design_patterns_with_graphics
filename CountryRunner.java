import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 */
public class CountryRunner {

    public final JFrame frame = new JFrame();
    public final Display display = new Display();
    
    /**
     * Default constructor
     */
    public CountryRunner() {
        frame.setTitle("Country Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        display.addKeyListener(display);
        display.addMouseListener(display);
        display.setFocusable(true);
        container.add(display);
        //frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }


    /**
     * @param args 
     */
    public static void main(String[] args) {
        // TODO implement here
        AllProperties.initialize();
        CountryRunner cr = new CountryRunner();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cr.frame.setVisible(true);
            }
        });
        cr.display.redrawThings();
    }

}