package ie.atu.sw.embedding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataBase {
	private Set<String> commonWords;
	private Map<String, double[]> wordVectors;

	public DataBase() {
		this.commonWords = new HashSet<>();
		this.wordVectors = new HashMap<>();
	}

	/**
	 * Loads Google-1000 words from a file into a set.
	 * 
	 * Time Complexity: O(n)
	 * - Rationale: Iterates through the file line by line where 'n' is the number
	 * of lines in the files. Each word is added to a HashSet, which takes O(1) per
	 * insertion. Overall complexity is O(n) because insertion into the HashSet is
	 * constant-time.
	 * 
	 * @param filePath Path to the Google-1000 words file.
	 */
	public void loadGoogleWords(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				commonWords.add(line.trim().toLowerCase());
			}
			System.out.println("Loaded " + commonWords.size() + " common words.");
		} catch (IOException e) {
			System.err.println("Error loading Google-1000 File: " + e.getMessage());
		}
	}

	/**
	 * Loads word embeddings from a file into a map.
	 * 
	 * Time Complexity: O(n * m)
	 * - Rationale: Iterates through the file with `n` lines. For each line,
	 * splits into token, which takes O(m). Each vector is tored in a HasMap
	 * with O(1) insertion time per entry. Overall complexity is O(n * m), where
	 * n is the number of lines and m is the average number of tokens per line.
	 * 
	 * @param filePath Path to the word embeddings file.
	 */
	public void loadWordEmbeddings(String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("[,\\s]+"); // Handles both commas and spaces
				if (parts.length < 2) {
					System.err.println("Skipping malformed line: " + line);
					continue;
				}
				String word = parts[0];
				double[] vector = new double[parts.length - 1];
				for (int i = 1; i < parts.length; i++) {
					try {
						vector[i - 1] = Double.parseDouble(parts[i]);
					} catch (NumberFormatException e) {
						System.err.println("Skipping invalid value in line: " + line);
						continue;
					}
				}
				wordVectors.put(word.toLowerCase(), vector);
			}
			System.out.println("Loaded " + wordVectors.size() + " word embeddings.");
		}
	}

	/**
	 * Checks if a word is in the common words set.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Checking for membership in a `HashSet` is a constant-time
	 * operation due to its hash-based implementation.
	 * 
	 * @param word The word to check.
	 * @return True if the word is common, false otherwise.
	 */
	public boolean isCommonWord(String word) {
		return commonWords.contains(word.toLowerCase());
	}

	/**
	 * Retrieves the vector representation of a word.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Retrieving a value from a HashMap is a constant-time
	 * operation, as it uses hashing to access the value.
	 * 
	 * @param word The word to look up.
	 * @return The vector representation of the word, or null if not found.
	 */
	public double[] getVector(String word) {
		return wordVectors.get(word.toLowerCase());
	}

	public Set<String> getCommonWords() {
		return commonWords;
	}

	public Map<String, double[]> getWordVectors() {
		return wordVectors;
	}

}
