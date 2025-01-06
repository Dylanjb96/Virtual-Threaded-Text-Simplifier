package ie.atu.sw.menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import ie.atu.sw.console.ConsoleColour;
import ie.atu.sw.console.ConsoleLoadingMeter;
import ie.atu.sw.console.ConsolePrint;
import ie.atu.sw.embedding.DataBase;
import ie.atu.sw.settingmenu.Configuration;
import ie.atu.sw.settingmenu.ConfigurationMenu;
import ie.atu.sw.utils.ConsoleUI;
import ie.atu.sw.utils.FileUtils;

/**
 * The Menu class manages the main user interface and application workflow.
 * It provides options for configuring the application, managing files, and
 * simplifying text.
 */
public class Menu {

	/**
	 * Scanner object for capturing user input.
	 */
	private Scanner s = new Scanner(System.in);

	/**
	 * DataBase instance for managing word vectors and common words.
	 */
	private DataBase dataBase = new DataBase();

	/**
	 * Configuration instance for storing application settings.
	 */
	private Configuration config = new Configuration();

	/**
	 * ConfigurationMenu instance for handling configuration options.
	 */
	private ConfigurationMenu configMenu = new ConfigurationMenu(config, dataBase);

	/**
	 * Path to the word embedding file specified by the user.
	 */
	private String wordEmbeddingPath;

	/**
	 * Path to the Google-1000 file specified by the user.
	 */
	private String google1000Path;

	/**
	 * Path to the input file specified by the user.
	 */
	private String inputFilePath;

	/**
	 * Path to the output file specified by the user. Defaults to "./output.txt".
	 */
	private String outputFilePath = "./output.txt";

	/**
	 * Displays the application heading and starts the menu display.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Prints a fixed string to the console and invokes the
	 * `displayMenu` method.
	 */
	public void heading() {
		ConsolePrint.printTitle("************************************************************\n" + //
				"*     ATU - Dept. of Computer Science & Applied Physics    *\n" + //
				"*                                                          *\n" + //
				"*             Virtual Threaded Text Simplifier             *\n" + //
				"*                                                          *\n" + //
				"************************************************************");

		System.out.println(ConsoleColour.RESET);

		displayMenu();
	}

	/**
	 * Displays the main menu and handles user input.
	 * 
	 * Time Complexity: O(n)
	 * - Rationale: Continuously runs until the user exits. Each iteration processes
	 * user input and invokes appropriate methods.
	 */
	public void displayMenu() {
		boolean running = true;
		while (running) {
			String title = "Main Menu";
			String[] options = {
					"Specify a path for Word Embedding File",
					"Specify a path for Google-1000 File",
					"Specify a path for Input.txt to Analyze",
					"Specify a path for Output file (default: ./output.txt)",
					"Simplify text manually",
					"Configuration Settings",
					"Quit"
			};
			ConsoleUI.printMenu(title, options);
			System.out.print(ConsoleColour.PURPLE_BOLD + "Enter your choice: " + ConsoleColour.RESET);

			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			if (choice == 7) {
				running = false; // Exit the loop
				ConsolePrint.printInfo("Exiting. Goodbye!");
			} else {
				handleOption(choice); // Handle the choice
			}
		}
	}

	/**
	 * Handles the user's menu selection.
	 * 
	 * Time Complexity: O(1) for each case.
	 * - Rationale: Each case handles a specific user action, such as validating
	 * paths or invoking other methods.
	 */
	private void handleOption(int option) {
		switch (option) {
			case 1:
				System.out.print(ConsoleColour.YELLOW + "Enter path for Word Embedding File: " + ConsoleColour.RESET);
				wordEmbeddingPath = s.nextLine();
				if (FileUtils.validatePath(wordEmbeddingPath)) {
					config.setWordEmbeddingPath(wordEmbeddingPath);
					simulateFileLoading("Word Embedding File");
					ConsolePrint.printInfo("Word Embedding Path set successfully.");
				} else {
					ConsolePrint.printError("Invalid path for Word Embedding File.");
				}
				break;

			case 2:
				System.out.print(ConsoleColour.YELLOW + "Enter path for Google-1000 File: " + ConsoleColour.RESET);
				google1000Path = s.nextLine();
				if (FileUtils.validatePath(google1000Path)) {
					config.setGoogle1000Path(google1000Path);
					simulateFileLoading("Google-1000 File");
					ConsolePrint.printInfo("Google-1000 Path set successfully.");
				} else {
					ConsolePrint.printError("Invalid path for Google-1000 File.");
				}
				break;

			case 3:
				System.out.print(ConsoleColour.YELLOW + "Enter path for Input File: " + ConsoleColour.RESET);
				inputFilePath = s.nextLine().trim(); // Get input file path

				if (inputFilePath.isEmpty()) {
					ConsolePrint.printError("Invalid input. Please provide a valid file path.");
					break;
				}

				try {
					File inputFile = new File(inputFilePath);
					if (!inputFile.exists()) {
						boolean fileCreated = inputFile.createNewFile();
						if (fileCreated) {
							ConsolePrint.printInfo("Input file '" + inputFilePath + "' created successfully.");
						} else {
							ConsolePrint.printError("Failed to create the input file. Check permissions.");
							break;
						}
					} else {
						ConsolePrint.printWarning("Input file already exists.");
					}

					// Optionally write default content or notify user to edit the file
					ConsolePrint.printInfo("Input file path set successfully: " + inputFilePath);

				} catch (IOException e) {
					ConsolePrint.printError("Error while creating input file: " + e.getMessage());
				}
				break;

			case 4:
				System.out.print(ConsoleColour.YELLOW + "Enter path for Output File" + ConsoleColour.RESET
						+ ConsoleColour.YELLOW_BOLD + " [Press " + ConsoleColour.RESET
						+ ConsoleColour.ORANGE_UNDERLINED + "ENTER" + ConsoleColour.RESET + ConsoleColour.YELLOW_BOLD
						+ " for default ./output.txt]: "
						+ ConsoleColour.RESET);
				outputFilePath = s.nextLine().trim(); // Get output file path

				if (outputFilePath.isEmpty()) {
					outputFilePath = "./output.txt"; // Default output file path
				}

				try {
					File outputFile = new File(outputFilePath);
					if (!outputFile.exists()) {
						boolean fileCreated = outputFile.createNewFile();
						if (fileCreated) {
							ConsolePrint.printInfo("Output file '" + outputFilePath + "' created successfully.");
						} else {
							ConsolePrint.printError("Failed to create the output file. Check permissions.");
							break;
						}
					} else {
						ConsolePrint.printWarning("Output file already exists.");
					}

					// Update the Configuration object with the new path
					config.setOutputFilePath(outputFilePath);
					ConsolePrint.printInfo("Output file path set to: " + outputFilePath);

				} catch (IOException e) {
					ConsolePrint.printError("Error while creating output file: " + e.getMessage());
				}
				break;

			case 5:
				textMenu();
				break;

			case 6:
				configMenu.display();
				break;

			case 7:
				s.close();
				System.exit(0);
				break;

			default:
				ConsolePrint.printWarning("Invalid option. Please choose a valid menu item.");
		}
	}

