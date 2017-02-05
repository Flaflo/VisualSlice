package net.hybridhacker.visualslice.particlesystem;

import lombok.Data;

/**
 * A simple vector class
 */
@Data
public class Vector2D {
    private double x, y;
    
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Add another vector to this vector
     *
     * @param vector a {@link Vector2D} instance
     */
    public void add(final Vector2D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }
    
    /**
     * @return length of this vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * Normalize this vector instance
     */
    public void normalize() {
        final double length = this.length();
        this.x /= length;
        this.y /= length;
    }
    
    /**
     * Divide the vector by a given value
     *
     * @param divisor divisor of the division
     */
    public void divide(final double divisor) {
        this.x /= divisor;
        this.y /= divisor;
    }
    
    /**
     * Multiply the velocity by a factor
     *
     * @param factor multiplication factor
     */
    public void multiply(final double factor) {
        this.x *= factor;
        this.y *= factor;
    }
}
