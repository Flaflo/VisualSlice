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
    private static final long STANDARD_FPS_MILLIS = 1000 / 60;

    private final String title;
    private final int width, height;

    private final long fpsMillis;

    /**
     * @param title the title
     * @param width the width
     * @param height the height
     */
    public Display(final String title, final int width, final int height) {
        this(title, width, height, STANDARD_FPS_MILLIS);
    }

    /**
     * @param title the title
     * @param width the width
     * @param height the height
     * @param fps the fps
     */
    public Display(final String title, final int width, final int height, final long fps) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.fpsMillis = 1000 / fps;
    }

    @Override
    public void run() {
        G2D.window(this.getTitle(), this.getWidth(), this.getHeight(), false, true);
        G2D.init(BUFFERS);

        while (G2D.window().isShowing()) {
            G2D.push();
            G2D.high();

            G2D.pop();

            try {
                Thread.sleep(this.getFpsMillis());
            } catch (InterruptedException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
