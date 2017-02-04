package net.hybridhacker.visualslice.visualizer.beat;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.RequiredArgsConstructor;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 */
public class DrumkitBeatVisualizer implements IVisualizer {
    
    private BeatComponent[] components = null;
    
    @Override
    public void initialize() {}
    
    @Override
    public void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer,
                       final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft) {
        final int width = G2D.canvas().getWidth();
        final int height = G2D.canvas().getHeight();
        
        if (components == null) {
            // @formatter:off
            components = new BeatComponent[]
                    {
                            
                    new BeatComponent(120, 240, 10, beatDetect::isKick,
                                      comp -> {
                                            if (comp.beatSupplier.get())
                                                comp.currentAmplitude = comp.maxSize;
                                            else if (comp.currentAmplitude > comp.minSize)
                                                comp.currentAmplitude -= Math.min(comp.falloff, comp.currentAmplitude - comp.minSize);
                                            
                                            G2D.outlineOval((width - comp.currentAmplitude) / 2, (height - comp.currentAmplitude) / 2,
                                                           comp.currentAmplitude, comp.currentAmplitude, Color.DARK_GRAY);
                                      }),
                    
                    new BeatComponent(60, 120, 10, beatDetect::isHat,
                                      comp -> {
                                            if (comp.beatSupplier.get())
                                                comp.currentAmplitude = comp.maxSize;
                                            else if (comp.currentAmplitude > comp.minSize)
                                                comp.currentAmplitude -= Math.min(comp.falloff, comp.currentAmplitude - comp.minSize);
                                            
                                            G2D.outlineOval((width - comp.currentAmplitude) / 2 - 70,
                                                            (height - comp.currentAmplitude) / 2 - 30,
                                                           comp.currentAmplitude, comp.currentAmplitude, Color.GRAY);
                                            G2D.outlineOval((width - comp.currentAmplitude) / 2 + 70,
                                                            (height - comp.currentAmplitude) / 2 - 30,
                                                           comp.currentAmplitude, comp.currentAmplitude, Color.GRAY);
                                      }),
                        
                    };
            // @formatter:on
        }
        
        // draw beat components
        Arrays.stream(components).forEach(c -> c.drawer.accept(c));
    }
    
    @Override
    public String getName() {
        return "Drum Kit";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[0];
    }
    
    @RequiredArgsConstructor
    private class BeatComponent {
        private final int minSize;
        private final int maxSize;
        private final int falloff;
        private final Supplier<Boolean> beatSupplier;
        private final Consumer<BeatComponent> drawer;
        
        private int currentAmplitude;
    }
}
