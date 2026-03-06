package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;


public class Camera {

    private final float VERTICAL_OFFSET = 3;

    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f( 0, 0, 1);
    private float pitch = 0;

    private final Player player;

    public Camera(Player player){
        this.player = player;
    }

    public void move(){
        moveCamera();
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    private void moveCamera(){
        System.out.println(player.getPosition());
        this.position = new Vector3f(5 * player.getPosition().getX(), 0, 1);
        System.out.println(position);
    }
}
