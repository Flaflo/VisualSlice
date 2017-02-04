package net.hybridhacker.visualslice.visualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class DecoratorRegistry {
    private final static DecoratorRegistry instance;
    
    private Map<String, Function<IVisualizer, ? extends AbstractVisualizerDecorator>> registeredDecorators = new HashMap<>();
    
    static {
        instance = new DecoratorRegistry();
    }
    
    /**
     * This class is a singleton and thus only creates itself
     */
    private DecoratorRegistry() {
        
    }
    
    /**
     * @return the singleton instance of this class
     */
    public static DecoratorRegistry getInstance() {
        return instance;
    }
    
    public void registerDecorator(final AbstractVisualizerDecorator prototype) {
        this.registeredDecorators.put(prototype.getName(), prototype::create);
    }
    
    /**
     * @return a list of strings that are names of registered decorators
     */
    public String[] getRegisteredDecorators() {
        return this.registeredDecorators.keySet().toArray(new String[this.registeredDecorators.size()]);
    }
    
    /**
     * Create a new decorator from its prototype
     *
     * @param decorator          the name of the decorator to create
     * @param embeddedVisualizer the IVisualizer instance to embed in the new decorator
     *
     * @return a new instance of any subclass of {@link AbstractVisualizerDecorator}
     */
    public AbstractVisualizerDecorator createNewDecorator(final String decorator, final IVisualizer embeddedVisualizer) {
        return this.registeredDecorators.get(decorator).apply(embeddedVisualizer);
    }
}
