package main.app.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;
@Getter
@Setter
public class Project extends MapNodeComposite{

    private String author;
    private String filePath = null;
    private boolean changed = true;

    public Project(){}

    public Project(String author, MapNode parent) {
        super(author, parent);
    }

    @Override
    public void addChild(MapNode child) {
        if(child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            mindMap.setParent(this);
            if(!this.getChildren().contains(mindMap)){
                this.getChildren().add(mindMap);
                notify(MyNotification.ADD_CHILD);
            }
        }
        changed = true;
    }

    @Override
    public void deleteChild(MapNode child) {
        if(child instanceof MindMap){
            if(this.getChildren().contains((MindMap)child)){
                this.getChildren().remove((MindMap)child);
                notify(MyNotification.DELETE_CHILD);
            }
        }
        changed = true;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        changed = true;
        notify(MyNotification.RENAME);
    }

    public void setAuthor(String author) {
        this.author = author;
        changed = true;
        notify(MyNotification.AUTHOR);
    }

}
