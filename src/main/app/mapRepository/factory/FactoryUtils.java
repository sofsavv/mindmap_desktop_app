package main.app.mapRepository.factory;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.factory.concrete.ElementFactory;
import main.app.mapRepository.factory.concrete.MindMapFactory;
import main.app.mapRepository.factory.concrete.ProjExplorerFactory;
import main.app.mapRepository.factory.concrete.ProjectFactory;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;
import main.app.mapRepository.implementation.ProjectExplorer;

public class FactoryUtils {

    public static NodeFactory getFactory(MapNode parent){

        if(parent instanceof ProjectExplorer){
            return new ProjectFactory();

        }else if(parent instanceof Project){
            return new MindMapFactory();

        }else if(parent instanceof MindMap){
            return new ElementFactory();

        }else if(parent == null){
            return new ProjExplorerFactory();

        }else {
            return null;
        }
    }
}
