package skullshooter.engine.render;

import skullshooter.engine.ecs.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component {

    private BufferedImage image;

    public SpriteRenderer(BufferedImage image) {
        super("SpriteRenderer");
        this.image = image;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, transform.getX(), transform.getY(), transform.getSizeX(), transform.getSizeY(), null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}