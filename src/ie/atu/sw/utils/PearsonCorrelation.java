package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Pearson Correlation algorithm.
 * Pearson Correlation measures the linear correlation between two vectors,
 * producing a value between -1 and 1, where 1 indicates a perfect positive
 * correlation,
 * -1 indicates a perfect negative correlation, and 0 indicates no correlation.
 */
public class PearsonCorrelation extends AbstractSimilarityAlgorithm {
    /**
     * Constructs a PearsonCorrelation object with the specified name and behavior.
     */
    public PearsonCorrelation() {
        super("Pearson Correlation", true); // True because higher values indicate stronger correlation
    }

    /**
     * Calculates the Pearson Correlation between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through the elements of both vectors of size `n`
     * to compute their means, numerator, and denominator required for the formula.
     * Each step involves constant-time operations for each element.
     * 
     * The method requires iterating over the elements of both input vectors
     * (arrays) to:
     * 1. Compute the means of the vectors.
     * 2. Calculate the numerator (sum of products of deviations from means).
     * 3. Calculate the denominator (square root of sums of squared deviations).
     * 
     * Each of these operations involves processing each vector element once
     * 
     * The overall time complexity of the calculate method is O(n), where n
     * is the length of the input vectors.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Pearson correlation coefficient between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.pearsonCorrelation(vector1, vector2);
    }
}
