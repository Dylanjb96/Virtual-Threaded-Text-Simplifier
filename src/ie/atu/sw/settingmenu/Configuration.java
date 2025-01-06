package ie.atu.sw.settingmenu;

import java.util.ArrayList;
import java.util.List;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;
import ie.atu.sw.console.ConsoleColour;
import ie.atu.sw.console.ConsolePrint;
import ie.atu.sw.utils.SimilarityAlgorithmFactory;

public class Configuration {
    private String wordEmbeddingPath;
    private String google1000Path;
    private String outputFilePath;
    private List<AbstractSimilarityAlgorithm> selectedAlgorithms = new ArrayList<>();
    private String replacementMethod = "Most Similar";

    public Configuration() {
        /**
         * Constructor for Configuration class. Default similarity algorithm
         * 
         * Time Complexity: O(1)
         * - Rationale: Initializes the list and adds a default algorithm, which are
         * constant-time operations.
         */
        selectedAlgorithms.add(SimilarityAlgorithmFactory.getAlgorithm("Cosine"));
    }

    /**
     * Gets the path to the word embedding file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Simple getter method that retrieves a string value.
     */
    public String getWordEmbeddingPath() {
        return wordEmbeddingPath;
    }

    /**
     * Sets the path to the word embedding file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Assigns a value to a variable and prints a message.
     * 
     * @param wordEmbeddingPath Path to the word embedding file.
     */
    public void setWordEmbeddingPath(String wordEmbeddingPath) {
        this.wordEmbeddingPath = wordEmbeddingPath;
        ConsolePrint.printInfo("Word Embedding Path set to: " + wordEmbeddingPath);
    }

    /**
     * Gets the path to the Google-1000 file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Simple getter method that retrieves a string value.
     */
    public String getGoogle1000Path() {
        return google1000Path;
    }

    /**
     * Sets the path to the Google-1000 file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Assigns a value to a variable and prints a message.
     * 
     * @param google1000Path Path to the Google-1000 file.
     */
    public void setGoogle1000Path(String google1000Path) {
        this.google1000Path = google1000Path;
        ConsolePrint.printInfo("Google-1000 Path set to: " + google1000Path);
    }

    /**
     * Gets the path to the output file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Simple getter method that retrieves a string value.
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Sets the path to the output file.
     * 
     * Time Complexity: O(1)
     * - Rationale: Assigns a value to a variable and prints a message.
     * 
     * @param outputFilePath Path to the output file.
     */
    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        ConsolePrint.printInfo("Output File Path set to: " + outputFilePath);
    }

    /**
     * Gets the list of selected similarity algorithms.
     * 
     * Time Complexity: O(1)
     * - Rationale: Returns a reference to the list, which is a constant-time
     * operation.
     */
    public List<AbstractSimilarityAlgorithm> getSelectedAlgorithms() {
        return selectedAlgorithms;
    }

    /**
     * Sets the list of selected similarity algorithms.
     * 
     * Time Complexity: O(n)
     * - Rationale: Clears the existing list and adds new algorithms. Both
     * operations are linear in the size of the list.
     * 
     * @param algorithms List of similarity algorithms to set.
     */
    public void setSelectedAlgorithms(List<AbstractSimilarityAlgorithm> algorithms) {
        this.selectedAlgorithms = new ArrayList<>(algorithms);
    }

    /**
     * Gets the replacement method.
     * 
     * Time Complexity: O(1)
     * - Rationale: Simple getter method that retrieves a string value.
     */
    public String getReplacementMethod() {
        return replacementMethod;
    }

    /**
     * Sets the replacement method.
     * 
     * Time Complexity: O(1)
     * - Rationale: Assigns a value to a variable and prints a message.
     * 
     * @param replacementMethod The replacement method to set.
     */
    public void setReplacementMethod(String replacementMethod) {
        this.replacementMethod = replacementMethod;
        ConsolePrint.printInfo("Replacement Method set to: " + replacementMethod);
    }

    /**
     * Prints the current configuration.
     * 
     * Time Complexity: O(n)
     * - Rationale: Iterates through the list of selected algorithms to print their
     * names.
     */
    public void printConfiguration() {
        System.out.println(ConsoleColour.YELLOW_BOLD + "===================================================");
        System.out.println("Current Configuration:");
        System.out.println("===================================================" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.CYAN_BOLD + "Word-Embedding File: " +
                (wordEmbeddingPath != null ? ConsoleColour.GREEN_BOLD + wordEmbeddingPath
                        : ConsoleColour.RED_BRIGHT + "Not Set")
                + ConsoleColour.RESET);
        System.out.println(ConsoleColour.CYAN_BOLD + "Google-1000 File: " +
                (google1000Path != null ? ConsoleColour.GREEN_BOLD + google1000Path
                        : ConsoleColour.RED_BRIGHT + "Not Set")
                + ConsoleColour.RESET);
        System.out.println(ConsoleColour.CYAN_BOLD + "Output File: " +
                (outputFilePath != null ? ConsoleColour.GREEN_BOLD + outputFilePath
                        : ConsoleColour.RED_BRIGHT + "Not Set")
                + ConsoleColour.RESET);
        System.out.println(ConsoleColour.CYAN_BOLD + "Selected Similarity Algorithms: ");
        if (selectedAlgorithms.isEmpty()) {
            System.out.println(ConsoleColour.RED_BRIGHT + " - [No Algorithms Selected]" + ConsoleColour.RESET);
        } else {
            for (AbstractSimilarityAlgorithm algo : selectedAlgorithms) {
                if (algo != null) {
                    System.out.println(ConsoleColour.GREEN_BOLD + " - " + algo.getName() + ConsoleColour.RESET);
                }
            }
        }
        System.out.println(ConsoleColour.CYAN_BOLD + "Word Replacement Method: " + ConsoleColour.GREEN_BOLD
                + replacementMethod + ConsoleColour.RESET);
    }

    /**
     * Resets the configuration to default values.
     * 
     * Time Complexity: O(1)
     * - Rationale: Clears the list of algorithms and adds a default algorithm, both
     * constant-time operations.
     */
    public void resetToDefault() {
        wordEmbeddingPath = null;
        google1000Path = null;
        outputFilePath = "./output.txt";
        selectedAlgorithms.clear();
        selectedAlgorithms.add(SimilarityAlgorithmFactory.getAlgorithm("Cosine"));
        replacementMethod = "Most Similar";
        ConsolePrint.printInfo("Configuration reset to default.");
    }

    /**
     * Determines whether a word can be replaced based on the replacement method.
     * 
     * Time Complexity: O(1)
     * - Rationale: Simple logic based on the replacement method string.
     * 
     * @param word Word to check
     * @return True if replacement is allowed.
     */
    public boolean isReplacementAllowed(String word) {
        switch (replacementMethod) {
            case "Most Similar":
                return true;
            case "Least Similar":
                return true;
            case "Random":
                return Math.random() > 0.5; // Randomly allow replacement
            default:
                return true; // Default to allowing replacement
        }
    }
}
