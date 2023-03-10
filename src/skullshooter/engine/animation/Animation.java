package skullshooter.engine.animation;

import skullshooter.engine.ecs.Component;
import skullshooter.engine.render.SpriteRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Animation extends Component {

    private SpriteRenderer renderer;
    private float maxTime;
    private float timer;
    private BufferedImage spritesheet;
    private int w;
    private int h;
    private int x;
    private int y;

    public Animation(float fps, BufferedImage spritesheet, int w, int h) {
        super("Animation");

        this.maxTime = fps;
        this.spritesheet = spritesheet;
        this.w = w;
        this.h = h;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void update() {
        timer += 1f;

        if (timer >= maxTime) {
            x += w;
            if (x >= spritesheet.getWidth()) {
                x = 0;
                y += h;
                if (y >= spritesheet.getHeight()) {
                    y = 0;
                }
            }

            BufferedImage image = spritesheet.getSubimage(x, y, w, h);
            if (isEmpty(image)) return;

            renderer.setImage(image);
            timer = 0;
        }

        super.update();
    }

    private boolean isEmpty(BufferedImage image) {
        byte[] pixels1 = getPixels(image);
        byte[] pixels2 = getPixels(new BufferedImage(image.getWidth(), image.getHeight(), image.getType()));

        return Arrays.equals(pixels1, pixels2);
    }

    private static byte[] getPixels(BufferedImage img) {
        byte[] imageInByte = null;
        String format = "png";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, format, baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageInByte;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public BufferedImage getSpritesheet() {
        return spritesheet;
    }

    public void setSpritesheet(BufferedImage spritesheet) {
        this.spritesheet = spritesheet;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void enable() {
        renderer = (SpriteRenderer) transform.getComponent("SpriteRenderer");
        renderer.setImage(spritesheet.getSubimage(0, 0, w, h));
        super.enable();
    }
}