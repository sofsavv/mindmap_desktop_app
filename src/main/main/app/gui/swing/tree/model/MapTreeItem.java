package main.app.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import main.app.mapRepository.composite.MapNode;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;
    public MapTreeItem(MapNode nodeModel){
        this.mapNode = nodeModel;
    }

    @Override
    public String toString() {
        return mapNode.getName();
    }

    public void setName(String name){
        this.mapNode.setName(name);
    }


}
