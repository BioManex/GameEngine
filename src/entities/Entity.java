package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

    private TexturedModel model;
    private Vector2f position;
    private float rot;
    private Vector2f scale;

    private int textureIndex = 0;

    public Entity(TexturedModel model, Vector2f position, float rot, Vector2f scale) {
        this.model = model;
        this.position = position;
        this.rot = rot;
        this.scale = scale;
    }

    public Entity(TexturedModel model, int index, Vector2f position, float rot, Vector2f scale) {
        this.model = model;
        this.position = position;
        this.rot = rot;
        this.scale = scale;
        this.textureIndex = index;
    }

    public float getTextureXOffset(){
        int column = textureIndex % model.getTexture().getNumberOfRows();
        return (float) column / model.getTexture().getNumberOfRows();
    }

    public float getTextureYOffset(){
        int row = textureIndex / model.getTexture().getNumberOfRows();
        return (float) row / model.getTexture().getNumberOfRows();
    }

    public void increasePosition(float dx, float dy){
        this.position.x += dx;
        this.position.y += dy;
    }

    public void increaseRotation(float d){
        this.rot += d;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }
}
