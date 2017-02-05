package net.hybridhacker.visualslice.export;

import lombok.Getter;
import lombok.Setter;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;
import org.apache.commons.lang3.ArrayUtils;

import java.net.URI;

/**
 * An exporter for visualizer videos
 */
public abstract class AbstractExporter {
    
    @Getter
    @Setter
    private int FPS = 60;
    
    @Getter
    @Setter
    private int width = 1920, height = 1024;
    
    public AbstractExporter() {
        
    }
    
    /**
     * @return the name of the exporter
     */
    public abstract String getName();
    
    /**
     * Export the given audio file with given visualizer as video
     *
     * @param audioSource some source of audio that can be loaded into the music player
     * @param visualizer  the decorated visualizer that shall be rendered
     */
    public abstract void export(final URI audioSource, final IVisualizer visualizer);
    
    /**
     * @return the settings specific to the exporter implementation
     */
    protected abstract Setting<?>[] getSecificSettings();
    
    /**
     * @return the exporter's default and specific settings
     */
    public final Setting<?>[] getSettings() {
        return ArrayUtils.addAll(this.getSecificSettings(), new Setting<>("FPS", this.FPS, this::setFPS),
                                 new Setting<>("Width", this.width, this::setWidth), new Setting<>("Height", this.height, this::setHeight));
    }
}
