package unr.edu;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL41.*;

/**
 * Created by cam on 10/7/14.
 */
public class Graphics implements Manager {
    private Engine engine;
    private List<Terrain> terrain_vec = new ArrayList<>();
    private List<Shape> shape_vec = new ArrayList<>();
    public Camera camera;

    public Graphics(Engine e)
    {
        engine = e;
    }

    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for(Terrain t : terrain_vec)
            t.render();

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


    }

    @Override
    public void tick(float dt)
    {
        for(Terrain t : terrain_vec)
            t.tick(dt);

        for(Shape s : shape_vec)
            s.tick(dt);
    }

    @Override
    public void stop()
    {

    }
}
