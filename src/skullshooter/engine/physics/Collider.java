package skullshooter.engine.physics;

import skullshooter.engine.ecs.Component;

import java.awt.*;

public class Collider extends Component {

    public Rectangle bounds;
    public Collider collision;

    private boolean replaceBoundsOnEnable;

    public Collider(int width, int height) {
        super("Collider");

        this.bounds = new Rectangle(0, 0, width, height);
    }

    public Collider() {
        super("Collider");
        replaceBoundsOnEnable = true;
    }

    @Override
    public void enable() {
        if (replaceBoundsOnEnable) {
            replaceBoundsOnEnable = false;
            this.bounds = new Rectangle(transform.getX(), transform.getY(), transform.getSizeX(), transform.getSizeY());
        }

        PhysicsManager.colliders.add(this);
        super.enable();
    }

    @Override
    public void disable() {
        PhysicsManager.colliders.remove(this);
        super.disable();
    }
}