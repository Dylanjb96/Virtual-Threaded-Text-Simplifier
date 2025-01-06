package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Cosine Similarity algorithm.
 * This class calculates the cosine similarity between two vectors, which
 * measures the cosine of the angle between them.
 * Higher values indicate greater similarity.
 */
public class CosineSimilarity extends AbstractSimilarityAlgorithm {
    /**
     * Constructor for CosineSimilarity.
     * Initializes the algorithm with its name and indicates that higher values
     * are better for similarity comparisons.
     */
    public CosineSimilarity() {
        super("Cosine Similarity", true); // True because higher values are better
    }

    /**
     * Calculates the cosine similarity between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: The `VectorUtils.cosineSimilarity` method computes the dot
     * product and magnitudes of the two vectors, which involve iterating through
     * the vector elements once. The complexity is proportional to the length of
     * the vectors (\(n\)).
     * 
     * The dot product of the two vectors (O(n))
     * The magnitude of each vector (O(n))
     * A division operation (O(1))
     * 
     * Therefore, the overall complexity is O(n)
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The cosine similarity value between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.cosineSimilarity(vector1, vector2);
    }
}
