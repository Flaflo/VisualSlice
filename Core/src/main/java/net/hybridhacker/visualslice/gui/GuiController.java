package net.hybridhacker.visualslice.gui;

import lombok.Getter;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.gui.settings.DisplaySettings;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.visualizer.builder.GenericBuilder;

/**
 * @author Flaflo
 */
public final class GuiController {
    
    private static final GuiController instance = new GuiController();
    
    @Getter
    private final GenericBuilder builder = new GenericBuilder();
    
    @Getter
    private final MusicPlayer player = new MusicPlayer();
    
    @Getter
    private final DisplaySettings displaySettings = new DisplaySettings();
    
    private Display display = null;
    
    public static GuiController getInstance() {
        return instance;
    }
    
    /**
     * This class is a singleton and thus handles its creation on its own
     */
    private GuiController() {}
    
    /**
     * Returns the display instance controlled by this controller. As the method is invoked first, the display instance is created and
     * then saved
     *
     * @return the display instance controlled by this controller
     */
    public Display getDisplay() {
        if (this.display == null) {
            this.display = new Display("VisualSlice", this.displaySettings.getWidth(), this.displaySettings.getHeight(),
                                       this.displaySettings.getFps());
        }
        
        return this.display;
    }
}
