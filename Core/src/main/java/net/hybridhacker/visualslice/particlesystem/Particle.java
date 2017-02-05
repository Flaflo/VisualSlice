package net.hybridhacker.visualslice.particlesystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * A simple particle that does not define the draw method
 */
@RequiredArgsConstructor
public abstract class Particle {
    
    @Getter
    private final Vector2D position;
    
    @Getter
    private final Vector2D velocity;
    
    @Getter
    private int lifetime = -1;
    
    @Getter
    @Setter
    private boolean dead = false;
    
    public Particle(final Vector2D position, final Vector2D velocity, final int lifetime) {
        this(position, velocity);
        this.lifetime = lifetime;
    }
    
    /**
     * Tick the particle and call {@link #onDraw()}
     */
    public final void draw() {
        this.onDraw();
        
        if (this.lifetime > 0) this.lifetime--;
        else if (this.lifetime == 0) this.setDead(true);
    }
    
    /**
     * Draw the particle
     */
    protected abstract void onDraw();
    
    /**
     * Accelerate the particle by a given velocity vector
     *
     * @param velocity direction and speed of acceleration
     */
    public void accelerate(final Vector2D velocity) {
        this.velocity.add(velocity);
    }
    
    /**
     * Decelerate the vector by given factor. (A factor below 1 will actually accelerate the particle)
     *
     * @param factor a factor that is divisor of a division applied to the particle's velocity
     */
    public void decelerate(final double factor) {
        this.velocity.divide(factor);
    }
    
    /**
     * Accelerate the particle by a given factor
     *
     * @param factor acceleration factor
     */
    public void accelerate(final double factor) {
        this.velocity.multiply(factor);
    }
    
    /**
     * Directly move this particle by given x and y offsets
     *
     * @param x x offset
     * @param y y offset
     */
    public void move(final double x, final double y) {
        this.move(new Vector2D(x, y));
    }
    
    /**
     * Directly move this particle by a given offset
     *
     * @param offset offset as vector
     */
    public void move(final Vector2D offset) {
        this.position.add(offset);
    }
    
    /**
     * @return the particle's x position
     */
    public double getX() {
        return this.position.getX();
    }
    
    /**
     * @return the particle's y position
     */
    public double getY() {
        return this.position.getY();
    }
}
