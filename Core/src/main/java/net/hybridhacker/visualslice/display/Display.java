package net.hybridhacker.visualslice.display;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import net.hybridhacker.visualslice.utils.G2D;

/**
 * Display for VisualSlice
 *
 * @author Flaflo
 */
@Data
public final class Display extends Thread {

    private static final int BUFFERS = 3;
    
    private final String title;
    private final int width, height;

    private final long fps;
    
    @Override
    public void run() {
        G2D.window(this.getTitle(), this.getWidth(), this.getHeight(), false, true);
        G2D.init(BUFFERS);

        while (G2D.window().isShowing()) {
            G2D.push();
            G2D.high();
            
            G2D.pop();
            
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
