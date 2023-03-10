package skullshooter.engine.render;

import skullshooter.engine.ecs.Component;

import java.awt.*;

public class RectRenderer extends Component {

    private Color color;

    public RectRenderer(Color color) {
        super("RectRenderer");
        this.color = color;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect(transform.getX(), transform.getY(), transform.getSizeX(), transform.getSizeY());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}