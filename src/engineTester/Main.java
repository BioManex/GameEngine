package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.TexturedModel;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import org.lwjgl.opengl.Display;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random random = new Random();
    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        Terrain terrain = new Terrain(0,-1, loader, texturePack, blendMap, "heightmap");
        Terrain terrain2 = new Terrain(-1,-1, loader, texturePack, blendMap, "heightmap");

        RawModel model = OBJLoader.loadOBJModel("tree", loader);
        TexturedModel treeModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadOBJModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);
        TexturedModel fern = new TexturedModel(OBJLoader.loadOBJModel("fern", loader), new ModelTexture(loader.loadTexture("fern")));
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLighting(true);

        List<Entity> entities = new ArrayList<Entity>();
        for(int i = 0; i < 500; i++){
            Vector3f pos = generateRandomPosition(terrain);
            entities.add(new Entity(treeModel, pos, 0, 0, 0, 3));
            pos = generateRandomPosition(terrain);
            entities.add(new Entity(grass, pos, 0, 0, 0, 1));
            pos = generateRandomPosition(terrain);
            entities.add(new Entity(fern, pos, 0, 0, 0, 0.6f));
        }

        List<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("fern"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
        guiTextures.add(gui);

        List<Light> lights = new ArrayList<Light>();
        lights.add(new Light(new Vector3f(0, 1000, 0), new Vector3f(0.4f,0.4f,0.4f)));
        lights.add(new Light(new Vector3f(185, -1, -293), new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(370, 17, -300), new Vector3f(0, 2, 2), new Vector3f(1, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(293, 7, -305), new Vector3f(2, 2, 0), new Vector3f(1, 0.01f, 0.002f)));

        TexturedModel dragon = new TexturedModel(OBJLoader.loadOBJModel("person", loader), new ModelTexture(loader.loadTexture("playerTexture")));
        Player player = new Player(dragon, new Vector3f(0, 0, -50), 0, 0, 0, 0.3f);
        Camera camera = new Camera(player);
        MasterRenderer renderer = new MasterRenderer(loader);

        GuiRenderer guiRenderer = new GuiRenderer(loader);

        MousePicker mousePicker = new MousePicker(camera, renderer.getProjectionMatrix());

        while (!Display.isCloseRequested()){
            camera.move();
            player.move(terrain);
            renderer.processEntity(player);

            mousePicker.update();
            System.out.println(mousePicker.getCurrentRay());

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for(Entity tree : entities){
                renderer.processEntity(tree);
            }

            renderer.render(lights, camera);
            guiRenderer.render(guiTextures);
            DisplayManager.updateDisplay();
        }
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();

    }

    private static Vector3f generateRandomPosition(Terrain terrain){
        float x = random.nextFloat() * 800 - 400;
        float z = random.nextFloat() * -600;
        float y = terrain.getHeightOfTerrain(x, z);
        return new Vector3f(x, y, z);
    }
}