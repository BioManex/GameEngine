package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import terrains.Terrain;

public class Camera {

    private final float VERTICAL_OFFSET = 3;

    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f( 0, 50f, 0);
    private float pitch = 20;
    private float yaw;
    private float roll;

    private Player player;

    public Camera(Player player){
        this.player = player;
    }

    public void move(){
        calculatePitch();
        calculateZoom();
        calculateAngleAroundPlayer();
        float horizontalDistance = getHorizontalDistance();
        float verticalDistance = getVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
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

    private void calculateCameraPosition(float horizDistance, float vertDistance){
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = player.getPosition().y + vertDistance;
        this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
    }

    private float getHorizontalDistance(){
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float getVerticalDistance(){
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)) + VERTICAL_OFFSET);
    }

    private void calculateZoom(){
        float zoomLevel = Mouse.getDWheel() * 0.1f;
        distanceFromPlayer -= zoomLevel;
    }

    private void calculatePitch(){
        if(Mouse.isButtonDown(1)){
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch -= pitchChange;
        }
    }

    private void calculateAngleAroundPlayer(){
        if(Mouse.isButtonDown(1)){
            float angleChange = Mouse.getDX() * 0.1f;
            angleAroundPlayer -= angleChange;
        }
    }
}
