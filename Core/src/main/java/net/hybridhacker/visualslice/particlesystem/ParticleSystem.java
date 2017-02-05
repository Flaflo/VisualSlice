package net.hybridhacker.visualslice.particlesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A basic particle system initialized with some consumers that will handle the system logic
 */
public class ParticleSystem {
    
    private static final int TPS = 60;
    private static final double TICK_GAP_MS = 1000D / TPS;
    
    private int ticksPassed;
    
    private final List<Particle> particles = new ArrayList<>();
    private final List<Consumer<Integer>> systemHandlers = new LinkedList<>();
    
    @SafeVarargs
    public ParticleSystem(final Consumer<Integer>... handlers) {
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
        this.systemHandlers.forEach(handler -> handler.accept(tick));
    }
}
