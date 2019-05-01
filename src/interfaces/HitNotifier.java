package interfaces;

/**
 * .
 * author: Eyal Styskin
 * Interface name:HitNotifier
 * Interface operation: indicate that objects that implement
 * it send notifications when they are being hit:
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * Function Name: addHitListener
     * Function Operation: Add hl as a listener to hit events.
     *
     * @param hl - HitListener type (listener pattern)
     */
    void addHitListener(HitListener hl);

    /**
     * Function Name: removeHitListener
     * Function Operation: Remove hl from the list of listeners to hit events.
     *
     * @param hl - HitListener type (listener pattern)
     */
    void removeHitListener(HitListener hl);
}
