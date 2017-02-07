package net.hybridhacker.visualslice.gui.settings;

import lombok.Data;

/**
 * A data class for all display settings
 */
@Data
public class DisplaySettings {
    private int width = 400, height = 400;
    private int fps = 120;
}
