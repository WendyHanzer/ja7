package unr.edu;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by cam on 10/8/14.
 */
public class Camera {
    private Engine engine;
    private Vector3f pos, orientation, up;
    private float rotateX, rotateY, speed, sensitivity, boost;

    public Camera(Engine e)
    {
        engine = e;

        pos = new Vector3f(0.0f,50.0f, 200.0f);
        orientation = new Vector3f(0.0f, -50.0f, -200.0f);
        up = new Vector3f(0.0f, 1.0f, 0.0f);

        rotateX = rotateY = 0.0f;
        boost = 1.0f;
        speed = engine.options.camera_speed;
        sensitivity = engine.options.camera_sensitivity;
    }

    public void moveForward()
    {
        Vector3f newVec = orientation.normalise(null);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f.add(pos, newVec, pos);
    }

    public void moveBackward()
    {
        Vector3f newVec = orientation.normalise(null);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f.sub(pos, newVec, pos);
    }

    public void moveLeft()
    {
        Vector3f newVec = orientation.normalise(null);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f result = Vector3f.cross(newVec, up, null);

        Vector3f.sub(pos, result, pos);
    }

    public void moveRight()
    {
        Vector3f newVec = orientation.normalise(null);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f result = Vector3f.cross(newVec, up, null);

        Vector3f.add(pos, result, pos);
    }

    public void moveUp()
    {
        Vector3f newVec = new Vector3f(up);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f.add(pos, newVec, pos);
    }

    public void moveDown()
    {
        Vector3f newVec = new Vector3f(up);
        newVec.scale(speed);
        newVec.scale(boost);

        Vector3f.sub(pos, newVec, pos);
    }

    public void rotate(float x, float y)
    {
        rotateX -= x * sensitivity;
        rotateY += y * sensitivity;
    }

    public void update()
    {
        Matrix4f rotationMatrix = new Matrix4f();

        if(rotateX != 0.0f) {
            Matrix4f.rotate(rotateX, orientation, rotationMatrix, rotationMatrix);
            Vector4f vec4 = new Vector4f(orientation.x, orientation.y, orientation.z, 0.0f);
            //Vector4f.m
        }

    }

    public void applyBoost(float newBoost)
    {

    }

    public Matrix4f getView()
    {
        return null;
    }
}
