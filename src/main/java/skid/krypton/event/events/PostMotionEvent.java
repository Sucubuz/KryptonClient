package skid.krypton.event.events;

import skid.krypton.event.CancellableEvent;

public class PostMotionEvent extends CancellableEvent {
	private static final PostMotionEvent INSTANCE = new PostMotionEvent();

	public static PostMotionEvent get() {
		return INSTANCE;
	}
}
