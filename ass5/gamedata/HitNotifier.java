//208005587 Itay Sharfer
package gamedata;
/**
 * The HitNotifier interface is used for objects that notifies if they have been hit.
 */
public interface HitNotifier {
    /**
     * The addHitListener method adds a given HitListener object to a HitListeners list of a HitNotifier object.
     * @param hl - the HitListener object that will be added.
     */
    void addHitListener(HitListener hl);
    /**
     * The removeHitListener method removes a given HitListener object from a HitListeners list of a HitNotifier object.
     * @param hl - the HitListener object that will be removed.
     */
    void removeHitListener(HitListener hl);
}
