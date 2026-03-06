package models;

import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;
import textures.ModelTexture;

public class TexturedModel {

    private float height;
    private float width;
    private RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(Vector2f dimentions, ModelTexture texture, Loader loader){
        this.rawModel = generateModel(dimentions, loader);
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    private RawModel generateModel(Vector2f dimentions, Loader loader){
        float[] vertices = new float[]{
                dimentions.x / 2,  dimentions.y / 2,
                -dimentions.x / 2, dimentions.y / 2,
                dimentions.x / 2, -dimentions.y / 2,
                -dimentions.x / 2, -dimentions.y / 2,
        };
        return loader.loadToVAO(vertices);
    }
}
