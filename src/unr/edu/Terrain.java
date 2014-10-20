package unr.edu;

import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

/**
 * Created by cam on 10/7/14.
 */
public class Terrain implements Entity {
    private int vbo, vao;

    private float[] geometry;

    @Override
    public void init() {
        List<Float> geom = new ArrayList<>();

        geom.add(-1.0f);
        geom.add(0.0f);
        geom.add(0.0f);

        geom.add(0.0f);
        geom.add(1.0f);
        geom.add(0.0f);

        geom.add(1.0f);
        geom.add(0.0f);
        geom.add(0.0f);

        geometry = new float[geom.size()];

        int i = 0;
        for(Float f : geom) {
            geometry[i++] = f;
        }

        initGL();
    }

    @Override
    public void initGL() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glBufferData(GL_ARRAY_BUFFER, FloatBuffer.wrap(geometry), GL_STATIC_DRAW);
    }

    @Override
    public void tick(float dt) {

    }

    @Override
    public void render() {
        GL30.glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

    }
}
