package ie.atu.sw.utils;

import ie.atu.sw.abstractalgorithms.AbstractSimilarityAlgorithm;

/**
 * Implementation of the Jaccard Similarity algorithm.
 * This algorithm calculates the similarity between two sets
 * or vectors based on their intersection and union.
 */
public class JaccardSimilarity extends AbstractSimilarityAlgorithm {
    /**
     * Constructs a JaccardSimilarity object with the specified name and behavior.
     */
    public JaccardSimilarity() {
        super("Jaccard Similarity", true); // True because higher values indicate greater similarity
    }

    /**
     * Calculates the Jaccard Similarity between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates over both vectors of size `n` to calculate the
     * intersection and union. Each element is processed in constant time.
     * 
     * The method iterates over the elements of both input vectors (arrays) to
     * compute the intersection (minimum values) and the union (maximum values).
     * 
     * Each iteration processes an element in constant time (O(1))
     * 
     * For two vectors of size n, this operation takes O(n).
     * 
     * Overall time complexity of the calculate method is O(n) where n
     * is the length of the input vectors.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Jaccard similarity value between the two vectors.
     */
    @Override
    public double calculate(double[] vector1, double[] vector2) {
        return VectorUtils.jaccardSimilarity(vector1, vector2);
    }
}
