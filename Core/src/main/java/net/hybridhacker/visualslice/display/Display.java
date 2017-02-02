package net.hybridhacker.visualslice.display;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import net.hybridhacker.visualslice.utils.G2D;
import org.apache.commons.lang3.ArrayUtils;

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

    private Runnable[] renderers;

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
        
        this.renderers = new Runnable[0];
    }

    @Override
    public void run() {
        G2D.window(this.getTitle(), this.getWidth(), this.getHeight(), false, true);
        G2D.init(BUFFERS);

        while (G2D.window().isShowing()) {
            G2D.push();
            G2D.high();
            Arrays.stream(this.getRenderers()).forEach(Runnable::run);
            G2D.pop();

            try {
                Thread.sleep(this.getFpsMillis());
            } catch (InterruptedException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Adds a renderer
     *
     * @param runnable the runnable
     */
    public void addRenderer(final Runnable runnable) {
        this.setRenderers(ArrayUtils.add(this.getRenderers(), runnable));
    }

    /**
     * Removes a renderer
     *
     * @param runnable the runnable
     */
    public void removeRenderer(final Runnable runnable) {
        this.setRenderers(ArrayUtils.removeElement(this.getRenderers(), runnable));
    }
}
