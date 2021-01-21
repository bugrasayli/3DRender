package engineTester;

import models.RawModel;
import models.TexturedModel;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;


import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.ObjLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

public class MainGameLoop {
	static float[][] ways;
	static int[] Positions;
	static RawModel[] RawModels; 
	static long lastframe;
	static long getTime()
	{
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}
	static int getDelta()
	{
		long currentTime = getTime();
		int delta = (int)(currentTime-lastframe);
		lastframe = getTime();
		return delta;
	}
	
	public static void main(String[] args)
	{
		int[] Positions = new int[3]; Positions[0] = 45; Positions[1]= 80; Positions[2]=125;  // radiuses
		int T1=100,T2=90,T3=110;  // Time
		float[][] ways = getWays(Positions, T1, T2, T3); // calculation of ways, velocities, and angular velocity
		int[] PositionOfTrack = new int[2];	PositionOfTrack[0] = 0; PositionOfTrack[1] = 0; 
		ArrayList<Entity> entities = new ArrayList<Entity>();
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		RawModel CarModel = ObjLoader.loadObjModel("lancia",loader);
		RawModel FloorModel = ObjLoader.loadObjModel("floor3", loader);
		RawModel SkyModel = ObjLoader.loadObjModel("sky5", loader);
		RawModel WheelModel = ObjLoader.loadObjModel("wheel", loader);
		RawModel TrackModel1 = ObjLoader.loadObjModel("track1", loader);
		RawModel TrackModel2 = ObjLoader.loadObjModel("track2", loader);
		RawModel TrackModel3 = ObjLoader.loadObjModel("track3", loader);
		RawModel SeritModel1 = ObjLoader.loadObjModel("serit1", loader);
		RawModel SeritModel2 = ObjLoader.loadObjModel("serit2", loader);
		RawModel TreeModel = ObjLoader.loadObjModel("tree", loader);
		
		TexturedModel Car1 = new TexturedModel(CarModel,new ModelTexture(loader.loadTexture("carTxt2")));
		TexturedModel Car2 = new TexturedModel(CarModel,new ModelTexture(loader.loadTexture("carTxt")));
		TexturedModel Car3 = new TexturedModel(CarModel,new ModelTexture(loader.loadTexture("carTxt3")));
		
		TexturedModel TextureWheel1=new TexturedModel(WheelModel,new ModelTexture(loader.loadTexture("carTxt2")));
		TexturedModel TextureWheel2=new TexturedModel(WheelModel,new ModelTexture(loader.loadTexture("carTxt")));
		TexturedModel TextureWheel3=new TexturedModel(WheelModel,new ModelTexture(loader.loadTexture("carTxt3")));
		
		
		TexturedModel Sky = new TexturedModel(SkyModel,new ModelTexture(loader.loadTexture("back")));
		TexturedModel staticModel3 = new TexturedModel(FloorModel,new ModelTexture(loader.loadTexture("grass")));
		
		
		TexturedModel track1 = new TexturedModel(TrackModel1,new ModelTexture(loader.loadTexture("gray")));
		TexturedModel track2 = new TexturedModel(TrackModel2,new ModelTexture(loader.loadTexture("gray")));
		TexturedModel track3 = new TexturedModel(TrackModel3,new ModelTexture(loader.loadTexture("gray")));
		
		TexturedModel serit1 = new TexturedModel(SeritModel1,new ModelTexture(loader.loadTexture("white2")));
		TexturedModel serit2 = new TexturedModel(SeritModel2,new ModelTexture(loader.loadTexture("white2")));
		TexturedModel Tree = new TexturedModel(TreeModel,new ModelTexture(loader.loadTexture("tree")));
		
		Entity entityCar1 = new Entity(Car1, new Vector3f(PositionOfTrack[0],-9,Positions[0] + PositionOfTrack[1]),0,90,0,1);	entities.add(entityCar1);
		Entity entityCar2 = new Entity(Car2, new Vector3f(PositionOfTrack[0],-9,Positions[1] + PositionOfTrack[1]),0,90,0,1);	entities.add(entityCar2);
		Entity entityCar3 = new Entity(Car3, new Vector3f(PositionOfTrack[0],-9,Positions[2] + PositionOfTrack[1]),0,90,0,1);	entities.add(entityCar3);

		Entity EntityWheel1 = new Entity(TextureWheel1, new Vector3f(PositionOfTrack[0],-9,Positions[0] + PositionOfTrack[1]),0,90,0,1);	//entities.add(EntityWheel1);
		Entity EntityWheel2 = new Entity(TextureWheel2, new Vector3f(PositionOfTrack[0],-9,Positions[1] + PositionOfTrack[1]),0,90,0,1);	//entities.add(EntityWheel2);
		Entity EntityWheel3 = new Entity(TextureWheel3, new Vector3f(PositionOfTrack[0],-9,Positions[2] + PositionOfTrack[1]),0,90,0,1);	//entities.add(EntityWheel3);
		
		
		Entity entitySky  = new Entity(Sky,  new Vector3f(-200,0,-600),90,0,0,0.1f);	entities.add(entitySky);
		ArrayList<Entity> floors = new ArrayList<Entity>();
		
		for (int i=-1000; i<1000;i+=210) 
		{
			for(int j = -500; j< 500; j+=210)
			{
				Entity entity3 = new Entity(staticModel3, new Vector3f(i,1,j),0,0,0,1);
				floors.add(entity3);
			}
		}
		System.out.println(ways[0][0] + " "+ways[0][1]+" "+ ways[0][2]);
		Entity entityTrack  = new Entity(track1, new Vector3f(PositionOfTrack[0],-10,PositionOfTrack[1]),0,0,0,1);	entities.add(entityTrack);
		Entity entityTrack2 = new Entity(track2, new Vector3f(PositionOfTrack[0],-10,PositionOfTrack[1]),0,0,0,1);	entities.add(entityTrack2);
		Entity entityTrack3 = new Entity(track3, new Vector3f(PositionOfTrack[0],-10,PositionOfTrack[1]),0,0,0,1); 	entities.add(entityTrack3);
		Entity entitySerit = new Entity(serit1, new Vector3f(PositionOfTrack[0],-11,PositionOfTrack[1]),0,0,0,1); 	entities.add(entitySerit);
		Entity entitySerit2 = new Entity(serit2, new Vector3f(PositionOfTrack[0],-10,PositionOfTrack[1]),0,0,0,1); 	entities.add(entitySerit2);
		Entity entityTree = new Entity(Tree, new Vector3f(PositionOfTrack[0],-10,PositionOfTrack[1]),0,0,0,15); 	entities.add(entityTree);
		ArrayList<Entity> trees = new ArrayList<Entity>(); 
		Random rnd = new Random();
		for (int i = 0; i< 200	; i++) 
		{
			int x = rnd.nextInt(600)+PositionOfTrack[0];
			int z = rnd.nextInt(600)+PositionOfTrack[0];
			
			boolean a =check(PositionOfTrack[0]-200, PositionOfTrack[0]-200, PositionOfTrack[0]-200, PositionOfTrack[0]+200, PositionOfTrack[0]+200,
			PositionOfTrack[0]+200,PositionOfTrack[0]+200, PositionOfTrack[0]-200, x, z);
			if(!a)
				if(i%2==0)
				{
					trees.add(new Entity(Tree, new Vector3f(x,-10,-z), 0, 0, 0, 15));
				}
				else
					trees.add(new Entity(Tree, new Vector3f(-x,-10,-z), 0, 0, 0, 15));	
		}
		Camera camera = new Camera();
		float angle1=0,angle2=0,angle3=0;
		float a = 0;
		int x = 0;
		boolean begin=false,stop=false;
		lastframe =getTime();
		while(!Display.isCloseRequested())
		{	
			camera.move();
			renderer.prepare();
			shader.start();
			for (Entity entity : trees) {
				renderer.render(entity, shader);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				begin = true;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				stop = true; begin = false;
				Vector3f car1 = new Vector3f(PositionOfTrack[0],-9,Positions[0] + PositionOfTrack[1]);
				Vector3f car2 = new Vector3f(PositionOfTrack[0],-9,Positions[1] + PositionOfTrack[1]);
				Vector3f car3 = new Vector3f(PositionOfTrack[0],-9,Positions[2] + PositionOfTrack[1]);
				
				entityCar1.setPosition(car1); entityCar1.setRotY(90);
				entityCar2.setPosition(car2); entityCar2.setRotY(90);
				entityCar3.setPosition(car3); entityCar3.setRotY(90);
				
				//EntityWheel1.setPosition(car1); EntityWheel1.setRotY(90);
				//EntityWheel2.setPosition(car2); EntityWheel2.setRotY(90);
				//EntityWheel3.setPosition(car3); EntityWheel3.setRotY(90);
				
				if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					entitySky.increaseRotation(10, 10, 10);
					for (Entity entity : trees) 
					{
						entity.increaseRotation(0, 10, 0);
					}
				}
				angle1 = 0;	angle3 = 0; angle2 = 0;
			}
			if(begin)
			{
			angle1 += ways[0][2];angle2 += ways[1][2];angle3 += ways[2][2];
			entityCar1.increasePosition(ways[0][1] * (float)Math.cos(angle1),0,ways[0][1] * -(float)Math.sin(angle1));
			entityCar2.increasePosition(ways[1][1] * (float)Math.cos(angle2),0,ways[1][1] * -(float)Math.sin(angle2));
			entityCar3.increasePosition(ways[2][1] * (float)Math.cos(angle3),0,ways[2][1] * -(float)Math.sin(angle3));
			
			EntityWheel1.increasePosition(ways[0][1] * (float)Math.cos(angle1),0,ways[0][1] * -(float)Math.sin(angle1));
			EntityWheel2.increasePosition(ways[1][1] * (float)Math.cos(angle2),0,ways[1][1] * -(float)Math.sin(angle2));
			EntityWheel3.increasePosition(ways[2][1] * (float)Math.cos(angle3),0,ways[2][1] * -(float)Math.sin(angle3));
			
			entityCar1.increaseRotation(0, (float)360/(float)T1, 0); //863f
			entityCar2.increaseRotation(0, (float)360/(float)T2, 0); //863f
			entityCar3.increaseRotation(0, (float)360/(float)T3, 0);
			
			EntityWheel1.increaseRotation(0, (float)360/(float)T1, 0); //863f
			EntityWheel2.increaseRotation(0, (float)360/(float)T2, 0); //863f
			EntityWheel3.increaseRotation(0, (float)360/(float)T3, 0);
			}
			shader.loadViewMatrix(camera);
			
			for (Entity entity : entities) {
				renderer.render(entity, shader);
			}
			for (Entity entity : floors) {
				renderer.render(entity,shader);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_1))
			{
				Vector3f vec = new Vector3f(entityCar1.getPosition().x-20,entityCar1.getPosition().y+20,entityCar1.getPosition().z-20);
				Camera.SetCameraPosition(vec);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_2))
			{
				Vector3f vec = new Vector3f(entityCar2.getPosition().x-20,entityCar1.getPosition().y+20,entityCar1.getPosition().z-20);
				Camera.SetCameraPosition(vec);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_3))
			{
				Vector3f vec = new Vector3f(entityCar3.getPosition().x-20,entityCar1.getPosition().y+20,entityCar1.getPosition().z-20);
				Camera.SetCameraPosition(vec);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_0))
			{
				Vector3f vec = new Vector3f(0,50f,300f);
				Camera.SetCameraPosition(vec);
			}
			shader.stop();

			//System.out.println(getDelta());
			DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}
	public static float[][] getWays(int positions[],int T1,int T2,int T3)
	{
		ways = new float[3][3];
		ways[0][0] = 2 * (float)Math.PI * 45; 	// 282,84 -- pixel number of track
		ways[0][1] = ways[0][0] / T1;	// 282,84 Velocity
		ways[0][2] = (float)(2 * Math.PI) / T1; // angler Velocity
		
		ways[1][0] = 2 * (float)Math.PI * 80; 	// 502,65
		ways[1][1] = ways[1][0] / T2;
		ways[1][2] = (float)(2 * Math.PI) / T2;
		
		ways[2][0] = 2 * (float)Math.PI * 125;	// 785,39
		ways[2][1] = ways[2][0] / T3;
		ways[2][2] = (float)(2 * Math.PI) / T3;
		
		return ways;
	}
	public static RawModel[] getRawModels(String Text[],Loader loader)
	{
		int i = 0;
		for (String string : Text) {
			RawModels[i] = ObjLoader.loadObjModel(Text[i], loader);
		}
		return RawModels;
	}
	public static float area(int x1, int y1, int x2,  int y2, int x3, int y3) { 
		return (float)Math.abs((x1 * (y2 - y3) +  x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0); 
	} 
	public static boolean check(int x1, int y1, int x2, int y2, 
int x3, int y3, int x4, int y4, int x, int y) 
{ 
float A = area(x1, y1, x2, y2, x3, y3)+  
    area(x1, y1, x4, y4, x3, y3); 
float A1 = area(x, y, x1, y1, x2, y2); 
float A2 = area(x, y, x2, y2, x3, y3); 
float A3 = area(x, y, x3, y3, x4, y4); 
float A4 = area(x, y, x1, y1, x4, y4); 
return (A == A1 + A2 + A3 + A4); 
} 

}
