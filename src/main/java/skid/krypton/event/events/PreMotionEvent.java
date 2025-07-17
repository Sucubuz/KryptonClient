package skid.krypton.event.events;

import skid.krypton.event.CancellableEvent;

public class PreMotionEvent extends CancellableEvent {
	private static final PreMotionEvent INSTANCE = new PreMotionEvent();

	public static PreMotionEvent get() {
		return INSTANCE;
	}
}
