package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Chebyshev Distance similarity algorithm.
 * Chebyshev Distance measures the greatest difference between corresponding
 * elements of two vectors.
 */
public class ChebyshevDistance extends AbstractSimilarityAlgorithm {
    /**
     * Constructor for ChebyshevDistance.
     * 
     * Time Complexity: O(1)
     * - Rationale: The constructor calls the parent class constructor with the
     * name of the algorithm and a flag indicating whether a lower score is better.
     * Both are constant-time operations.
     */
    public ChebyshevDistance() {
        super("Chebyshev Distance", false);
    }

    /**
     * Calculates the Chebyshev Distance between two vectors.
     * 
     * Time Complexity: O(n)
     * - Rationale: The method delegates the calculation to
     * `VectorUtils.chebyshevDistance`, which iterates through the vectors
     * element by element to find the maximum absolute difference. This requires
     * \(O(n)\) operations, where \(n\) is the size of the vectors.
     * 
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Chebyshev Distance between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.chebyshevDistance(vector1, vector2);
    }
}
