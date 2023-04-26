package main.app.mapRepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode{

    private List<MapNode> children;
    private int index = 1;

    public MapNodeComposite(){};

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }
    public MapNodeComposite(String name, MapNode parent, List<MapNode> children) {
        super(name, parent);
        this.children = children;
    }

    public abstract void addChild(MapNode child);
    public abstract void deleteChild(MapNode child);

}
