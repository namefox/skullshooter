package skullshooter.game;

import skullshooter.engine.animation.Animation;
import skullshooter.engine.assets.Assets;
import skullshooter.engine.ecs.Entity;
import skullshooter.engine.physics.Collider;
import skullshooter.engine.physics.Gravity;
import skullshooter.engine.physics.PhysicsManager;
import skullshooter.engine.render.Game;
import skullshooter.engine.render.SpriteRenderer;
import skullshooter.engine.scenes.Scene;
import skullshooter.engine.scenes.SceneManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Assets.init();
        PhysicsManager.init();

        SceneManager.init(new Scene(
                new Entity("Background", 0, 0, 1280, 720,
                        new SpriteRenderer(Assets.getImage("environment/background.png"))
                ),
                new Entity("Player", 100, 100, 100, 100,
                        new SpriteRenderer(null),
                        new Animation(12, Assets.getImage("skeleton/idle.png"), 32, 32),
                        new Collider(40, 90, 25, 20),
                        new Gravity(),
                        new Player()
                ),
                new Entity("Grass", 0, 620, 1280, 100,
                        new SpriteRenderer(Assets.getImage("environment/grass.png")),
                        new Collider()
                ),
                new Entity("Vignette", 0, 0, 1280, 720,
                        new SpriteRenderer(Assets.getImage("environment/vignette.png"))
                )
        ));

        new Game();
    }
}