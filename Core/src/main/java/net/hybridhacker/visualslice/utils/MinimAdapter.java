package net.hybridhacker.visualslice.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Cydhra
 */
public final class MinimAdapter {

    /**
     * I assume this method is provided by processing to load resources
     *
     * @param filePath path to file to load
     *
     * @return FileInputStream with file
     */
    public InputStream createInput(String filePath) {
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not load file: " + filePath);
            return null;
        }
    }
}
