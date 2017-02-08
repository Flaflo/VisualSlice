package net.hybridhacker.visualslice.particlesystem.particles;

import java.awt.*;
import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.particlesystem.Particle;
import net.hybridhacker.visualslice.particlesystem.Vector2D;

/**
 * A simple circular particle that does not change its image
 */
public class SimpleParticle extends Particle {
    
    private final int size;
    private final Color color;
    
    /**
     * A simple colored circular particle with life time
     *
     * @param position initial particle position
     * @param velocity initial particle velocity
     * @param lifetime particle lifetime
     * @param size     particle size
     * @param color    particle color
     */
    public SimpleParticle(final Vector2D position, final Vector2D velocity, final int lifetime, final int size, final Color color) {
        super(position, velocity, lifetime);
        this.size = size;
        this.color = color;
    }
    
    /**
     * A simple colored circular particle living forever
     *
     * @param position initial particle position
     * @param velocity initial particle velocity
     * @param size     particle size
     * @param color    particle color
     */
    public SimpleParticle(final Vector2D position, final Vector2D velocity, final int size, final Color color) {
        this(position, velocity, -1, size, color);
    }
    
    @Override
    protected void onDraw() {
        this.getPosition().add(this.getVelocity());
        Controller.getInstance().getRenderEngine().setColor(this.color);
        Controller.getInstance().getRenderEngine().fillOval((int) this.getX(), (int) this.getY(), this.size, this.size);
    }
}
