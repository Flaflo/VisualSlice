package net.hybridhacker.visualslice.plugin;

import net.hybridhacker.visualslice.plugins.AutoRegister;
import net.hybridhacker.visualslice.plugins.SlicePlugin;

/**
 * An example plugin
 */
@AutoRegister
public class ExamplePlugin implements SlicePlugin {
    @Override
    public void onEnable() {
        
    }
    
    @Override
    public String getAuthor() {
        return "Cydhra";
    }
    
    @Override
    public String getVersion() {
        return "1.0";
    }
}
