package main.app.mapRepository.implementation;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite{

    public ProjectExplorer(){};
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {

    }

    @Override
    public void deleteChild(MapNode child) {

    }
}
