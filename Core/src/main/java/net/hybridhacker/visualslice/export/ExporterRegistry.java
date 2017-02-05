package net.hybridhacker.visualslice.export;

import java.util.HashMap;
import java.util.Map;

/**
 * A singleton registry for exporters
 */
public class ExporterRegistry {
    
    private final static ExporterRegistry instance;
    
    private final Map<String, AbstractExporter> registeredExporters = new HashMap<>();
    
    static {
        instance = new ExporterRegistry();
    }
    
    /**
     * This class is a singleton and thus handles instantiating only itself
     */
    private ExporterRegistry() {
        // intentionally empty
    }
    
    /**
     * @return the singleton instance of this class
     */
    public static ExporterRegistry getInstance() {
        return instance;
    }
    
    /**
     * Register an exporter
     */
    public void registerExporter(final AbstractExporter exporter) {
        this.registeredExporters.put(exporter.getName(), exporter);
    }
    
    /**
     * @return the names of all registered exporters
     */
    public String[] getRegisteredExporterNames() {
        return this.registeredExporters.keySet().toArray(new String[this.registeredExporters.keySet().size()]);
    }
    
    /**
     * @param name name of the exporter
     *
     * @return the exporter registered with this name
     */
    public AbstractExporter getExporterByName(final String name) {
        return this.registeredExporters.get(name);
    }
}
