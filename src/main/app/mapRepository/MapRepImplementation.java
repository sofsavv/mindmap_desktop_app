package main.app.mapRepository;

import main.app.core.MapRepository;
import main.app.mapRepository.implementation.ProjectExplorer;

public class MapRepImplementation implements MapRepository{

    private ProjectExplorer projectExplorer;

    public MapRepImplementation() {
        projectExplorer = new ProjectExplorer("Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() { return projectExplorer; }

//    @Override
//    public void addChild(MapNodeComposite parent, MapNode child) {}
//
//    @Override
//    public void undoCommand(MindMap mindMap) {}

}
