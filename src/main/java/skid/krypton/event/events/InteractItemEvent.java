package skid.krypton.event.events;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import skid.krypton.event.CancellableEvent;

public class InteractItemEvent extends CancellableEvent {
    private static final InteractItemEvent INSTANCE = new InteractItemEvent();

    public Hand hand;
    public ActionResult toReturn;

    public static InteractItemEvent get(Hand hand) {
        INSTANCE.hand = hand;
        INSTANCE.toReturn = null;

        return INSTANCE;
    }
}