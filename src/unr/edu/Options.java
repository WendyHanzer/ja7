package unr.edu;

import java.util.List;
/**
 * Created by cam on 10/7/14.
 */
public class Options {
    public boolean verbose;
    public String color_map;
    public List<String> terrain;
    public List<String> shapes;
    public float height_scalar;
    public float map_scalar;
    public boolean wireframe;
    public float camera_sensitivity;
    public float camera_speed;
    public String data_directory;
    public float sample_probability;

    @Override
    public String toString() {
        return "Options {" +
                "verbose=" + verbose +
                ", color_map='" + color_map + '\'' +
                ", terrain=" + terrain +
                ", shapes=" + shapes +
                ", height_scalar=" + height_scalar +
                ", map_scalar=" + map_scalar +
                ", wireframe=" + wireframe +
                ", camera_sensitivity=" + camera_sensitivity +
                ", camera_speed=" + camera_speed +
                ", data_directory='" + data_directory + '\'' +
                ", sample_probability=" + sample_probability +
                '}';
    }
}
