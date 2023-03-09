package skullshooter.engine;

import java.awt.*;

public class RectRenderer extends Component {

    public RectRenderer() {
        super("RectRenderer");
    }

    @Override
    public void update() {
        super.update();
        transform.setX(transform.getX() + 1);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(transform.getX(), transform.getY(), transform.getSizeX(), transform.getSizeY());
    }
}