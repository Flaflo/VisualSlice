package net.hybridhacker.visualslice.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Flaflo
 */
public final class MathUtil {

    /**
     * The random constant
     */
    public static final Random RANDOM = new Random();

    /**
     * Ranom number between two values as int
     *
     * @param min the min
     * @param max the max
     * @return the number
     */
    public static int nextInt(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Maps a clamped value to a new clamp range
     *
     * @param value value to map
     * @param originalMin old lower bound
     * @param originalMax old upper bound
     * @param newMin new lower bound
     * @param newMax new upper bound
     *
     * @return mapped integer value
     */
    public static int map(int value, int originalMin, int originalMax, int newMin, int newMax) {
        return newMin + (int) (value / (float) (originalMax - originalMin) * (newMax - newMin));
    }

    /**
     * Maps a clamped value to a new clamp range
     *
     * @param value value to map
     * @param originalMin old lower bound
     * @param originalMax old upper bound
     * @param newMin new lower bound
     * @param newMax new upper bound
     *
     * @return mapped float value
     */
    public static float map(float value, float originalMin, float originalMax, float newMin, float newMax) {
        return newMin + (value / (originalMax - originalMin) * (newMax - newMin));
    }
    
    
    /**
     * Calculate the cubic root of the specified value.
     */
    public static double cubicRoot(double value) {
        return Math.pow(value, 1d / 3d);
    }
}
