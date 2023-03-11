package skullshooter.game;

import skullshooter.engine.animation.Animation;
import skullshooter.engine.assets.Assets;
import skullshooter.engine.ecs.Component;
import skullshooter.engine.input.Input;
import skullshooter.engine.input.InputCode;
import skullshooter.engine.physics.Collider;
import skullshooter.engine.physics.Gravity;
import skullshooter.engine.scenes.SceneManager;

public class Player extends Component {

    private static final int speed = 5;
    private Gravity gravity;
    private Collider collider;
    private Animation animation;
    private boolean left = false;

    public Player() {
        super("Player");
    }

    @Override
    public void enable() {
        super.enable();

        gravity = (Gravity) transform.getComponent("Gravity");
        collider = (Collider) transform.getComponent("Collider");
        animation = (Animation) transform.getComponent("Animation");
    }

    @Override
    public void update() {
        boolean isGrounded = collider.collision != null;

        if (Input.getKeyDown(InputCode.LEFT) || Input.getKeyDown(InputCode.A)) {
            transform.setX(transform.getX() - speed);
            animation.setSpritesheet(Assets.getImage("skeleton/left/run.png"));
            left = true;
        } else if (Input.getKeyDown(InputCode.RIGHT) || Input.getKeyDown(InputCode.D)) {
            transform.setX(transform.getX() + speed);
            animation.setSpritesheet(Assets.getImage("skeleton/run.png"));
            left = false;
        } else {
            animation.setSpritesheet(Assets.getImage("skeleton/" + (left ? "left/" : "") + "idle.png"));
        }

        if ((Input.getKeyDown(InputCode.UP) || Input.getKeyDown(InputCode.W) || Input.getKeyDown(InputCode.SPACE)) && isGrounded)
            gravity.setVelocity(-speed * 2);

        if (!isGrounded)
            animation.setSpritesheet(Assets.getImage("skeleton/" + (left ? "left/" : "") + "jump.png"));

        if (transform.getY() > 720) SceneManager.load(0);

        super.update();
    }
}