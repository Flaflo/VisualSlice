package net.hybridhacker.visualslice.visualizer.builder;

import lombok.Getter;
import lombok.Setter;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic builder that can build decorated visualizers out of any visualizers and decorators that are registered
 */
public class GenericBuilder implements VisualizerBuilder {
    
    @Getter
    @Setter
    public IVisualizer visualizer = null;
    
    public List<String> decorators = new ArrayList<>();
    
    public GenericBuilder() {
        
    }
    
    @Override
    public IVisualizer buildVisualizer() {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer chosen");
        
        IVisualizer hithertoVisualizer = this.visualizer;
        
        for (String decoratorName : decorators) {
            final AbstractVisualizerDecorator decorator =
                    DecoratorRegistry.getInstance().createNewDecorator(decoratorName, hithertoVisualizer);
            
            // TODO settings
            hithertoVisualizer = decorator;
        }
        
        return hithertoVisualizer;
    }
    
    /**
     * Add a decorator to the builder (by its name)
     *
     * @param decorator name of a decorator
     */
    public void addDecorator(final String decorator) {
        this.decorators.add(decorator);
    }
    
    /**
     * Remove a previously added decorator
     *
     * @param decorator name of a decorator
     */
    public void removeDecorator(final String decorator) {
        this.decorators.remove(decorator);
    }
}
