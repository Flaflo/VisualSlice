package net.hybridhacker.visualslice.renderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

/**
 * Graphics2D render engine implementation
 *
 * @author Flaflo
 */
public class Graphics2DRenderEngine implements IRenderEngine {

    /**
     * The standard clear color
     */
    private static final Color STANDARD_CLEAR_COLOR = Color.BLACK;

    private Canvas canvas;

    private boolean antialiasing;

    private Graphics2D graphics;

    private Font font, lastFont;
    private Color color, lastColor;
    private Color clearColor;

    public Graphics2DRenderEngine() {
        this.clearColor = STANDARD_CLEAR_COLOR;
    }

    @Override
    public void init(int width, int height, int bufferCount) {
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));

        this.canvas.createBufferStrategy(bufferCount);
    }

    @Override
    public void initRenderPass() {
        this.graphics = (Graphics2D) this.canvas.getBufferStrategy().getDrawGraphics();

        if (antialiasing) {
            this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            this.graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            this.graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        } else {
            this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            this.graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
            this.graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            this.graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
        }
    }

    @Override
    public void endRenderPass() {
        this.graphics.dispose();
        this.canvas.getBufferStrategy().show();
    }

    @Override
    public void scale(int scaleX, int scaleY) {
        this.graphics.scale(scaleX, scaleY);
    }

    @Override
    public void translate(int dX, int dY) {
        this.graphics.translate(dX, dY);
    }

    @Override
    public int getStringHeight(String string) {
        return (int) this.graphics.getFontMetrics().getStringBounds(string, this.graphics).getHeight();
    }

    @Override
    public int getStringWidth(String string) {
        return (int) this.graphics.getFontMetrics().getStringBounds(string, this.graphics).getWidth();
    }

    @Override
    public void setFontSize(float size) {
        this.lastFont = this.graphics.getFont();
        this.font = this.graphics.getFont().deriveFont(size);
        this.graphics.setFont(this.font);
    }

    @Override
    public float getFontSize() {
        return this.graphics.getFont().getSize2D();
    }

    @Override
    public void resetFont() {
        this.font = this.lastFont;
        this.graphics.setFont(this.font);
    }

    @Override
    public void setColor(Color color) {
        this.graphics.setColor(color);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void resetColor() {
        this.color = this.lastColor;
        this.graphics.setColor(this.color);
    }

    @Override
    public void setClearColor(Color color) {
        this.clearColor = color;
    }

    @Override
    public Color getClearColor() {
        return this.clearColor;
    }

    @Override
    public void drawString(String text, int x, int y, float size) {
        this.setFontSize(size);
        this.drawString(text, x, y);
        this.resetFont();
    }

    @Override
    public void drawString(String text, int x, int y) {
        this.graphics.drawString(text, x, y);
    }

    @Override
    public void clear() {
        this.clearRect(0, 0, this.getCanvas().getWidth(), this.getCanvas().getHeight());
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        this.graphics.clearRect(x, y, width, height);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        this.graphics.fillRect(x, y, width, height);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        this.graphics.drawRect(x, y, width, height);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        this.graphics.fillOval(x, y, width, height);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        this.graphics.drawOval(x, y, width, height);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        this.graphics.drawImage(image, x, y, null);
    }

    @Override
    public void drawImage(Image image, int x, int y, int width, int height) {
        this.graphics.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    @Override
    public Canvas getCanvas() {
        return this.canvas;
    }
}
