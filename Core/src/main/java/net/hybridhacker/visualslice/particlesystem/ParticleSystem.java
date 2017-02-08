package net.hybridhacker.visualslice.particlesystem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * A basic particle system initialized with some consumers that will handle the system logic
 */
public class ParticleSystem {
    
    private static final int TPS = 60;
    private static final double TICK_GAP_MS = 1000D / TPS;
    
    private int ticksPassed;
    
    @Getter
    private final List<Particle> particles = new ArrayList<>();
    
    private final List<BiConsumer<ParticleSystem, Integer>> systemHandlers = new LinkedList<>();
    
    /**
     * Initialize a particle system with an array of handlers that will be called at every tick and consume the system and current tick
     *
     * @param handlers a variadic array of {@link BiConsumer} that will be used as a tick handler
     */
    @SafeVarargs
    public ParticleSystem(final BiConsumer<ParticleSystem, Integer>... handlers) {
        this.systemHandlers.addAll(Arrays.asList(handlers));
    }
    
    /**
     * Called whenever the particle system shall update. Calculates the ticks that should have happened yet and calls {@link #doTick(int)}
     * for every tick that was not executed yet but should have been.
     */
    public final void tick(final int positionMs) {
        while (positionMs / TICK_GAP_MS > ticksPassed) {
            doTick(++ticksPassed);
        }
    }
    
    /**
     * Tick the particle system
     *
     * @param tick current tick
     */
    protected void doTick(final int tick) {
        this.systemHandlers.forEach(handler -> handler.accept(this, tick));
    }
    
    /**
     * Add a handler to the system
     *
     * @param handler consumer as system tick handler
     */
    public void addHandler(final BiConsumer<ParticleSystem, Integer> handler) {
        this.systemHandlers.add(handler);
    }
}
