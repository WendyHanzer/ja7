package unr.edu;

//import org.apache.commons.cli.*;

import org.apache.commons.cli.*;
import org.gdal.gdal.gdal;
import org.gdal.ogr.ogr;

import java.util.ArrayList;

/**
 * Created by cam on 10/7/14.
 */

public class Engine {
    public String[] args;
    public Options options;

    private Clock clock;
    public Input input;
    public Graphics graphics;

    public boolean running = true;

   public Engine(String[] cmd_args)
   {
       args = cmd_args;
       parseCmdArgs();

       clock = new Clock();

       init();
   }

    public void init()
    {
        gdal.AllRegister();
        ogr.RegisterAll();

        graphics = new Graphics(this);
        input = new Input(this);

        graphics.init();
        input.init();
    }

    public void run()
    {
        float dt;

        clock.init();

        while(running) {
            dt = clock.tick();
            input.tick(dt);
            graphics.tick(dt);
            graphics.render();
        }

        stop();
    }

    public void stop()
    {
        stop(0);
    }

    public void stop(int exitCode)
    {
        input.stop();
        graphics.stop();

        System.exit(exitCode);
    }

    public void parseCmdArgs()
    {
        options = new Options();

        org.apache.commons.cli.Options opts = new org.apache.commons.cli.Options();

        opts.addOption("h", "help", false, "Print Help Message")
                .addOption("v", "verbose", false, "Verbose Output")
                .addOption("s", "scalar", true, "Height Scalar Value")
                .addOption("m", "map-scalar", true, "Map Scalar Value")
                .addOption("t", "texture", true, "Texture File")
                .addOption("w", "wireframe", false, "Only Render Wireframe")
                .addOption("", "sensitivity", true, "Mouse Sensitivity")
                .addOption("", "speed", true, "Camera Speed")
                .addOption("d", "data", true, "Data Directory")
                .addOption("p", "prob", true, "Sample Probability");

        CommandLineParser parser = new GnuParser();
        CommandLine cmdLine = null;

        try {
            cmdLine = parser.parse(opts, args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if(cmdLine.hasOption("h")) {
            HelpFormatter help = new HelpFormatter();
            help.printHelp(HelpFormatter.DEFAULT_ARG_NAME, opts, true);
            System.exit(0);
        }

        String opStr;

        opStr = cmdLine.getOptionValue("s");
        options.height_scalar = (opStr != null) ? Float.parseFloat(opStr) : 2.0f;

        opStr = cmdLine.getOptionValue("m");
        options.map_scalar = (opStr != null) ? Float.parseFloat(opStr) : 1.0f;

        opStr = cmdLine.getOptionValue("t");
        options.color_map = (opStr != null) ? opStr : "../colorMap.png";

        opStr = cmdLine.getOptionValue("sensitivity");
        options.camera_sensitivity = (opStr != null) ? Float.parseFloat(opStr) : 0.25f;

        opStr = cmdLine.getOptionValue("speed");
        options.camera_speed = (opStr != null) ? Float.parseFloat(opStr) : 5.0f;

        opStr = cmdLine.getOptionValue("d");
        options.data_directory = (opStr != null) ? opStr : "../DryCreek/isnobaloutput";

        opStr = cmdLine.getOptionValue("p");
        options.sample_probability = (opStr != null) ? Float.parseFloat(opStr) : 0.1f;

        options.verbose = cmdLine.hasOption("v");
        options.wireframe = cmdLine.hasOption("w");

        options.terrain = new ArrayList<String>(3);
        options.terrain.add("../DryCreek/tl2p5_dem.ipw.tif");
        options.terrain.add("../DryCreek/tl2p5mask.ipw.tif");
        options.terrain.add("../DryCreek/DCEWsqrExtent.tif");

        options.shapes = new ArrayList<String>(3);
        options.shapes.add("../DryCreek/streamDCEW/streamDCEW.shp");
        options.shapes.add("../DryCreek/boundDCEW/boundDCEW.shp");
    }

    public static void main(String[] args)
    {
        Engine e = new Engine(args);

        e.run();
    }

    /*
    static
    {
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.indexOf("win") >= 0) {
            System.setProperty("java.library.path", "lib/native/windows");
        }

        else if(osName.indexOf("mac") >= 0) {
            System.setProperty("java.library.path", "lib/native/macosx");
        }

        else { // linux
            System.setProperty("java.library.path", "lib/native/linux");
        }
    }
    */
}
