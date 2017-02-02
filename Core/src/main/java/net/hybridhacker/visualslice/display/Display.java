package net.hybridhacker.visualslice.display;

import lombok.Data;
import net.hybridhacker.visualslice.utils.G2D;

/**
 * Display for VisualSlice
 *
 * @author Flaflo
 */
@Data
public final class Display extends Thread {

    private static final int BUFFERS = 3;

    private final String title;
    private final int width, height;

    @Override
    public void run() {
        G2D.window(this.getTitle(), this.getWidth(), this.getHeight(), false, true);
        G2D.init(BUFFERS);

        while (G2D.window().isShowing()) {
            G2D.push();
            G2D.high();

            G2D.pop();
        }
    }
}
