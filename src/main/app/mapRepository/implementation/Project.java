package main.app.mapRepository.implementation;

import lombok.Getter;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;
@Getter
public class Project extends MapNodeComposite{

    private String author;
    private String filePath = null;
    private boolean changed = true;

    public Project(){};

    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {

    }

    @Override
    public void deleteChild(MapNode child) {

    }

    @Override
    public void setName(String name) {
        super.setName(name);
        changed = true;
    }

    public void setAuthor(String author) {
        this.author = author;
        changed = true;
    }

}
