package skullshooter.engine;

import java.awt.*;
import java.util.ArrayList;

public class Scene {

    private final ArrayList<Entity> entities;
    private final ArrayList<Entity> addedEntities;

    public Scene(Entity... entities) {
        this.entities = new ArrayList<>();
        this.addedEntities = new ArrayList<>();

        addEntity(entities);

        for (Entity entity: entities) {
            entity.init(this);
            this.entities.add(entity);
        }
    }

    public void update() {
        // Can't use forEach because that gives a ConcurrentModificationException.
        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void render(Graphics2D g) {
        // Can't use forEach because that gives a ConcurrentModificationException.
        // noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public Scene onLoad() {
        for (Entity entity:
             entities) {
            entity.init(this);
        }
        return this;
    }

    public Scene onUnload() {
        for (Entity entity:
                entities) {
            entity.reset();
        }
        removeEntity(addedEntities.toArray(new Entity[0]));

        return this;
    }

    public Scene addEntity(Entity... entities) {
        for (Entity entity: entities) {
            entity.init(this);

            this.entities.add(entity);
            this.addedEntities.add(entity);
        }

        return this;
    }

    public Scene removeEntity(Entity... entities) {
        for (Entity entity: entities) {
            entity.reset();
            this.entities.remove(entity);
        }

        return this;
    }
}