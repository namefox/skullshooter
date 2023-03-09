package skullshooter.engine;

import java.awt.*;
import java.util.ArrayList;

public class Entity {

    private String name;
    private int x, y, sX, sY;

    private Scene scene;
    private final ArrayList<Component> components;

    private Entity originalValues;

    public Entity(String name, int x, int y, int sX, int sY, Component... components) {
        this(name, x, y, sX, sY, true, components);
    }

    public Entity(String name, int x, int y, int sX, int sY, boolean hasOriginalValues, Component... components) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;

        this.components = new ArrayList<>();
        addComponent(components);

        if (hasOriginalValues)
            this.originalValues = clone();
    }

    public void init(Scene scene) {
        this.scene = scene;
    }

    public void update() {
        // Can't use forEach because that gives a ConcurrentModificationException.
        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update();
        }
    }

    public void render(Graphics2D g) {
        // Can't use forEach because that gives a ConcurrentModificationException.
        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render(g);
        }
    }

    public void reset() {
        this.x = originalValues.getX();
        this.y = originalValues.getY();
        this.sX = originalValues.getSizeX();
        this.sY = originalValues.getSizeY();

        for (Component component:
             components) {
            component.reset();
        }

        removeComponent(getComponents());
        addComponent(originalValues.getComponents());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeX() {
        return sX;
    }

    public void setSizeX(int sX) {
        this.sX = sX;
    }

    public int getSizeY() {
        return sY;
    }

    public void setSizeY(int sY) {
        this.sY = sY;
    }

    public Component[] getComponents() {
        return components.toArray(new Component[0]);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void addComponent(Component... components) {
        for (Component component:
             components) {
            component.init(this);
            this.components.add(component);

            component.enable();
            component.awake();
        }
    }

    public void removeComponent(Component... components) {
        for (Component component:
                components) {
            this.components.remove(component);
            component.disable();
        }
    }

    @Override
    public Entity clone() {
        return new Entity(name, x, y, sX, sY, false, getComponents());
    }
}