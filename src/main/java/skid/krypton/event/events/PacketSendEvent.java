package skid.krypton.event.events;

import net.minecraft.network.packet.Packet;
import skid.krypton.event.CancellableEvent;

public class PacketSendEvent extends CancellableEvent {
    public Packet<?> packet;

    public PacketSendEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    public Packet<?> getPacket() {
        return packet;
    }
    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }
}