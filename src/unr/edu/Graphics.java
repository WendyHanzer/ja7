package unr.edu;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by cam on 10/7/14.
 */
public class Graphics implements Manager {
    private Engine engine;
    private List<Terrain> terrain_vec = new ArrayList<>();
    private List<Shape> shape_vec = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    public Matrix4f projection, view;

    public Camera camera;

    public Graphics(Engine e)
    {
        engine = e;
    }

    public void render()
    {
        updateCamera();
        updateView();

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for(Entity e : entities)
            e.render();

        Display.update();
    }

    @Override
    public void init()
    {
        camera = new Camera(engine);

        PixelFormat pixelFormat = new PixelFormat();
        ContextAttribs attributes = new ContextAttribs(4,1)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(1024, 768));
            Display.setTitle("CS791a");
            Display.create(pixelFormat, attributes);
        } catch (LWJGLException e) {
            e.printStackTrace();
            engine.stop();
        }

        initGL();
    }

    private void initGL()
    {
        if(engine.options.verbose)
            System.out.println(glGetString(GL_VERSION));

        glClearColor(0.0f,0.2f,0.2f,1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);

        if(engine.options.wireframe)
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

        projection = perspective(45.0f, (float)Display.getWidth() / (float)Display.getHeight(), 0.01f, 5000.0f);
    }

    @Override
    public void tick(float dt)
    {
        /*
        for(Terrain t : terrain_vec)
            t.tick(dt);

        for(Shape s : shape_vec)
            s.tick(dt);
        */

        for(Entity e : entities)
            e.tick(dt);
    }

    @Override
    public void stop()
    {
        Display.destroy();
    }

    private static Matrix4f perspective(float fovy, float aspect, float near, float far)
    {
        Matrix4f ret = new Matrix4f();

        float l_fd = (float)(1.0 / Math.tan((fovy * (Math.PI / 180)) / 2.0));
        float l_a1 = (far + near) / (near - far);
        float l_a2 = (2 * far * near) / (near - far);

        ret.m00 = l_fd / aspect;
        ret.m10 = 0;
        ret.m20 = 0;
        ret.m30 = 0;
        ret.m01 = 0;
        ret.m11 = l_fd;
        ret.m21 = 0;
        ret.m31 = 0;
        ret.m02 = 0;
        ret.m12 = 0;
        ret.m22 = l_a1;
        ret.m32 = -1;
        ret.m03 = 0;
        ret.m13 = 0;
        ret.m23 = l_a2;
        ret.m33 = 0;

        return ret;
    }

    private void updateCamera()
    {
        camera.update();
    }

    private void updateView()
    {
        view = camera.getView();
    }
}
