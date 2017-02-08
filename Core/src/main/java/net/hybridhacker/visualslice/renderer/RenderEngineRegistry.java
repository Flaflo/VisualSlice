package net.hybridhacker.visualslice.renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * The render engine registry holds instances of all available render engines, so they can be switched
 */
public class RenderEngineRegistry {
    private final static RenderEngineRegistry instance;
    
    private final List<IRenderEngine> renderEngineList = new ArrayList<>();
    
    static {
        instance = new RenderEngineRegistry();
    }
    
    /**
     * This class is a singleton and thus handles instantiating only itself
     */
    private RenderEngineRegistry() {
        // intentionally empty
    }
    
    /**
     * @return the singleton instance of this class
     */
    public static RenderEngineRegistry getInstance() {
        return instance;
    }
    
    /**
     * Register a render engine
     *
     * @param engine {@link IRenderEngine} implementation
     */
    public void registerEngine(final IRenderEngine engine) {
        this.renderEngineList.add(engine);
    }
    
    /**
     * @return an array of all registered render engines
     */
    public IRenderEngine[] getRenderEngines() {
        return this.renderEngineList.toArray(new IRenderEngine[this.renderEngineList.size()]);
    }
}
