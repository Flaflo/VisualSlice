package net.hybridhacker.visualslice.plugins;

/**
 * Thrown on an illegal plugin class trying to get processed by auto register
 */
public class IllegalPluginDefinitionException extends RuntimeException {
    
    /**
     * @param description exception's description
     */
    public IllegalPluginDefinitionException(final String description) {
        super(description);
    }
}
