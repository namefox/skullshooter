package skullshooter.engine.physics;

import skullshooter.engine.ecs.Component;

import java.awt.*;

public class Collider extends Component {

    public Rectangle bounds;
    public Collider collision;
    public int offsetX, offsetY;

    private boolean replaceBoundsOnEnable;

    public Collider(int width, int height, int offsetX, int offsetY) {
        super("Collider");

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.bounds = new Rectangle(offsetX, offsetY, width, height);
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
    public void render(Graphics2D g) {
        g.draw(bounds);
        super.render(g);
    }

    @Override
    public void disable() {
        PhysicsManager.colliders.remove(this);
        super.disable();
    }
}