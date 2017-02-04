package net.hybridhacker.visualslice.visualizer.settings;

import lombok.Data;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * A setting value for a visualizer
 */
@Data
public class Setting<T extends Serializable> {
    
    private final String name;
    private T value;
    
    private final Consumer<T> settingUpdater;
    
    /**
     * @param name          name of the setting
     * @param initialValue  initial setting value
     * @param settingSetter a consumer that accepts updates of the setting to inform the setting
     */
    public Setting(final String name, final T initialValue, final Consumer<T> settingSetter) {
        this.name = name;
        this.value = initialValue;
        this.settingUpdater = settingSetter;
    }
    
    /**
     * Sets the setting value and updates the visualizer via the consumer
     *
     * @param value new value for this setting
     */
    public void setValue(final T value) {
        this.value = value;
        if (settingUpdater != null) settingUpdater.accept(value);
    }
}
