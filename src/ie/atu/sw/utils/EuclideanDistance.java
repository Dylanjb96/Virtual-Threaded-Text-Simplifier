package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Euclidean Distance algorithm.
 * This class calculates the Euclidean distance between two vectors, which
 * is a measure of the straight-line distance in a multidimensional space.
 * Lower values indicate greater similarity.
 */
public class EuclideanDistance extends AbstractSimilarityAlgorithm {
    /**
     * Constructor for EuclideanDistance.
     * Initializes the algorithm with its name and indicates that lower values
     * are better for similarity comparisons.
     */
    public EuclideanDistance() {
        super("Euclidean Distance", false); // False because lower values are better
    }

    /**
     * Calculates the Euclidean distance between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: The `VectorUtils.euclideanDistance` method computes the squared
     * differences of corresponding elements in the two vectors, sums them up,
     * and then computes the square root of the sum. Each of these operations
     * involves a single iteration through the vector elements, making the overall
     * complexity proportional to the length of the vectors (\(n\)).
     * 
     * Computing the quared differences of corresponding elements (O(n))
     * Summing up these squared differences (O(n))
     * Taking the square root of the sum (O(1))
     * 
     * Therefore, the overall complexity is O(n), where n is the length of the
     * vectors
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Euclidean distance between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.euclideanDistance(vector1, vector2);
    }
}
