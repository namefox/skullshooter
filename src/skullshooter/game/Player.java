package skullshooter.game;

import skullshooter.engine.ecs.Component;
import skullshooter.engine.input.Input;
import skullshooter.engine.input.InputCode;
import skullshooter.engine.physics.Collider;
import skullshooter.engine.physics.Gravity;

public class Player extends Component {

    private static final int speed = 10;
    private Gravity gravity;
    private Collider collider;
    private boolean isGrounded;

    public Player() {
        super("Player");
    }

    @Override
    public void enable() {
        super.enable();

        gravity = (Gravity) transform.getComponent("Gravity");
        collider = (Collider) transform.getComponent("Collider");
    }

    @Override
    public void update() {
        isGrounded = collider.collision != null;

        if (Input.getKeyDown(InputCode.LEFT) || Input.getKeyDown(InputCode.A))
            transform.setX(transform.getX() - speed);
        if (Input.getKeyDown(InputCode.RIGHT) || Input.getKeyDown(InputCode.D))
            transform.setX(transform.getX() + speed);
        if ((Input.getKeyDown(InputCode.UP) || Input.getKeyDown(InputCode.W) || Input.getKeyDown(InputCode.SPACE)) && isGrounded)
            gravity.setVelocity(-speed);

        super.update();
    }
}