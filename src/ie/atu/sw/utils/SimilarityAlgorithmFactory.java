package ie.atu.sw.utils;

import java.util.HashMap;
import java.util.Map;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * A factory class for creating and retrieving instances of similarity
 * algorithms. This class uses a static map to store and manage pre-initialized
 * algorithm objects.
 */
public class SimilarityAlgorithmFactory {
    // Map of algorithm names to their respective instances
    private static final Map<String, AbstractSimilarityAlgorithm> algorithms = new HashMap<>();

    /**
     * Static initializer to populate the algorithm map
     * 
     * Initializes a HashMap with a fixed number of key-value pairs (6 algorithms).
     * Each put operation has a time complexity of O(1), resulting in O(1) for the
     * blocks since the number of entries is constant.
     */
    static {
        algorithms.put("euclidean", new EuclideanDistance());
        algorithms.put("cosine", new CosineSimilarity());
        algorithms.put("jaccard", new JaccardSimilarity());
        algorithms.put("manhattan", new ManhattanDistance());
        algorithms.put("pearson", new PearsonCorrelation());
        algorithms.put("chebyshev", new ChebyshevDistance());
    }

    /**
     * Retrieves the names of all available similarity algorithms.
     *
     * Time Complexity: O(1)
     * - Rationale: The array is statically defined and does not involve any dynamic
     * computation.
     * 
     * Returns a statically defined array, which is an O(1) operations as there is
     * no computation or iteration.
     *
     * @return An array of algorithm names.
     */
    public static String[] getAvailableAlgorithms() {
        return new String[] { "Euclidean", "Cosine", "Jaccard", "Manhattan", "Pearson", "Chebyshev" };
    }

    /**
     * Retrieves an instance of the specified similarity algorithm.
     *
     * Time Complexity: O(1)
     * - Rationale: Retrieving a value from a `HashMap` by its key is a
     * constant-time operation.
     * 
     * Retrieves a value from the HashMap using its key.
     * Hash-based lookup in the map is a constant-time operation, O(1),
     * under typical conditions
     *
     * @param name The name of the desired algorithm (case-insensitive).
     * @return The corresponding `AbstractSimilarityAlgorithm` instance, or `null`
     *         if not found.
     */
    public static AbstractSimilarityAlgorithm getAlgorithm(String name) {
        return algorithms.getOrDefault(name.toLowerCase(), null);
    }

    /*
     * Overall, all methods in this class execute in O(1) time complexity, ensuring
     * efficient and consistent performance regardless of the number of algorithms
     * or queries.
     */
}
