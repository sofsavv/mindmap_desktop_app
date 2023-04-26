package main.app.mapRepository.factory.concrete;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.factory.NodeFactory;
import main.app.mapRepository.implementation.ProjectExplorer;

public class ProjExplorerFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent) {
        return new ProjectExplorer("Project Explorer");
    }
}
