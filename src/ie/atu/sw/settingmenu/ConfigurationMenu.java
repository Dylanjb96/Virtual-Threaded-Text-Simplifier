package ie.atu.sw.settingmenu;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;
import ie.atu.sw.console.ConsoleColour;
import ie.atu.sw.console.ConsolePrint;
import ie.atu.sw.embedding.DataBase;
import ie.atu.sw.utils.ConsoleUI;
import ie.atu.sw.utils.SimilarityAlgorithmFactory;

public class ConfigurationMenu {
    private Scanner s = new Scanner(System.in);
    private Configuration config;
    private DataBase dataBase;

    /**
     * Constructor for ConfigurationMenu.
     * 
     * Time Complexity: O(1)
     * - Rationale: Initializes the `config` and `dataBase` objects.
     * 
     * @param config   The configuration object.
     * @param dataBase The database object.
     */
    public ConfigurationMenu(Configuration config, DataBase dataBase) {
        this.config = config;
        this.dataBase = dataBase;
    }

    /**
     * Displays the configuration menu and handles user input.
     * 
     * Time Complexity: O(n)
     * - Rationale: Iterates over the menu options and calls associated methods
     * based on user input. The complexity depends on the user's choices.
     */
    public void display() {
        while (true) {
            String title = "Configuration Menu";
            String[] options = {
                    "Select similarity algorithms",
                    "Print current configuration",
                    "Specify new Word-Embedding file",
                    "Specify new Google-1000 file",
                    "Specify new Output file",
                    "Set configuration to default",
                    "Set word replacement method",
                    "Back to Main Menu"
            };
            ConsoleUI.printConfigMenu(title, options);
            System.out.print(ConsoleColour.PURPLE_BOLD + "Enter your choice: " + ConsoleColour.RESET);

            try {
                int choice = s.nextInt();
                s.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        configureAlgorithms();
                        break;
                    case 2:
                        config.printConfiguration();
                        break;
                    case 3:
                        configureWordEmbeddingFile();
                        break;
                    case 4:
                        configureGoogle1000File();
                        break;
                    case 5:
                        configureOutputFile();
                        break;
                    case 6:
                        config.resetToDefault();
                        break;
                    case 7:
                        configureWordReplacementMethod();
                        break;
                    case 8:
                        ConsolePrint.printInfo("Returning to Main Menu...");
                        return; // Back to Main Menu
                    default:
                        ConsolePrint.printError("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Configures similarity algorithms by allowing the user to select from a list.
     * 
     * Time Complexity: O(m)
     * - Rationale: Displays `m` algorithms and processes the user's selections.
     */
    private void configureAlgorithms() {
        System.out.println(
                ConsoleColour.ORANGE_BOLD + "Select Similarity Algorithms (e.g., 1,3,5):" + ConsoleColour.RESET);

        String[] algorithms = { "Euclidean", "Cosine", "Jaccard", "Manhattan", "Pearson", "Chebyshev" };

        for (int i = 0; i < algorithms.length; i++) {
            System.out.println(ConsoleColour.CYAN_BOLD + "[" + (i + 1) + "] " + algorithms[i] + ConsoleColour.RESET);
        }

        try {
            String input = s.nextLine();
            String[] selections = input.split(",");
            config.getSelectedAlgorithms().clear();

            for (String sel : selections) {
                int idx = Integer.parseInt(sel.trim()) - 1;
                if (idx >= 0 && idx < algorithms.length) {
                    AbstractSimilarityAlgorithm algorithm = SimilarityAlgorithmFactory
                            .getAlgorithm(algorithms[idx].toLowerCase());
                    if (algorithm != null) {
                        config.getSelectedAlgorithms().add(algorithm);
                    }
                }
            }

            if (config.getSelectedAlgorithms().isEmpty()) {
                ConsolePrint.printWarning("No valid algorithms selected.");
            } else {
                ConsolePrint.printInfo("Selected Algorithms Updated.");
            }
        } catch (Exception e) {
            ConsolePrint.printError("Invalid selection. Please try again.");
        }
    }

    /**
     * Configures the Word-Embedding file path and loads the embeddings into the
     * database.
     * 
     * Time Complexity: O(n)
     * - Rationale: Reads `n` lines from the specified file.
     */
    private void configureWordEmbeddingFile() {
        String wordEmbeddingPath = getValidFilePath(
                ConsoleColour.ORANGE_BOLD + "Enter path for new Word-Embedding file: " + ConsoleColour.RESET);
        config.setWordEmbeddingPath(wordEmbeddingPath);
        try {
            dataBase.loadWordEmbeddings(wordEmbeddingPath);
            ConsolePrint.printInfo("Word Embeddings loaded successfully.");
        } catch (Exception e) {
            ConsolePrint.printError("Error loading Word Embeddings: " + e.getMessage());
        }
    }

    /**
     * Configures the Google-1000 file path and loads the words into the database.
     * 
     * Time Complexity: O(n)
     * - Rationale: Reads `n` lines from the specified file.
     */
    private void configureGoogle1000File() {
        String google1000Path = getValidFilePath(
                ConsoleColour.ORANGE_BOLD + "Enter path for new Google-1000 file: " + ConsoleColour.RESET);
        config.setGoogle1000Path(google1000Path);
        try {
            dataBase.loadGoogleWords(google1000Path);
            ConsolePrint.printInfo("Google-1000 words loaded successfully.");
        } catch (Exception e) {
            ConsolePrint.printError("Error loading Google-1000 words: " + e.getMessage());
        }
    }

    /**
     * Configures the output file path.
     * 
     * Time Complexity: O(1)
     * - Rationale: Sets a single string value in the configuration.
     */
    private void configureOutputFile() {
        String outputFilePath = getValidFilePath(
                ConsoleColour.ORANGE_BOLD + "Enter path for Output file: " + ConsoleColour.RESET);
        config.setOutputFilePath(outputFilePath);
        ConsolePrint.printInfo("Output file path updated.");
    }

    /**
     * Configures the word replacement method.
     * 
     * Time Complexity: O(1)
     * - Rationale: Processes a single integer input and updates the configuration.
     */
    private void configureWordReplacementMethod() {
        System.out.println(ConsoleColour.ORANGE_BOLD + "Select Word Replacement Method:" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.PURPLE_BOLD + "[1] Most Similar");
        System.out.println("[2] Least Similar");
        System.out.println("[3] Random");
        System.out.println("[0] Back to Configuration Menu" + ConsoleColour.RESET);

        try {
            int choice = s.nextInt();
            s.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    config.setReplacementMethod("Most Similar");
                    break;
                case 2:
                    config.setReplacementMethod("Least Similar");
                    break;
                case 3:
                    config.setReplacementMethod("Random");
                    break;
                case 0:
                    return;
                default:
                    ConsolePrint.printError("Invalid choice. Please select a valid option.");
            }
        } catch (InputMismatchException e) {
            ConsolePrint.printError("Invalid input. Please enter a number.");
            s.nextLine(); // Clear invalid input
        }
    }

    /**
     * Validates and retrieves a file path from the user.
     * 
     * Time Complexity: O(1) for each user input attempt.
     * - Rationale: Checks the existence of a file at the provided path.
     * 
     * @param promptMessage The message to display to the user.
     * @return A valid file path.
     */
    private String getValidFilePath(String promptMessage) {
        System.out.print(promptMessage);
        String path = s.nextLine();
        if (!new File(path).exists()) {
            ConsolePrint.printWarning("File not found. Please try again.");
            return getValidFilePath(promptMessage);
        }
        return path;
    }

}
