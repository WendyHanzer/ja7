package unr.edu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cam on 10/7/14.
 */
public class Input implements Manager {
    private Engine engine;
    private Map<Integer,Boolean> keyPresses = new TreeMap<>();

    public Input(Engine e)
    {
        engine = e;
    }

    @Override
    public void init() {
        Mouse.setGrabbed(true);

        initKeyPresses();
    }

    private void initKeyPresses()
    {
        keyPresses.put(Keyboard.KEY_W, false);
        keyPresses.put(Keyboard.KEY_A, false);
        keyPresses.put(Keyboard.KEY_S, false);
        keyPresses.put(Keyboard.KEY_D, false);
        keyPresses.put(Keyboard.KEY_R, false);
        keyPresses.put(Keyboard.KEY_F, false);
        //keyPresses.put(Keyboard.KEY_W, false);
    }

    @Override
    public void tick(float dt)
    {
        while(Keyboard.next()) {

            // keydown
            if(Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()) {
                    case Keyboard.KEY_ESCAPE:
                        engine.stop();
                        break;

                    case Keyboard.KEY_W:
                        keyPresses.put(Keyboard.KEY_W, true);
                        break;

                    case Keyboard.KEY_A:
                        keyPresses.put(Keyboard.KEY_A, true);
                        break;

                    case Keyboard.KEY_S:
                        keyPresses.put(Keyboard.KEY_S, true);
                        break;

                    case Keyboard.KEY_D:
                        keyPresses.put(Keyboard.KEY_D, true);
                        break;

                    case Keyboard.KEY_R:
                        keyPresses.put(Keyboard.KEY_R, true);
                        break;

                    case Keyboard.KEY_F:
                        keyPresses.put(Keyboard.KEY_F, true);
                        break;
                }
            }

            // keyup
            else {
                switch(Keyboard.getEventKey()) {
                    case Keyboard.KEY_ESCAPE:
                        engine.stop();
                        break;

                    case Keyboard.KEY_W:
                        keyPresses.put(Keyboard.KEY_W, false);
                        break;

                    case Keyboard.KEY_A:
                        keyPresses.put(Keyboard.KEY_A, false);
                        break;

                    case Keyboard.KEY_S:
                        keyPresses.put(Keyboard.KEY_S, false);
                        break;

                    case Keyboard.KEY_D:
                        keyPresses.put(Keyboard.KEY_D, false);
                        break;

                    case Keyboard.KEY_R:
                        keyPresses.put(Keyboard.KEY_R, false);
                        break;

                    case Keyboard.KEY_F:
                        keyPresses.put(Keyboard.KEY_F, false);
                        break;
                }
            }
        }

        // handle mouse motion
        float x = Mouse.getDX();
        float y = Mouse.getDY();

        System.out.print("Mouse x: " + x + " y: " + y + "\r");

        handleKeyPresses();

    }

    private void handleKeyPresses()
    {
        if(keyPresses.get(Keyboard.KEY_W)) {
            engine.graphics.camera.moveForward();
        }

        if(keyPresses.get(Keyboard.KEY_A)) {
            engine.graphics.camera.moveLeft();
        }

        if(keyPresses.get(Keyboard.KEY_S)) {
            engine.graphics.camera.moveBackward();
        }

        if(keyPresses.get(Keyboard.KEY_D)) {
            engine.graphics.camera.moveRight();
        }

        if(keyPresses.get(Keyboard.KEY_R)) {
            engine.graphics.camera.moveUp();
        }

        if(keyPresses.get(Keyboard.KEY_F)) {
            engine.graphics.camera.moveDown();
        }

    }

    @Override
    public void stop() {

    }
}
