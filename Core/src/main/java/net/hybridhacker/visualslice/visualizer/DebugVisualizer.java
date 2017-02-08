package net.hybridhacker.visualslice.visualizer;

import ddf.minim.analysis.FFT;
import lombok.Getter;
import lombok.Setter;
import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;

/**
 * A visualizer for debugging purpose
 */
public class DebugVisualizer implements IVisualizer {
    
    @Setter
    @Getter
    private Color color;
    
    public DebugVisualizer() {
        this.color = Color.BLACK;
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void onDraw(final int playerLength, final int playerPosition, final float[] leftAudioBuffer,
                       final float[] rightAudioBuffer, final float[] mixAudioBuffer, final FFT fft) {
        
        final int bufferSize = mixAudioBuffer.length;
        
        final Color color2 = this.getColor().brighter();
        
        { //Volume
            for (int i = 0; i < bufferSize - 1; i++) {
                Controller.getInstance().getRenderEngine().setColor(color2);
                Controller.getInstance().getRenderEngine()
                          .drawLine(i, (int) (50 + leftAudioBuffer[i] * 20), i, (int) (50 - leftAudioBuffer[i] * 20));
                Controller.getInstance().getRenderEngine()
                          .drawLine(i, (int) (50 + rightAudioBuffer[i] * 20), i, (int) (50 - rightAudioBuffer[i] * 20));
                
                Controller.getInstance().getRenderEngine().setColor(this.getColor());
                Controller.getInstance().getRenderEngine()
                          .drawLine(i, (int) (50 + leftAudioBuffer[i] * 5), i, (int) (50 - leftAudioBuffer[i] * 5));
                Controller.getInstance().getRenderEngine()
                          .drawLine(i, (int) (50 + rightAudioBuffer[i] * 5), i, (int) (50 - rightAudioBuffer[i] * 5));
            }
        }
        
        { //FFT Bands
            final double scale = 0.8;
            final int min = 2;
            final int width = 20;
            final int gap = 2;
            
            Controller.getInstance().getRenderEngine().setColor(color2);
            for (int i = 0; i < Controller.getInstance().getDisplay().getWidth() / (width + gap); i++) {
                final int height = (int) Math.max(fft.getBand(i * width) * scale, min);
                Controller.getInstance().getRenderEngine()
                          .fillRect(i * (width + gap), Controller.getInstance().getDisplay().getHeight() - height, width, height);
            }
        }
        
        { //Player progress 
            final int width =
                    (int) Math.round(((double) playerPosition / (double) playerLength) * Controller.getInstance().getDisplay().getWidth());
            final int height = 4;
            
            Controller.getInstance().getRenderEngine().setColor(color);
            Controller.getInstance().getRenderEngine().fillRect(0, 0, Controller.getInstance().getDisplay().getWidth(), height);
            Controller.getInstance().getRenderEngine().setColor(color2);
            Controller.getInstance().getRenderEngine().fillRect(0, 0, width, height);
        }
    }
    
    @Override
    public String getName() {
        return "Debug Visualizer";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[0];
    }
}
