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
     * Sets the setting value and updates the visualizer via the consumer.
     * <p>
     * Note: Due to the lack of useful generics in Java this method accepts an object and casts it to its generic type. It is expected,
     * that this cast works.
     *
     * @param value new value for this setting
     */
    @SuppressWarnings("unchecked")
    public void setValue(final Object value) {
        this.value = (T) value;
        if (settingUpdater != null) settingUpdater.accept((T) value);
    }
    
    public T getValue() {
        return this.value;
    }
}
