package net.hybridhacker.visualslice.utils;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphics 2D Utility, oriented on LWJGL syntax
 *
 * @author Flaflo
 */
public final class G2D {

    private static volatile JFrame frame;
    private static volatile Canvas canvas;
    private static volatile BufferStrategy strategy;
    private static volatile Graphics2D graphics;
    private static volatile Color reversableColor;
    private static volatile Font reversableFont;

    private static volatile boolean mouseLeft, mouseRight;
    private static volatile int mouseButton = -1;

    public static BufferedImage canvasTexture() {
        final BufferedImage bufferedImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics graphics = bufferedImage.getGraphics();
        canvas.paintAll(graphics);
        
        return bufferedImage;
    }

    public static Graphics2D graphics() {
        return graphics;
    }

    public static int mouseButton() {
        return mouseButton;
    }

    public static boolean mouseLeft() {
        return mouseLeft;
    }

    public static boolean mouseRight() {
        return mouseRight;
    }

    public static void translate(int tx, int ty) {
        if (!valid()) {
            return;
        }

        graphics.translate(tx, ty);
    }

    public static void scale(int sx, int sy) {
        if (!valid()) {
            return;
        }

        graphics.scale(sx, sy);
    }

    public static int stringHeight(String str) {
        if (!valid()) {
            return 0;
        }

        return graphics.getFontMetrics().getHeight();
    }

    public static int stringWidth(String str) {
        if (!valid()) {
            return 0;
        }

        return graphics.getFontMetrics().stringWidth(str);
    }

    public static int stringWidth(String str, float size) {
        if (!valid()) {
            return 0;
        }

        fontSize(size);
        final int width = graphics.getFontMetrics().stringWidth(str);
        resetFont();

        return width;
    }

    public static void fontSize(float size) {
        if (!valid()) {
            return;
        }

        reversableFont = graphics.getFont();

        graphics.setFont(graphics.getFont().deriveFont(size));
    }

    public static void resetFont() {
        if (!valid()) {
            return;
        }

        graphics.setFont(reversableFont);
    }

    public static void text(String text, int x, int y, float size, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        fontSize(size);
        graphics.drawString(text, x, y);
        resetFont();
        color();
    }

    public static void text(String text, int x, int y, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        graphics.drawString(text, x, y);
        color();
    }

    public static Color reversable() {
        return reversableColor;
    }

    public static void color() {
        if (!valid()) {
            return;
        }

        graphics.setColor(reversableColor);
    }

    public static void color(Color color) {
        if (!valid()) {
            return;
        }

        reversableColor = graphics.getColor();
        graphics.setColor(color);
    }

    public static void clear(Color color) {
        if (!valid()) {
            return;
        }

        rect(0, 0, canvas.getWidth(), canvas.getHeight(), color);
    }

    public static void clear(int x, int y, int width, int height) {
        if (!valid()) {
            return;
        }

        graphics.clearRect(x, y, width, height);
    }

    public static void rect(int x, int y, int width, int height, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        graphics.fillRect(x, y, width, height);
        color();
    }

    public static void outlineRect(int x, int y, int width, int height, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        graphics.drawRect(x, y, width, height);
        color();
    }

    public static void oval(int x, int y, int width, int height, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        graphics.fillOval(x, y, width, height);
        color();
    }

    public static void outlineOval(int x, int y, int width, int height, Color color) {
        if (!valid()) {
            return;
        }

        color(color);
        graphics.drawOval(x, y, width, height);
        color();
    }

    public static void line(int x, int y, int x1, int y1, Color color) {
        if (!valid()) {
            return;
        }
        color(color);
        graphics.drawLine(x, y, x1, y1);
        color();
    }

    public static void texture(int x, int y, int width, int height, BufferedImage image) {
        if (!valid()) {
            return;
        }

        graphics.drawImage(image, x, y, width, height, null);
    }

    public static void texture(int x, int y, BufferedImage image) {
        if (!valid()) {
            return;
        }

        graphics.drawImage(image, x, y, null);
    }

    public static void high() {
        if (!valid()) {
            return;
        }

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    }

    public static void init(Canvas canvas, int buffers) {
        final JPanel panel = new JPanel();

        panel.add(canvas);

        if (canvas.getBufferStrategy() == null) {
            canvas.createBufferStrategy(buffers);
        }

        G2D.canvas = canvas;
        strategy = canvas.getBufferStrategy();
        graphics = (Graphics2D) strategy.getDrawGraphics();
    }

    public static void init(int buffers) {
        frame.add(G2D.canvas(frame.getWidth(), frame.getHeight()));

        if (canvas.getBufferStrategy() == null) {
            canvas.createBufferStrategy(buffers);
        }

        strategy = canvas.getBufferStrategy();
        graphics = (Graphics2D) strategy.getDrawGraphics();
    }

    public static void push() {
        if (!valid()) {
            return;
        }

        graphics = (Graphics2D) strategy.getDrawGraphics();
        clear(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void pop() {
        if (!valid()) {
            return;
        }

        strategy.getDrawGraphics().dispose();
        strategy.show();
    }

    public static JFrame window(String title, int width, int height, boolean resizeable, boolean centered) {
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }

        final JFrame jframe = new JFrame(title);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(new Dimension(width, height));

        if (centered) {
            jframe.setLocationRelativeTo(null);
        }

        jframe.setResizable(resizeable);
        jframe.setVisible(true);

        frame = jframe;

        return jframe;
    }

    public static JFrame window() {
        return frame;
    }

    public static Canvas canvas(int width, int height) {
        if (canvas != null && frame != null) {
            frame.remove(canvas);
        }

        final Canvas ccanvas = new Canvas();
        ccanvas.setPreferredSize(new Dimension(width, height));
        ccanvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent a) {
            }

            @Override
            public void mouseEntered(MouseEvent a) {
            }

            @Override
            public void mouseExited(MouseEvent a) {
            }

            @Override
            public void mousePressed(MouseEvent a) {
                if (a.getButton() == 1) {
                    mouseLeft = true;
                } else if (a.getButton() == 3) {
                    mouseRight = true;
                }
                mouseButton = a.getButton();
            }

            @Override
            public void mouseReleased(MouseEvent a) {
                if (a.getButton() == 1) {
                    mouseLeft = false;
                } else if (a.getButton() == 3) {
                    mouseRight = false;
                }
                mouseButton = -1;
            }
        });

        canvas = ccanvas;

        return ccanvas;
    }

    public static Canvas canvas() {
        return canvas;
    }

    public static boolean valid() {
        return canvas != null && strategy != null && graphics != null;
    }

}
