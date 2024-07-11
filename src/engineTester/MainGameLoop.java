package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.TexturedModel;
import org.lwjgl.opengl.Display;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		//StaticShader shader = new StaticShader();
		//EntityRenderer renderer = new EntityRenderer(shader);


		TexturedModel person = new TexturedModel(OBJLoader.loadObjModel("man", loader),
				new ModelTexture(loader.loadTexture("manTexture")));

		TexturedModel dragon = new TexturedModel(OBJLoader.loadObjModel("dragon", loader),
				new ModelTexture(loader.loadTexture("texture3")));

		TexturedModel castle = new TexturedModel(OBJLoader.loadObjModel("castle", loader),
				new ModelTexture(loader.loadTexture("castleTexture")));

		TexturedModel tree = new TexturedModel(OBJLoader.loadObjModel("tree2", loader),
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



		//Entity entity = new Entity(person, new Vector3f(0,0,10),0,0,0,1);
		Entity entity2 = new Entity(dragon, new Vector3f(30,0,10),0,90,0,4);
		Entity entity3 = new Entity(castle, new Vector3f(-50,0,10),0,0,0,20);
		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));

		Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain1 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain2 = new Terrain(-1,1,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain3 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain4 = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain5 = new Terrain(0,1,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain6 = new Terrain(1,-1,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain7 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass2")));
		Terrain terrain8 = new Terrain(1,1,loader,new ModelTexture(loader.loadTexture("grass2")));


		MasterRenderer renderer = new MasterRenderer();

		Player player = new Player(person, new Vector3f(0, 0, 100), 0, 180, 0, 1);
		Camera camera = new Camera(player);

		while(!Display.isCloseRequested()){

			// ==================== game logic ====================
			//entity.increaseRotation(0, -1, 0);
			if (Math.abs(player.getPosition().getX() - entity2.getPosition().getX()) < 10
			&& Math.abs(player.getPosition().getZ() - entity2.getPosition().getZ()) < 25) {
				JOptionPane.showMessageDialog(null, "Game Over!");
				break;
			}
			camera.move();
			player.move();
			entity2.move2();
			renderer.processEntity(player);

			// person
			//renderer.processEntity(entity);

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
