package main.app.gui.swing.tree;

import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.tree.view.MapTreeView;
import main.app.gui.swing.view.ProjectView;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;
import main.app.mapRepository.factory.FactoryUtils;
import main.app.mapRepository.factory.NodeFactory;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;
import main.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeImplementation implements MapTree{
    private MapTreeView mapTreeView;
    private DefaultTreeModel defaultTreeModel;
    private static int count = 1;
    private static int mapCount = 1;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        defaultTreeModel = new DefaultTreeModel(root);
        mapTreeView = new MapTreeView(defaultTreeModel);
        return mapTreeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {
        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;
        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        ((MapNode)child).setParent(parent.getMapNode());
        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
        for(int i = 0; i < mapTreeView.getRowCount(); i++){
            mapTreeView.expandRow(i);
        }
    }

    @Override
    public void deleteChild(MapTreeItem child){

        MapNodeComposite parent = (MapNodeComposite)child.getMapNode().getParent();
        child.removeFromParent();
        parent.deleteChild(child.getMapNode());
        ((MapNodeComposite)child.getMapNode()).getChildren().clear();
        SwingUtilities.updateComponentTreeUI(mapTreeView);

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem)mapTreeView.getLastSelectedPathComponent() ;
    }

    @Override
    public void loadProject(Project project) {

        ProjectExplorer projectExplorer = (ProjectExplorer) ((MapTreeItem) defaultTreeModel.getRoot()).getMapNode();
        for(MapNode node: projectExplorer.getChildren()){
            if(node.getName().equals(project.getName())){
                node.setName(node.getName() + "(" + count + ")");
                count++;
            }
        }
        projectExplorer.addChild(project);

        ProjectView projectView = new ProjectView(project);
        projectView.setProject(project);

        MapTreeItem loaded = new MapTreeItem(project);
        ((MapTreeItem)defaultTreeModel.getRoot()).add(loaded);

        for(MapNode child: project.getChildren()){
            MapTreeItem item = new MapTreeItem(child);
            loaded.add(item);
        }
        for(MapNode child : project.getChildren()){
            MindMap mindMap = (MindMap) child;
            mindMap.setParent(project);
            mindMap.notifyView(MyNotification.OPENED, mindMap);
        }
    }

    @Override
    public void loadMap(MindMap map, MapTreeItem selected) {

        MapTreeItem loaded = new MapTreeItem(map);
        Project project = (Project) selected.getMapNode();

        for(MapNode mapNode: project.getChildren()){
            if(mapNode.getName().equals(map.getName()))
                map.setName(map.getName() + "(" + mapCount + ")");
        }
        selected.add(loaded);
        map.setParent(project);
        project.addChild(map);
        map.notifyView(MyNotification.OPENED, map);

    }

    private MapNode createChild(MapNode parent) {
        NodeFactory nodeFactory = FactoryUtils.getFactory(parent);
        return nodeFactory.getNode(parent);
    }
}
