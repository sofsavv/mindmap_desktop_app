package main.app.mapRepository.implementation;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

public class MindMap extends MapNodeComposite {

    public MindMap(){};

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {

    }

    @Override
    public void deleteChild(MapNode child) {

    }
}