	/**
	 * Handles text simplification.
	 * 
	 * Time Complexity: O(n)
	 * - Rationale: Validates file paths, writes input text to a file, and processes
	 * the text using a Simplify object.
	 */
	private void textMenu() {
		if (!validateFilePaths()) {
			ConsolePrint.printWarning("Please set all required file paths before proceeding.");
			return;
		}

		if (!isDatabaseLoaded()) {
			ConsolePrint.printWarning("Cannot proceed. Database is not fully loaded.");
			return;
		}

		System.out.print(ConsoleColour.ORANGE_BOLD + "Please enter text to simplify: " + ConsoleColour.RESET);
		String text = s.nextLine();

		try {
			Files.write(Paths.get(inputFilePath), (text + System.lineSeparator()).getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			ConsolePrint.printInfo("Input text appended to: " + inputFilePath);
		} catch (IOException e) {
			ConsolePrint.printError("Error writing to input file: " + e.getMessage());
			return;
		}

		Simplify simplify = new Simplify(dataBase, config);
		String simplifiedText = simplify.simplifyText(text);

		System.out.println(ConsoleColour.SUNRISE_BOLD + "\nSimplified Text:\n" + ConsoleColour.RESET
				+ ConsoleColour.GREEN_BOLD + simplifiedText + ConsoleColour.RESET);

		try {
			Files.write(Paths.get(outputFilePath), (simplifiedText + System.lineSeparator()).getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			ConsolePrint.printInfo("Simplified text saved to: " + outputFilePath);
		} catch (IOException e) {
			ConsolePrint.printError("Error writing to output file: " + e.getMessage());
		}
	}

	/**
	 * Validates required file paths.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Checks whether paths are null or empty.
	 */
	private boolean validateFilePaths() {
		if (wordEmbeddingPath == null || wordEmbeddingPath.isEmpty()) {
			ConsolePrint.printError("Error: Word Embedding File path is not set.");
			return false;
		}
		if (google1000Path == null || google1000Path.isEmpty()) {
			ConsolePrint.printError("Error: Google-1000 File path is not set.");
			return false;
		}
		if (inputFilePath == null || inputFilePath.isEmpty()) {
			ConsolePrint.printError("Error: Input File path is not set.");
			return false;
		}
		return true;
	}

	/**
	 * Simulates file loading with progress indication.
	 * 
	 * Time Complexity: O(n)
	 * - Rationale: Progress bar runs for a fixed number of steps.
	 */
	private void simulateFileLoading(String fileName) {
		System.out.println(ConsoleColour.YELLOW_BOLD + "Loading " + ConsoleColour.RESET + ConsoleColour.CYAN_BOLD
				+ fileName + ConsoleColour.RESET + ConsoleColour.YELLOW_BOLD + "..." + ConsoleColour.RESET);

		try {
			int totalSteps = 100; // Total steps for the progress meter
			for (int step = 0; step <= totalSteps; step++) {
				ConsoleLoadingMeter.printProgress(step, totalSteps);

				// Simulate processing delay
				Thread.sleep(20);

				// Perform actual file loading for specific steps (e.g., 20%, 50%, 80%)
				if (step == 20 && "Word Embedding File".equals(fileName)) {
					dataBase.loadWordEmbeddings(wordEmbeddingPath);
				} else if (step == 50 && "Google-1000 File".equals(fileName)) {
					dataBase.loadGoogleWords(google1000Path);
				}
			}

			ConsolePrint.printInfo(fileName + " loaded successfully.");
		} catch (InterruptedException e) {
			ConsolePrint.printError("Loading interrupted: " + e.getMessage());
		} catch (Exception e) {
			ConsolePrint.printError("Error loading " + fileName + ": " + e.getMessage());
		}
	}

	/**
	 * Checks if the database is loaded.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Checks whether maps and sets are empty.
	 */
	private boolean isDatabaseLoaded() {
		if (dataBase.getWordVectors().isEmpty()) {
			ConsolePrint.printError("Error: Word embeddings are not loaded.");
			return false;
		}
		if (dataBase.getCommonWords().isEmpty()) {
			ConsolePrint.printError("Error: Common words are not loaded.");
			return false;
		}
		return true;
	}
}
