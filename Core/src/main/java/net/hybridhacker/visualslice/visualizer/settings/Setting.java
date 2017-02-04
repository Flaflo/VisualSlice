package net.hybridhacker.visualslice.visualizer.settings;

import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 */
@Data
public class Setting {
    
    public Setting() {
        throw new NotImplementedException();
    }
    
    public static enum SettingType {
        TYPE_URI, TYPE_STRING, TYPE_INTEGER, TYPE_DOUBLE;
    }
}
