package net.hybridhacker.visualslice.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A convenience class for the Apache cli interface
 */
public class CommandLineInterface {
    
    private final Options options;
    
    private final HashMap<String, Consumer<Optional<String>>> handlers = new HashMap<>();
    
    public CommandLineInterface() {
        this.options = new Options();
    }
    
    /**
     * Add an option to the CLI
     *
     * @param name        name of the option
     * @param description description of the option
     * @param required    whether the option is required
     * @param handler     a handler function for the parameter
     */
    public void addOption(final String name, final String description, final boolean required, final Consumer<Optional<String>> handler) {
        this.options.addOption(Option.builder().desc(description).longOpt(name).required(required).build());
        this.handlers.put(name, handler);
    }
    
    /**
     * Add an option with an argument to the CLI
     *
     * @param name         name of the option
     * @param description  description of the option
     * @param argumentName name of the argument
     * @param required     whether the option is required
     * @param handler      a handler function for the parameter
     */
    public void addOption(final String name, final String description, final String argumentName, final boolean required,
                          final Consumer<Optional<String>> handler) {
        this.options
                .addOption(Option.builder().desc(description).longOpt(name).hasArg(true).argName(argumentName).required(required).build());
        this.handlers.put(name, handler);
    }
    
    /**
     * Parse the command line and execute the respective handlers
     *
     * @param arguments command line arguments
     *
     * @throws ParseException if the command line arguments are of unexpected syntax
     */
    public void parse(final String... arguments) throws ParseException {
        final CommandLineParser commandLineParser = new DefaultParser();
        final CommandLine commandLine = commandLineParser.parse(this.options, arguments);
        
        this.handlers.keySet().stream().filter(commandLine::hasOption)
                     .forEach(key -> handlers.get(key).accept(Optional.ofNullable(commandLine.getOptionValue(key, null))));
    }
    
    /**
     * Print the help for the current set up CLI
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("VisualSlice", this.options);
    }
}
