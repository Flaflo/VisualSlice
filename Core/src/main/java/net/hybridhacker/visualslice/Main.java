package net.hybridhacker.visualslice;

import java.awt.Color;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.utils.G2D;

/**
 * Application's main class
 */
public final class Main {
    
    /**
     * Main method
     *
     * @param args terminal arguments
     */
    public static void main(final String... args) {
        final Display display = new Display("VisualSlice", 800, 600);
        display.addRenderer(() -> G2D.clear(Color.BLACK));
        display.start();
    }
}
