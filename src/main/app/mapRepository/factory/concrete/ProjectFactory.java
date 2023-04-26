package main.app.mapRepository.factory.concrete;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.factory.NodeFactory;
import main.app.mapRepository.implementation.Project;

public class ProjectFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent) {
        return new Project("Project", parent);
    }
}
