package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		//StaticShader shader = new StaticShader();
		//EntityRenderer renderer = new EntityRenderer(shader);


		TexturedModel person = new TexturedModel(OBJLoader.loadObjModel("person", loader),
				new ModelTexture(loader.loadTexture("playerTexture")));

		TexturedModel dragon = new TexturedModel(OBJLoader.loadObjModel("dragon", loader),
				new ModelTexture(loader.loadTexture("texture")));

		TexturedModel castle = new TexturedModel(OBJLoader.loadObjModel("castle", loader),
				new ModelTexture(loader.loadTexture("image2")));

		TexturedModel tree = new TexturedModel(OBJLoader.loadObjModel("tree", loader),
				new ModelTexture(loader.loadTexture("treeTexture")));

		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
				new ModelTexture(loader.loadTexture("grassTexture")));

		//ModelTexture texture = staticModel.getTexture();


		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		float min = 10.0f;
		float max = 30.0f;
		for (int i = 0; i < 500; i++) {
			float randomFloat = min + random.nextFloat() * (max - min);
			entities.add(new Entity(tree, new Vector3f(random.nextFloat()*800-400, 0,
					random.nextFloat()*-600), 0, 0, 0, randomFloat));
			entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800-400, 0,
					random.nextFloat()*-600), 0, 0, 0, 1));
		}



		Entity entity = new Entity(person, new Vector3f(0,0,10),0,0,0,1);
		Entity entity2 = new Entity(dragon, new Vector3f(30,0,10),0,0,0,2);
		Entity entity3 = new Entity(castle, new Vector3f(-50,0,10),0,0,0,30);
		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));

		Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain1 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(-1,1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain3 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain4 = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain5 = new Terrain(0,1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain6 = new Terrain(1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain7 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain8 = new Terrain(1,1,loader,new ModelTexture(loader.loadTexture("grass")));


		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();

		while(!Display.isCloseRequested()){

			// ==================== game logic ====================
			entity.increaseRotation(0, -1, 0);
			camera.move();

			// person
			renderer.processEntity(entity);

			// dragon
			renderer.processEntity(entity2);

			// castle
			renderer.processEntity(entity3);

			// tree & grass
			for (Entity ety : entities) {
				renderer.processEntity(ety);
			}

			// terrain
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain1);
			renderer.processTerrain(terrain2);
			renderer.processTerrain(terrain3);
			renderer.processTerrain(terrain4);
			renderer.processTerrain(terrain5);
			renderer.processTerrain(terrain6);
			renderer.processTerrain(terrain7);
			renderer.processTerrain(terrain8);


			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
