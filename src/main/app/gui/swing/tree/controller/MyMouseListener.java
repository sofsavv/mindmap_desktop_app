package main.app.gui.swing.tree.controller;

import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.ProjectView;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 2){

            MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
            MapNode node = selected.getMapNode();

            if(node instanceof Project) {
                if(!MainFrame.getInstance().getCurrProjectView().getMapViewList().isEmpty()){
                    MindMapView first = MainFrame.getInstance().getCurrProjectView().getMapViewList().get(0);
                    MainFrame.getInstance().getCurrProjectView().setCurrMapView(first);
                }
                showProjectView((Project) node);

            }else if(node instanceof MindMap){
                showProjectView((Project) node.getParent());
                JTabbedPane tabbedP = MainFrame.getInstance().getCurrProjectView().getTabbedPane();
                int i = 0;
                for(MapNode map: ((Project) node.getParent()).getChildren()){
                    if(node.getName().equals(map.getName())) {
                        for(MindMapView mapView: MainFrame.getInstance().getCurrProjectView().getMapViewList()){
                            if(mapView.getMindMap().equals(map))
                                MainFrame.getInstance().getCurrProjectView().setCurrMapView(mapView);
                        }
                        tabbedP.setSelectedIndex(i);
                    }
                    else i++;
                }
            }
        }
    }
    private void showProjectView(Project project){
        for(ProjectView projectView: MainFrame.getInstance().getProjectViewList()){
            if(projectView.getProject().equals(project)){
                if(!projectView.getMapViewList().isEmpty()){
                    int i = projectView.getTabbedPane().getSelectedIndex();
                    projectView.setCurrMapView(projectView.getMapViewList().get(i));
                }
                projectView.update(MyNotification.SHOW);
                return;
            }
        }
        ProjectView projectView = new ProjectView(project);
        projectView.update(MyNotification.NEW);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
