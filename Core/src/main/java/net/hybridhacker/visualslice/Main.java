package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.display.Display;

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
        display.start();
    }
}
