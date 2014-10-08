package unr.edu;

/**
 * Created by cam on 10/7/14.
 */
public class Clock {
    private long t1, t2;
    private float dt, totalTime;

    public Clock()
    {
        t1 = t2 = 0L;
    }

    public void init()
    {
        t1 = System.nanoTime();
    }

    public float getDT()
    {
        return dt;
    }

    public float tick()
    {
        return tick(0L);
    }

    public float tick(long framerate)
    {
        if(framerate > 0L) {
            long sleepTime = 1000 / framerate;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        t2 = System.nanoTime();
        dt = (float)(t2 - t1) / 1000000000.0f;
        totalTime += dt;
        t1 = t2;

        return dt;
    }

    public float getTotalTime()
    {
        return totalTime;
    }
}
