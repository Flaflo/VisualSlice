package net.hybridhacker.visualslice.renderer;

import java.awt.*;

/**
 *
 */
public interface IRenderEngine {
    
    /**
     * Initialize the render engine for given canvas dimensions and buffer strategy
     *
     * @param width       canvas width
     * @param height      canvas height
     * @param bufferCount amount of buffers
     */
    public void init(final int width, final int height, final int bufferCount);
    
    /**
     * Called when the render pass begins
     */
    public void initRenderPass();
    
    /**
     * Called when all render code was executed and the render pass is over
     */
    public void endRenderPass();
    
    /**
     * Scale the drawing calls by a factor
     *
     * @param scaleX x scale factor
     * @param scaleY y scale factor
     */
    public void scale(final int scaleX, final int scaleY);
    
    /**
     * Translate drawing calls by an offset
     *
     * @param dX x offset
     * @param dY y offset
     */
    public void translate(final int dX, final int dY);
    
    /**
     * @param string a string
     *
     * @return the height of given string if it would be rendered now
     */
    public int getStringHeight(final String string);
    
    /**
     * @param string a string
     *
     * @return the width of given string if it would be rendered now
     */
    public int getStringWidth(final String string);
    
    /**
     * @param string a string
     * @param size   font size
     *
     * @return the width of given string if it would be rendered with given size
     */
    public int getStringWidth(final String string, final float size);
    
    /**
     * Set the global font size
     *
     * @param size font size
     */
    public void setFontSize(final float size);
    
    /**
     * @return the globally set fontsize
     */
    public float getFontSize();
    
    /**
     * Reset the font to the state it had before the last change of font
     */
    public void resetFont();
    
    /**
     * Set the global drawing color
     *
     * @param color a {@link Color} object
     */
    public void setColor(final Color color);
    
    /**
     * @return the global drawing color
     */
    public Color getColor();
    
    /**
     * Reset the drawing color to the state it had before the last Color change
     */
    public void resetColor();
    
    /**
     * Set the global clear color
     *
     * @param color a {@link Color} object
     */
    public void setClearColor(final Color color);
    
    /**
     * @return the global clear color
     */
    public Color getClearColor();
    
    /**
     * Render a given string at given coordinates at given size with given color
     *
     * @param text a string
     * @param x    x coordinate
     * @param y    y coordinate
     * @param size font size
     */
    public void text(final String text, final int x, final int y, final float size);
    
    /**
     * Render a given string at given coordinates using the current set font in global color
     *
     * @param text a string
     * @param x    x coordinate
     * @param y    y coordinate
     */
    public void text(final String text, final int x, final int y);
    
    /**
     * Clear the whole canvas with set clear color
     */
    public void clear();
    
    /**
     * Clear a rectangle area with global clear color
     *
     * @param x      area top left x coordinate
     * @param y      area top left y coordinate
     * @param width  area width
     * @param height area height
     */
    public void clear(final int x, final int y, final int width, final int height);
    
    /**
     * Fill a rectangle area with the drawing color
     *
     * @param x      area top left x coordinate
     * @param y      area top left y coordinate
     * @param width  area width
     * @param height area height
     */
    public void fillRect(final int x, final int y, final int width, final int height);
    
    /**
     * Outline a rectangle area with the drawing color
     *
     * @param x      area top left x coordinate
     * @param y      area top left y coordinate
     * @param width  area width
     * @param height area height
     */
    public void drawRect(final int x, final int y, final int width, final int height);
    
    /**
     * Fill an rectangle area with the drawing color and outline it with given border Color
     *
     * @param x           area top left x coordinate
     * @param y           area top left y coordinate
     * @param width       area width
     * @param height      area height
     * @param borderColor color for the outlining
     */
    public default void outlinedFilledRect(final int x, final int y, final int width, final int height, final Color borderColor) {
        this.fillRect(x, y, width, height);
        this.setColor(borderColor);
        this.drawRect(x, y, width, height);
        this.resetColor();
    }
    
    /**
     * Fill an oval area with the drawing color
     *
     * @param x      area top left x coordinate
     * @param y      area top left y coordinate
     * @param width  area width
     * @param height area height
     */
    public void fillOval(final int x, final int y, final int width, final int height);
    
    /**
     * Outline an oval area with the drawing color
     *
     * @param x      area top left x coordinate
     * @param y      area top left y coordinate
     * @param width  area width
     * @param height area height
     */
    public void drawOval(final int x, final int y, final int width, final int height);
    
    /**
     * Fill an oval area with the drawing color and outline it with given border Color
     *
     * @param x           area top left x coordinate
     * @param y           area top left y coordinate
     * @param width       area width
     * @param height      area height
     * @param borderColor color for the outlining
     */
    public default void outlinedFilledOval(final int x, final int y, final int width, final int height, final Color borderColor) {
        this.fillOval(x, y, width, height);
        this.setColor(borderColor);
        this.drawOval(x, y, width, height);
        this.resetColor();
    }
    
    /**
     * Draw a line between given coordinates
     *
     * @param x1 first point's x coordinate
     * @param y1 first point's y coordinate
     * @param x2 second point's x coordinate
     * @param y2 second point's y coordinate
     */
    public void line(final int x1, final int y1, final int x2, final int y2);
    
    /**
     * Draw an image at the given coordinates not changing it dimensions
     *
     * @param x image's position top left x coordinate
     * @param y image's position top left y coordinate
     */
    public void drawImage(final int x, final int y, final Image image);
    
    /**
     * Draw an image at given coordinates scaling it to given dimensions
     *
     * @param x      image's position top left x coordinate
     * @param y      image's position top left y coordinate
     * @param width  drawn image's width
     * @param height draw image's height
     */
    public void drawImage(final int x, final int y, final int width, final int height);
    
    /**
     * @return the canvas this engine is drawing onto
     */
    public Canvas getCanvas();
}
