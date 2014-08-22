package net.uvavru.amazon;

/**
 * Extracted Amazon assignment into an interface.
 * <p/>
 * Created by stepan on 22.8.2014.
 */
public interface AmazonAssignment {

    /**
     * Reverses the list by using an iterative algorithm.
     * <p/>
     * Complexity O(n) (this is also a naive solution, which cannot be really improved).
     */
    void reverseIteratively();

    /**
     * Reverses the list by using a recursive algorithm.
     * <p/>
     * This method (in a contrast to the iterative one) can lead to stack overflow and has
     * higher space requirements.
     * <p/>
     * Complexity O(n) (this is also a naive solution, which cannot be really improved).
     */
    void reverseRecursively();
}
