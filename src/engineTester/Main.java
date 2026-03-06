package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Player;
import guis.GuiRenderer;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.*;
import org.lwjgl.opengl.Display;
import textures.ModelTexture;

import java.util.Random;

public class Main {
    private static Random random = new Random();
    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        TexturedModel playerModel = new TexturedModel(new Vector2f(2f, 2f), new ModelTexture(loader.loadTexture("Bird")), loader);
        playerModel.getTexture().setHasTransparency(true);
        Player player = new Player(playerModel, new Vector2f(0, 0), 0,new Vector2f(0.2f,0.35f));
        Camera camera = new Camera(player);
        TexturedModel squareTex = new TexturedModel(new Vector2f(0.5f, 0.5f), new ModelTexture(loader.loadTexture("Grass")), loader);
        squareTex.getTexture().setHasTransparency(true);
        Entity square = new Entity(squareTex, new Vector2f(0, -0.8f) ,0, new Vector2f(5, 1));
        MasterRenderer renderer = new MasterRenderer();

        GuiRenderer guiRenderer = new GuiRenderer(loader);


        while (!Display.isCloseRequested()){
            player.move();
            camera.move();
            renderer.processEntity(player);
            renderer.processEntity(square);
            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
//        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();

    }

}