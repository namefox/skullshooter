package skullshooter.other;

import skullshooter.engine.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Assets.init();

        SceneManager.init(new Scene(
                new Entity("Test", 0, 0, 100, 100,
                    new RectRenderer()
                ),

                new Entity("Test", 0, 150, 100, 100,
                    new RectRenderer()
                )
        ));

        new Game();
    }
}