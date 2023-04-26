package main.app.mapRepository.implementation;

import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite{

    public ProjectExplorer(){}

    public ProjectExplorer(String name){
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {
        if(child instanceof Project) {
            Project project = (Project) child;
            project.setParent(this);
            if (!this.getChildren().contains(project)) {
                this.getChildren().add(project);
                notify(MyNotification.ADD_CHILD);
            }
        }
    }

    @Override
    public void deleteChild(MapNode child) {
        if(child instanceof Project){
            if(this.getChildren().contains((Project)child)){
                this.getChildren().remove((Project)child);
                notifyView(MyNotification.DELETE, child);
            }
        }
    }
}
