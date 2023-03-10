package skullshooter.engine.physics;

import skullshooter.engine.ecs.Component;

import java.awt.*;

public class Collider extends Component {

    public Rectangle bounds;
    public Collider collision;

    public Collider(int width, int height) {
        super("Collider");

        this.bounds = new Rectangle(0, 0, width, height);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.white);
        g.draw(bounds);
    }

    @Override
    public void enable() {
        PhysicsManager.colliders.add(this);
        super.enable();
    }

    @Override
    public void disable() {
        PhysicsManager.colliders.remove(this);
        super.disable();
    }
}