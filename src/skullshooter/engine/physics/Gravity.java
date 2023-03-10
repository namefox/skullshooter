package skullshooter.engine.physics;

import skullshooter.engine.ecs.Component;

public class Gravity extends Component {

    private float velocity;
    private Collider collider;
    private final float acceleration;

    public Gravity() {
        super("Gravity");
        this.velocity = 0;
        this.acceleration = 0.25f;
    }

    @Override
    public void enable() {
        super.enable();
        collider = (Collider) transform.getComponent("Collider");
    }

    @Override
    public void update() {
        super.update();

        if (collider != null && collider.collision != null && velocity > 0) {
            velocity = 0;
            return;
        }

        this.velocity += acceleration;
        transform.setY((int) (transform.getY() + velocity));
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}