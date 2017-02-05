package net.hybridhacker.visualslice.display;

import lombok.Getter;
import net.hybridhacker.visualslice.gui.VisualSliceGui;
import net.hybridhacker.visualslice.utils.G2D;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Display for VisualSlice
 *
 * @author Flaflo
 */
public final class Display extends Thread {
    
    private static final int BUFFERS = 3;
    private static final long STANDARD_FPS = 60;
    
    @Getter
    private final String title;
    @Getter
    private final int width, height;
    
    private final long fpsMillis;
    
    private Runnable[] renderers;
    
    /**
     * @param title  the title
     * @param width  the width
     * @param height the height
     */
    public Display(final String title, final int width, final int height) {
        this(title, width, height, STANDARD_FPS);
    }
    
    /**
     * @param title  the title
     * @param width  the width
     * @param height the height
     * @param fps    the fps
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
        G2D.window().setLayout(new BorderLayout());
        G2D.window().add(new VisualSliceGui(), BorderLayout.WEST);
        G2D.init(BUFFERS);
        G2D.window().pack();
        
        while (G2D.window().isShowing()) {
            long start = System.nanoTime() / 1_000_000;
            G2D.push();
            G2D.high();
            Arrays.stream(this.renderers).forEach(Runnable::run);
            G2D.pop();
            
            long stop = System.nanoTime() / 1_000_000;
            try {
                Thread.sleep(Math.max(0, this.fpsMillis - (stop - start)));
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
        this.renderers = ArrayUtils.add(this.renderers, runnable);
    }
    
    /**
     * Removes a renderer
     *
     * @param runnable the runnable
     */
    public void removeRenderer(final Runnable runnable) {
        this.renderers = ArrayUtils.removeElement(this.renderers, runnable);
    }
}
