package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private static Vector3f position = new Vector3f(0,50f,300f);
	private float pitch;
	private float yaw;
	private float roll;
	private float distance=100;
	public Camera()
	{
			
	}
	public static void SetCameraPosition(Vector3f vec)
	{
		position = vec;
	}
	
	public void move()
	{
		calculateZoom();
		CalculatePitch();
		
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			if(position.z >-300)
			{
				position.z -= 1;	
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			
			if(position.x <300)
			{

				position.x+=1f;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			if(position.x > -300)
			{

				position.x-=1f;	
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			if(position.z <400)
			{
				position.z += 1f;	
			}
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			if(position.y >3)
			{
				position.y -= 0.4f;	
			}
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			
			position.y += 0.4f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	private void calculateZoom()
	{
		float zoomLevel = Mouse.getDWheel()*0.1f;
		distance -= zoomLevel;
	}
	private void CalculatePitch()
	{
		 if(Mouse.isButtonDown(1))
		 {
			 float PitchChange =  Mouse.getDY()*0.1f;
			 pitch -= PitchChange;
		 }
	}
	
	

}
