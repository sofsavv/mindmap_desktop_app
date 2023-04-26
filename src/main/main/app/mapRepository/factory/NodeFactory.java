package main.app.mapRepository.factory;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

public abstract class NodeFactory {

    public MapNode getNode(MapNode parent){

        MapNode mapNode = createNode(parent);
        mapNode.setParent(parent);

        if(parent instanceof MapNodeComposite){
            mapNode.setName(mapNode.getName() + "_" +((MapNodeComposite)parent).getIndex());
            ((MapNodeComposite)parent).setIndex(((MapNodeComposite)parent).getIndex()+1);
        }
        return mapNode;
    }

    public abstract MapNode createNode(MapNode parent);

}
