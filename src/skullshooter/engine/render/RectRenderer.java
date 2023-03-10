package skullshooter.engine.render;

import skullshooter.engine.ecs.Component;

import java.awt.*;

public class RectRenderer extends Component {

    public RectRenderer() {
        super("RectRenderer");
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(transform.getX(), transform.getY(), transform.getSizeX(), transform.getSizeY());
    }
}