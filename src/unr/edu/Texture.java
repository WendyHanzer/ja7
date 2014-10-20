package unr.edu;

import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

/**
 * Created by cam on 10/7/14.
 */
public class Texture {
    private Engine engine;
    private int texID;
    private int target;

    public Texture(Engine eng, String fileName)
    {
        this(eng, fileName, GL11.GL_TEXTURE_2D);
    }

    public Texture(Engine eng, String fileName, int tar)
    {
        engine = eng;
        target = tar;

        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            IntBuffer pixels = getPixels(img);

            texID = GL11.glGenTextures();

            if(engine.options.verbose)
                System.out.println("Texture ID: " + texID);

            GL11.glTexParameterf(target, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameterf(target, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

            if(target == GL11.GL_TEXTURE_2D) {
                GL11.glTexImage2D(target, 0, GL11.GL_RGBA, img.getWidth(), img.getHeight(),
                        0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            }

            else {
                GL11.glTexImage1D(target, 0, GL11.GL_RGBA, img.getWidth(),
                        0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int getID()
    {
        return texID;
    }

    public void bind()
    {
        GL11.glBindTexture(target, texID);
    }

    protected IntBuffer getPixels(BufferedImage image)
    {
        if (image == null) {
            throw new RuntimeException("Texture Image NULL");
        }

        int width = image.getWidth(), height = image.getHeight();

        int[] pixels = new int[width * height];

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i*height+j] = image.getRGB(j,i);
            }
        }

        return IntBuffer.wrap(pixels);
    }
}
