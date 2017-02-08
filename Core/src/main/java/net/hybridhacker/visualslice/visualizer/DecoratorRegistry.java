package net.hybridhacker.visualslice.visualizer;

import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class DecoratorRegistry {
    private final static DecoratorRegistry instance;
    
    private final Map<String, Function<IVisualizer, ? extends AbstractVisualizerDecorator>> registeredDecorators = new HashMap<>();
    private final Map<String, Setting<?>[]> decoratorPrototypeSettings = new HashMap<>();
    
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
    
    /**
     * Register a decorator using a prototype instance of it. This instance should not be used after registration and may not contain any
     * embedded visualizer since that is not needed
     *
     * @param prototype an instance of the decorator type to register
     */
    public void registerDecorator(final AbstractVisualizerDecorator prototype) {
        this.registeredDecorators.put(prototype.getName(), prototype::create);
        this.decoratorPrototypeSettings.put(prototype.getName(), prototype.getSettings());
    }
    
    /**
     * @return a list of strings that are names of registered decorators
     */
    public String[] getRegisteredDecorators() {
        return this.registeredDecorators.keySet().toArray(new String[this.registeredDecorators.size()]);
    }
    
    /**
     * Get the settings of a decorator to change them
     *
     * @param decorator the name of the desired decorator
     *
     * @return an array of settings.
     */
    public Setting<?>[] getSettingsOfDecorator(final String decorator) {
        return this.decoratorPrototypeSettings.get(decorator);
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
        final AbstractVisualizerDecorator decoratorInst = this.registeredDecorators.get(decorator).apply(embeddedVisualizer);
        final Setting<?>[] settings = decoratorInst.getSettings();
        final Setting<?>[] prototypeSettings = decoratorPrototypeSettings.get(decorator);
    
        for (int i = 0; i < settings.length; i++) {
            settings[i].setValue(prototypeSettings[i]);
        }
    
        return decoratorInst;
    }
}
