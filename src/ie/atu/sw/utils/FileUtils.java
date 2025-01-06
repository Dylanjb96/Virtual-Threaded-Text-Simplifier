package ie.atu.sw.utils;

import java.io.File;

/**
 * Utility class for file-related operations.
 * Provides methods to validate file paths and ensure files exist.
 */
public class FileUtils {
    /**
     * Validates whether a given file path corresponds to an existing file.
     *
     * Time Complexity: O(1)
     * - Rationale: The `File` constructor and `exists()`/`isFile()` methods
     * internally perform basic system-level checks, which operate in constant time
     * relative to the size of the file path string. These operations do not scale
     * with the size or contents of the file itself, only the file path's validity.
     * 
     * 'new File(filePath)' initializes a 'File' object and performs no I/O
     * operations. It simply encapsulates the file path string, which is O(1).
     * 
     * 'file.exists()' queries the filesystem to check if the file exists.
     * On most operating systems, this operation is handled as a system-level
     * call and is O(1).
     * 
     * 'file.isFile()' is another system-level call that checks if the path points
     * to a regular file, and it is also O(1).
     * 
     * Overall time complexity of the validatePath method is O(1).
     *
     * @param filePath The file path to validate.
     * @return True if the file path exists and points to a file, false otherwise.
     * @throws IllegalArgumentException if the file path is null.
     */
    public static boolean validatePath(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
