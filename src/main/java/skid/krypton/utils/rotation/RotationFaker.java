package skid.krypton.utils.rotation;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import skid.krypton.Krypton;
import skid.krypton.event.EventListener;
import skid.krypton.event.Listener;
import skid.krypton.event.Priority;
import skid.krypton.event.events.PostMotionEvent;
import skid.krypton.event.events.PreMotionEvent;

import java.lang.reflect.Method;

public final class RotationFaker {
	private boolean fakeRotation;
	private float serverYaw;
	private float serverPitch;
	private float realYaw;
	private float realPitch;


	@EventListener
	public void onPreMotion(PreMotionEvent event)
	{
		if(!fakeRotation)
			return;
		
		ClientPlayerEntity player = Krypton.mc.player;
		realYaw = player.getYaw();
		realPitch = player.getPitch();
		player.setYaw(serverYaw);
		player.setPitch(serverPitch);
	}
	
	@EventListener
	public void onPostMotion(PostMotionEvent event)
	{
		if(!fakeRotation)
			return;
		
		ClientPlayerEntity player = Krypton.mc.player;
		player.setYaw(realYaw);
		player.setPitch(realPitch);
		fakeRotation = false;
	}
	
	public void faceVectorPacket(Vec3d vec)
	{
		Rotation needed = RotationUtils.getNeededRotations(vec);
		ClientPlayerEntity player = Krypton.mc.player;
		
		fakeRotation = true;
		serverYaw =
			RotationUtils.limitAngleChange(player.getYaw(), needed.yaw());
		serverPitch = needed.pitch();
	}
	
	public void faceVectorClient(Vec3d vec)
	{
		Rotation needed = RotationUtils.getNeededRotations(vec);
		
		ClientPlayerEntity player = Krypton.mc.player;
		player.setYaw(
			RotationUtils.limitAngleChange(player.getYaw(), needed.yaw()));
		player.setPitch(needed.pitch());
	}
	
	public void faceVectorClientIgnorePitch(Vec3d vec)
	{
		Rotation needed = RotationUtils.getNeededRotations(vec);
		
		ClientPlayerEntity player = Krypton.mc.player;
		player.setYaw(
			RotationUtils.limitAngleChange(player.getYaw(), needed.yaw()));
		player.setPitch(0);
	}
	
	public float getServerYaw()
	{
		return fakeRotation ? serverYaw : Krypton.mc.player.getYaw();
	}
	
	public float getServerPitch()
	{
		return fakeRotation ? serverPitch : Krypton.mc.player.getPitch();
	}
}