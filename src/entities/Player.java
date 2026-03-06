package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

public class Player extends Entity{

    private static final float GRAVITY = -0.0f;
    private static final float JUMP_POWER = 0.8f;

    private float verticalVelocity = 0;


    public Player(TexturedModel model, Vector2f position, float rot, Vector2f scale) {
        super(model, position, rot, scale);
    }

    public void move(){
        verticalVelocity += GRAVITY;
        checkInputs();
        super.increasePosition(0.1f * DisplayManager.getFrameTimeSeconds(), verticalVelocity * DisplayManager.getFrameTimeSeconds());

    }

    private void jump(){
        verticalVelocity = JUMP_POWER;
    }

    private void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            jump();
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            jump();
        }
    }
}
