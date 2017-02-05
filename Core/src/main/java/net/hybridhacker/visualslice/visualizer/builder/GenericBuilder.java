package net.hybridhacker.visualslice.visualizer.builder;

import lombok.Getter;
import lombok.Setter;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A generic builder that can build decorated visualizers out of any visualizers and decorators that are registered
 */
public class GenericBuilder implements VisualizerBuilder {
    
    @Getter
    @Setter
    public IVisualizer visualizer = null;
    
    private final List<String> decorators = new ArrayList<>();
    private final Map<String, AbstractVisualizerDecorator> decoratorPrototypes = new HashMap<>();
    
    public GenericBuilder() {
        
    }
    
    @Override
    public IVisualizer buildVisualizer() {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer chosen");
        
        IVisualizer hithertoVisualizer = this.visualizer;
        
        for (String decoratorName : decorators) {
            final AbstractVisualizerDecorator decorator =
                    DecoratorRegistry.getInstance().createNewDecorator(decoratorName, hithertoVisualizer);
    
            final Setting[] prototypeSettings = this.getDecoratorSettings(decoratorName);
            for (int i = 0; i < prototypeSettings.length; i++) {
                decorator.getSettings()[i].setValue(prototypeSettings[i].getValue());
            }
            
            hithertoVisualizer = decorator;
        }
    
        hithertoVisualizer.initialize();
        return hithertoVisualizer;
    }
    
    /**
     * Add a decorator to the builder (by its name)
     *
     * @param decorator name of a decorator
     */
    public void addDecorator(final String decorator) {
        this.decorators.add(decorator);
        this.decoratorPrototypes.put(decorator, DecoratorRegistry.getInstance().createNewDecorator(decorator, null));
    }
    
    /**
     * Remove a previously added decorator
     *
     * @param decorator name of a decorator
     */
    public void removeDecorator(final String decorator) {
        this.decorators.remove(decorator);
        this.decoratorPrototypes.remove(decorator);
    }
    
    /**
     * @param name name of a decorator
     *
     * @return an array of settings assigned to the decorator with given name
     */
    public Setting<?>[] getDecoratorSettings(final String name) {
        return this.decoratorPrototypes.get(name).getSettings();
    }
}
