package main.app.gui.swing.tree;

import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.tree.view.MapTreeView;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;
import main.app.mapRepository.implementation.ProjectExplorer;

public interface MapTree {

    MapTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(MapTreeItem parent);
    void deleteChild(MapTreeItem parent);
    MapTreeItem getSelectedNode();

    void loadProject(Project project);
    void loadMap(MindMap map, MapTreeItem selected);


}
