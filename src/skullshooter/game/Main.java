package skullshooter.game;

import skullshooter.engine.assets.Assets;
import skullshooter.engine.ecs.Entity;
import skullshooter.engine.physics.Collider;
import skullshooter.engine.physics.Gravity;
import skullshooter.engine.physics.PhysicsManager;
import skullshooter.engine.render.Game;
import skullshooter.engine.render.RectRenderer;
import skullshooter.engine.render.SpriteRenderer;
import skullshooter.engine.scenes.Scene;
import skullshooter.engine.scenes.SceneManager;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Assets.init();
        PhysicsManager.init();

        SceneManager.init(new Scene(
                new Entity("Background", 0, 0, 1280, 720,
                    new SpriteRenderer(Assets.getImage("background.png"))
                ),

                new Entity("Test", 0, 0, 100, 100,
                    new SpriteRenderer(Assets.getImage("player.png")),
                    new Collider(),
                    new Gravity(),
                    new Player()
                ),

                new Entity("Test", 0, 620, 1280, 100,
                    new SpriteRenderer(Assets.getImage("grass.png")),
                    new Collider()
                )
        ));

        new Game();
    }
}