package ie.atu.sw.utils;

import ie.atu.sw.console.ConsoleColour;

/**
 * Utility class for handling console-based user interface functionalities.
 */
public class ConsoleUI {
        /**
         * Prints a menu with a title and a list of options.
         *
         * Time Complexity: O(n)
         * - Rationale: The method iterates over the `options` array, printing each
         * option along with its index. Each print statement is \(O(1)\), making the
         * overall complexity \(O(n)\), where \(n\) is the number of options.
         *
         * @param title   The title of the menu.
         * @param options The menu options to display.
         */
        public static void printMenu(String title, String[] options) {
                System.out.println(
                                ConsoleColour.CYAN_BOLD + "==================================================="
                                                + ConsoleColour.RESET);
                System.out.println(ConsoleColour.ORANGE_BOLD + title + ConsoleColour.RESET);
                System.out.println(
                                ConsoleColour.CYAN_BOLD + "==================================================="
                                                + ConsoleColour.RESET);

                // Print each menu option in the same color
                for (int i = 0; i < options.length; i++) {
                        System.out
                                        .println(ConsoleColour.BLUE_BOLD.toString() + (i + 1) + ". " + options[i]
                                                        + ConsoleColour.RESET);
                }

                // Print a footer
                System.out.println(
                                ConsoleColour.CYAN_BOLD + "==================================================="
                                                + ConsoleColour.RESET);
        }

        /**
         * Prints a configuration menu with a title and a list of options.
         *
         * Time Complexity: O(n)
         * - Rationale: Similar to `printMenu`, this method iterates over the `options`
         * array and prints each option. The iteration contributes \(O(n)\), where
         * \(n\) is the number of options.
         *
         * @param title   The title of the configuration menu.
         * @param options The configuration menu options to display.
         */
        public static void printConfigMenu(String title, String[] options) {
                System.out.println(
                                ConsoleColour.SUNRISE_BOLD + "==================================================="
                                                + ConsoleColour.RESET);
                System.out.println(ConsoleColour.ORANGE_BOLD + title + ConsoleColour.RESET);
                System.out.println(
                                ConsoleColour.SUNRISE_BOLD + "==================================================="
                                                + ConsoleColour.RESET);

                // Print each menu option in the same color
                for (int i = 0; i < options.length; i++) {
                        System.out
                                        .println(ConsoleColour.CYAN_BOLD.toString() + (i + 1) + ". " + options[i]
                                                        + ConsoleColour.RESET);
                }

                // Print a footer
                System.out.println(
                                ConsoleColour.SUNRISE_BOLD + "==================================================="
                                                + ConsoleColour.RESET);
        }

        /**
         * Prints a message with a specified color.
         *
         * Time Complexity: O(1)
         * - Rationale: The method prints a single line of text using the specified
         * color. Printing a line is a constant-time operation.
         *
         * @param message The message to print.
         * @param color   The color in which to print the message.
         */
        public static void printMessage(String message, ConsoleColour color) {
                System.out.println(color + message + ConsoleColour.RESET);
        }

}
