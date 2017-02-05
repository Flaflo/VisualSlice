package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import net.hybridhacker.visualslice.particlesystem.Particle;
import net.hybridhacker.visualslice.particlesystem.ParticleSystem;
import net.hybridhacker.visualslice.particlesystem.Vector2D;
import net.hybridhacker.visualslice.particlesystem.particles.SimpleParticle;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;
import java.util.Random;

/**
 *
 */
public class BeatParticleDecorator extends AbstractVisualizerDecorator {
    
    private static final Random random = new Random();
    private final ParticleSystem system;
    
    private boolean init = false;
    
    public BeatParticleDecorator(final IVisualizer visualizer) {
        super(visualizer);
        
        system = new ParticleSystem((ps, tick) -> ps.getParticles().forEach(Particle::draw));
    }
    
    @Override
    protected void doInitialize() {
        
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                          final BeatDetect beatDetect, final FFT fft, final int trackLength, final int trackPosition) {
        if (!init) {
            final int midX = G2D.canvas().getWidth() / 2;
            final int midY = G2D.canvas().getHeight() / 2;
            
            system.addHandler((ps, tick) -> ps.getParticles().add(new SimpleParticle(new Vector2D(midX, midY),
                                                                                     new Vector2D(random.nextDouble() - 0.5D,
                                                                                                  random.nextDouble() - 0.5D), 200, 2,
                                                                                     Color.YELLOW)));
            
            system.addHandler((ps, tick) -> ps.getParticles().forEach(p -> {
                if (beatDetect.isKick()) {
                    p.getVelocity().normalize();
                    p.accelerate(2.5);
                } else if (p.getVelocity().length() > 1) p.decelerate(1.2);
            }));
            
            this.init = true;
        }
        
        system.tick(trackPosition);
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new BeatParticleDecorator(embeddedVisualizer);
    }
    
    @Override
    public String getName() {
        return "Beat Particles";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[0];
    }
}
