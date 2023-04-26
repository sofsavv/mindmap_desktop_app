package main.app.mapRepository.factory.concrete;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.factory.NodeFactory;
import main.app.mapRepository.implementation.Element;

public class ElementFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent) {
        return new Element("Element", parent);
    }
}
