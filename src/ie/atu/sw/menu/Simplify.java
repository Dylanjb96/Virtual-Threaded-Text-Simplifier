package ie.atu.sw.menu;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;
import ie.atu.sw.embedding.DataBase;
import ie.atu.sw.settingmenu.Configuration;

public class Simplify {
	private DataBase database;
	private Configuration config;
	private Random random;

	/**
	 * Constructor for the Simplify class.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: The constructor initializes the `database`, `config`, and
	 * `random` variables.
	 */
	public Simplify(DataBase database, Configuration config) {
		if (config == null) {
			throw new IllegalArgumentException("Configuration cannot be null");
		}
		this.database = database;
		this.config = config;
		this.random = new Random();
	}

	/**
	 * Simplifies the input text by replacing words based on similarity algorithms
	 * and configuration.
	 * 
	 * Time Complexity: O(n * m)
	 * - Rationale:
	 * - Splitting the input text into `n` tokens is O(n).
	 * - For each token, `findBestReplacement` is called, which processes up to `m`
	 * candidates.
	 * 
	 * @param inputText The text to simplify.
	 * @return The simplified text.
	 */
	public String simplifyText(String inputText) {
		StringBuilder simplifiedText = new StringBuilder();
		String[] tokens = inputText.split("(?=\\p{Punct})|(?<=\\p{Punct})|\\s+");

		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];

			if (isPunctuation(token)) {
				simplifiedText.append(token); // Append punctuation directly without a space
			} else {
				String replacement = findBestReplacement(token.trim());
				simplifiedText.append(replacement);
			}

			// Add a space after each token unless it's punctuation or the last token
			if (i < tokens.length - 1 && !isPunctuation(tokens[i + 1])) {
				simplifiedText.append(" ");
			}
		}

		return simplifiedText.toString().trim();
	}

	/**
	 * Checks if a token is punctuation.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: The regular expression check is a constant-time operation.
	 * 
	 * @param token The token to check.
	 * @return True if the token is punctuation, false otherwise.
	 */
	private boolean isPunctuation(String token) {
		return token.matches("\\p{Punct}");
	}

	/**
	 * Finds the best replacement for a given word based on the configuration.
	 * 
	 * Time Complexity: O(m * k)
	 * - Rationale:
	 * - Filtering candidates from the database involves iterating over `m` words:
	 * O(m).
	 * - For each selected similarity algorithm, `k` candidates are compared: O(k).
	 * 
	 * @param word The word to replace.
	 * @return The best replacement for the word.
	 */
	private String findBestReplacement(String word) {
		double[] targetVector = database.getVector(word.toLowerCase());
		if (targetVector == null || database.isCommonWord(word)) {
			return word.toLowerCase(); // Return original word if no vector or it's common
		}

		Map<String, double[]> candidates = database.getWordVectors()
				.entrySet()
				.stream()
				.filter(entry -> database.isCommonWord(entry.getKey())) // Only common words
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (candidates.isEmpty()) {
			return word.toLowerCase(); // Fallback to original word if no candidates
		}

		String replacement = null;
		double bestScore = config.getSelectedAlgorithms().stream()
				.anyMatch(AbstractSimilarityAlgorithm::isHigherBetter) ? -Double.MAX_VALUE : Double.MAX_VALUE;

		// Iterate over all selected algorithms
		for (AbstractSimilarityAlgorithm algorithm : config.getSelectedAlgorithms()) {

			for (Map.Entry<String, double[]> entry : candidates.entrySet()) {
				double score = algorithm.calculate(targetVector, entry.getValue());
				if (algorithm.isHigherBetter() ? score > bestScore : score < bestScore) {
					bestScore = score;
					replacement = entry.getKey();
				}
			}
		}

		switch (config.getReplacementMethod().toLowerCase()) {
			case "most similar":
				replacement = findMostSimilar(targetVector, candidates);
				break;
			case "least similar":
				replacement = findLeastSimilar(targetVector, candidates);
				break;
			case "random":
				replacement = findRandomReplacement(candidates);
				break;
			default:
				replacement = word.toLowerCase();
		}

		return replacement != null ? replacement : word;
	}

	/**
	 * Finds the most similar word using the selected algorithms.
	 * 
	 * Time Complexity: O(m * k)
	 * - Rationale:
	 * - Iterates over `k` candidates for each of the `m` algorithms.
	 * 
	 * @param targetVector The target word vector.
	 * @param candidates   The candidate words and their vectors.
	 * @return The most similar word.
	 */
	private String findMostSimilar(double[] targetVector, Map<String, double[]> candidates) {
		String bestMatch = null;
		double bestScore = config.getSelectedAlgorithms().get(0).isHigherBetter() ? -Double.MAX_VALUE
				: Double.MAX_VALUE;

		for (AbstractSimilarityAlgorithm algorithm : config.getSelectedAlgorithms()) {

			for (Map.Entry<String, double[]> entry : candidates.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				double score = algorithm.calculate(targetVector, entry.getValue());
				if ((algorithm.isHigherBetter() && score > bestScore) ||
						(!algorithm.isHigherBetter() && score < bestScore)) {
					bestScore = score;
					bestMatch = entry.getKey();
				}
			}
		}
		if (bestMatch == null) {
			return "No Match Found"; // Fallback for no matches
		}
		return bestMatch;
	}

	/**
	 * Finds the least similar word using the selected algorithms.
	 * 
	 * Time Complexity: O(m * k)
	 * - Rationale:
	 * - Iterates over `k` candidates for each of the `m` algorithms.
	 * 
	 * @param targetVector The target word vector.
	 * @param candidates   The candidate words and their vectors.
	 * @return The least similar word.
	 */
	private String findLeastSimilar(double[] targetVector, Map<String, double[]> candidates) {
		String worstMatch = null;
		double worstScore = config.getSelectedAlgorithms().get(0).isHigherBetter() ? Double.MAX_VALUE
				: -Double.MAX_VALUE;

		for (AbstractSimilarityAlgorithm algorithm : config.getSelectedAlgorithms()) {
			for (Map.Entry<String, double[]> entry : candidates.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				double score = algorithm.calculate(targetVector, entry.getValue());
				if ((algorithm.isHigherBetter() && score < worstScore) ||
						(!algorithm.isHigherBetter() && score > worstScore)) {
					worstScore = score;
					worstMatch = entry.getKey();
				}
			}
		}
		if (worstMatch == null) {
			return "No Match Found"; // Fallback for no matches
		}
		return worstMatch;
	}

	/**
	 * Finds a random replacement from the candidates.
	 * 
	 * Time Complexity: O(1)
	 * - Rationale: Random selection from a list of candidates is a constant-time
	 * operation.
	 * 
	 * @param candidates The candidate words and their vectors.
	 * @return A randomly selected word.
	 */
	private String findRandomReplacement(Map<String, double[]> candidates) {
		List<String> keys = candidates.keySet().stream().toList();
		return keys.get(random.nextInt(keys.size()));
	}
}