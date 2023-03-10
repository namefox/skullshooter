package skullshooter.engine.physics;

import java.util.LinkedList;

public final class PhysicsManager {

    public static LinkedList<Collider> colliders;

    public static void init() {
        colliders = new LinkedList<>();
    }

    public static void update() {
        for (int i = 0; i < colliders.size(); i++) {
            Collider c = colliders.get(i);
            c.bounds.x = c.transform.getX();
            c.bounds.y = c.transform.getY();

            //noinspection ForLoopReplaceableByForEach
            for (int j = 0; j < colliders.size(); j++) {
                Collider b = colliders.get(j);
                b.bounds.x = b.transform.getX();
                b.bounds.y = b.transform.getY();

                if (!c.equals(b) && c.bounds.intersects(b.bounds)) {
                    c.collision = b;
                    b.collision = c;
                } else {
                    if (c.collision == b) c.collision = null;
                    if (b.collision == c) b.collision = null;
                }
            }
        }
    }
}