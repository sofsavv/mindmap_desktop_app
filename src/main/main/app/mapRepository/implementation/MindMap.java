package main.app.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.command.CommandManager;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

@Getter
@Setter
public class MindMap extends MapNodeComposite {

    private transient CommandManager commandManager;
    private boolean changed = true;
    private String filePath = null;
    public MindMap(){
        commandManager = new CommandManager();
    }

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        commandManager = new CommandManager();
        notify(MyNotification.NEW);
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void addChild(MapNode child) {
        if(child instanceof Element){
            Element element = (Element) child;
            element.setParent(this);
            if(!this.getChildren().contains(element)){
                this.getChildren().add(element);
                notifyView(MyNotification.ADD_CHILD, element);
            }
            else
                notifyView(MyNotification.OPENED, element);

        }
        changed = true;
    }

    @Override
    public void deleteChild(MapNode child) {
        if(child instanceof Element){
            Element element = (Element) child;
            if(this.getChildren().contains(element)) {
                this.getChildren().remove(element);
                notifyView(MyNotification.DELETE_CHILD, element);
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
}
