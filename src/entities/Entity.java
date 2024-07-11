package entities;

import models.TexturedModel;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

import java.util.Random;

public class Entity {

    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;

    private static final float RUN_SPEED = 0.02f;
    private static final float TURN_SPEED = 100;
    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;
    private static final float GRAVITY = -100;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x+=dx;
        this.position.y+=dy;
        this.position.z+=dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX+=dx;
        this.rotY+=dy;
        this.rotZ+=dz;
    }

    public  int generateRandomNumber() {
        Random random = new Random();
        float probability = random.nextFloat(); // Generate a random float between 0.0 and 1.0

        if (probability < 0.98) {
            return 0; // 98% chance
        } else {
            return random.nextInt(10001) - 5000; // 20% chance, generates a number between -50 and 50
        }
    }

    public void move2() {

        if (Mouse.isButtonDown(2)) {

            Random random = new Random();
            float min = 0.0f;
            float max = 50.0f;
            float randomFloat = min + random.nextFloat() * (max - min);
            currentSpeed = randomFloat;

            int randomInt = generateRandomNumber();
            currentTurnSpeed = randomInt;

            increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
            float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
            float dx = (float) (distance * Math.sin(Math.toRadians(getRotY())));
            float dz = (float) (distance * Math.cos(Math.toRadians(getRotY())));
            increasePosition(dz, 0, dx);

            //upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
            //increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        }
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
