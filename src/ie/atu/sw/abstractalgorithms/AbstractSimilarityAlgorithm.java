package ie.atu.sw.abstractalgorithms;

/**
 * Abstract class for similarity algorithms, providing a framework for
 * implementing specific similarity computations.
 */
public abstract class AbstractSimilarityAlgorithm {
    private final String name;
    private final boolean higherIsBetter;

    /**
     * Constructor for AbstractSimilarityAlgorithm.
     * 
     * Time Complexity: O(1)
     * - Rationale: Assigning values to instance variables is a constant-time
     * operation.
     * 
     * @param name           The name of the algorithm.
     * @param higherIsBetter Flag indicating whether higher scores indicate greater
     *                       similarity.
     */
    protected AbstractSimilarityAlgorithm(String name, boolean higherIsBetter) {
        this.name = name;
        this.higherIsBetter = higherIsBetter;
    }

    /**
     * Retrieves the name of the algorithm.
     * 
     * Time Complexity: O(1)
     * - Rationale: Returning a reference to a String is a constant-time operation.
     * 
     * @return The name of the algorithm.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if higher scores indicate greater similarity.
     * 
     * Time Complexity: O(1)
     * - Rationale: Returning a boolean value is a constant-time operation.
     * 
     * @return True if higher scores are better, false otherwise.
     */
    public boolean isHigherBetter() {
        return higherIsBetter;
    }

    /**
     * Abstract method to calculate the similarity between two vectors.
     * 
     * Time Complexity: Depends on the implementation in subclasses.
     * - Rationale: Each subclass will define its own complexity based on the
     * specific algorithm it implements. For example:
     * - Euclidean distance: O(n), where `n` is the size of the vectors.
     * - Cosine similarity: O(n), as it involves calculating dot products and
     * magnitudes.
     * - Jaccard similarity: O(n), based on intersection and union computations.
     * 
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The similarity score between the two vectors.
     */
    public abstract double calculate(double[] vector1, double[] vector2);
}