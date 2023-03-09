package skullshooter.engine;

import java.awt.*;

public abstract class Component {

    protected String name;
    protected Entity transform;

    public Component(String name) {
        this.name = name;
    }

    protected void init(Entity transform) {
        this.transform = transform;
    }

    public void awake() {}
    public void update() {}
    public void render(Graphics2D g) {}

    public void enable() {}
    public void disable() {}

    public void reset() {}
}