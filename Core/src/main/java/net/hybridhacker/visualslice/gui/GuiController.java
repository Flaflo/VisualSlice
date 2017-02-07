package net.hybridhacker.visualslice.gui;

import lombok.Getter;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.visualizer.builder.GenericBuilder;

/**
 *
 * @author Flaflo
 */
public final class GuiController {

    private static final GuiController INSTANCE = new GuiController();

    @Getter
    private final GenericBuilder builder = new GenericBuilder();

    @Getter
    private final MusicPlayer player = new MusicPlayer();
    
    @Getter
    private final Display display = new Display("VisualSlice", 400, 400, 120);
    
    public static GuiController getInstance() {
        return INSTANCE;
    }
}
