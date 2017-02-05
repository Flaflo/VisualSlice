package net.hybridhacker.visualslice.plugins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for plugins that will create a meta file at compile time which holds information required for plugin loading
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AutoRegister {
    public static final String PLUGIN_META_FILE = ".plugin";
}
