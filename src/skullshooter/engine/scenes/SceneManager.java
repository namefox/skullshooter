package skullshooter.engine.scenes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SceneManager {

    private static ArrayList<Scene> scenes;
    private static Scene currentScene;

    public static void init(Scene... scenes) {
        SceneManager.scenes = new ArrayList<>();
        SceneManager.scenes.addAll(Arrays.asList(scenes));

        currentScene = scenes[0];
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void update() {
        currentScene.update();
    }

    public static void render(Graphics2D g) {
        currentScene.render(g);
    }

    public static void load(int index) {
        currentScene.onUnload();

        currentScene = scenes.get(index);
        currentScene.onLoad();
    }
}