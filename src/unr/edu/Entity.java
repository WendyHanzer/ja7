package unr.edu;

/**
 * Created by cam on 10/7/14.
 */
public interface Entity {
    public void init();
    public void initGL();
    public void tick(float dt);
    public void render();
}
