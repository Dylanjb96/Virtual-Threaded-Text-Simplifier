package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Manhattan Distance algorithm.
 * Manhattan Distance calculates the sum of absolute differences
 * between the elements of two vectors.
 */
public class ManhattanDistance extends AbstractSimilarityAlgorithm {
    /**
     * Constructs a ManhattanDistance object with the specified name and behavior.
     */
    public ManhattanDistance() {
        super("Manhattan Distance", false); // False because lower values indicate greater similarity
    }

    /**
     * Calculates the Manhattan Distance between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates over the elements of both vectors of size `n`
     * to compute the sum of their absolute differences. Each element is processed
     * in constant time.
     * 
     * The method iterates through both input vectors (arrays) to compute the sum of
     * absolute differences between their corresponding elements.
     * 
     * Each element operation (absolute difference calculation and summation) is
     * performed in constant time (O(1))
     * 
     * For two vectors of size n, the operation takes O(n)
     * 
     * The overall time complexity of the calculate method is O(n), where n is the
     * length of the input vectors.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Manhattan distance between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.manhattanDistance(vector1, vector2);
    }
}
