package net.hybridhacker.visualslice.gui;

import lombok.Getter;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.gui.settings.DisplaySettings;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.Graphics2DRenderEngine;
import net.hybridhacker.visualslice.renderer.IRenderEngine;
import net.hybridhacker.visualslice.visualizer.builder.GenericBuilder;

/**
 * @author Flaflo
 */
public final class Controller {

    private static final Controller instance = new Controller();

    @Getter
    private final GenericBuilder builder = new GenericBuilder();

    @Getter
    private final MusicPlayer player = new MusicPlayer();

    @Getter
    private final DisplaySettings displaySettings = new DisplaySettings();

    @Getter
    private final IRenderEngine renderEngine = new Graphics2DRenderEngine();

    private Display display = null;

    public static Controller getInstance() {
        return instance;
    }

    /**
     * This class is a singleton and thus handles its creation on its own
     */
    private Controller() {
        Controller.getInstance().getRenderEngine().setAntialiasing(true);
    }

    /**
     * Returns the display instance controlled by this controller. As the method
     * is invoked first, the display instance is created and then saved
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
