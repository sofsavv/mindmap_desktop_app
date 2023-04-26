package main.app.mapRepository.factory.concrete;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.factory.NodeFactory;
import main.app.mapRepository.implementation.MindMap;

public class MindMapFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent) {
        return new MindMap("MinMap", parent);
    }
}
