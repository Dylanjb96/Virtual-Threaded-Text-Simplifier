package ie.atu.sw.utils;

/**
 * Utility class for performing vector-based operations such as calculating
 * distances and similarities between vectors.
 */
public class VectorUtils {

    /**
     * Calculates the Euclidean distance between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` once to calculate the
     * squared differences.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Euclidean distance between the two vectors.
     */
    public static double euclideanDistance(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            double diff = vector1[i] - vector2[i];
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }

    /**
     * Calculates the cosine similarity between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` once to compute the
     * dot product and vector norms.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The cosine similarity between the two vectors.
     */
    public static double cosineSimilarity(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            dot += vector1[i] * vector2[i];
            normA += vector1[i] * vector1[i];
            normB += vector2[i] * vector2[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /**
     * Calculates the Jaccard similarity between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` once to compute the
     * intersection and union.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Jaccard similarity between the two vectors.
     */
    public static double jaccardSimilarity(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double intersection = 0.0;
        double union = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            intersection += Math.min(vector1[i], vector2[i]);
            union += Math.max(vector1[i], vector2[i]);
        }
        return intersection / union;
    }

    /**
     * Calculates the Manhattan distance between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` once to compute the
     * absolute differences.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Manhattan distance between the two vectors.
     */
    public static double manhattanDistance(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            sum += Math.abs(vector1[i] - vector2[i]);
        }
        return sum;
    }

    /**
     * Calculates the Pearson correlation coefficient between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` to compute the mean,
     * numerator, and denominators.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Pearson correlation coefficient between the two vectors.
     */
    public static double pearsonCorrelation(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double mean1 = mean(vector1);
        double mean2 = mean(vector2);
        double numerator = 0.0, denominator1 = 0.0, denominator2 = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            double diff1 = vector1[i] - mean1;
            double diff2 = vector2[i] - mean2;
            numerator += diff1 * diff2;
            denominator1 += diff1 * diff1;
            denominator2 += diff2 * diff2;
        }
        return numerator / Math.sqrt(denominator1 * denominator2);
    }

    /**
     * Calculates the Chebyshev distance between two vectors.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through both vectors of size `n` to compute the maximum
     * absolute difference.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The Chebyshev distance between the two vectors.
     */
    public static double chebyshevDistance(double[] vector1, double[] vector2) {
        validateEqualLength(vector1, vector2);
        double max = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            max = Math.max(max, Math.abs(vector1[i] - vector2[i]));
        }
        return max;
    }

    /**
     * Calculates the mean of a vector.
     *
     * Time Complexity: O(n)
     * - Rationale: Iterates through the vector of size `n` to compute the sum and
     * divide by its length.
     *
     * @param vector The input vector.
     * @return The mean of the vector.
     */
    private static double mean(double[] vector) {
        double sum = 0.0;
        for (double val : vector) {
            sum += val;
        }
        return sum / vector.length;
    }

    /**
     * Validates that two vectors are of equal length.
     *
     * Time Complexity: O(1)
     * - Rationale: Simply compares the lengths of the two vectors, which is a
     * constant-time operation.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @throws IllegalArgumentException if the lengths of the vectors are not equal.
     */
    private static void validateEqualLength(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same length");
        }
    }
}
