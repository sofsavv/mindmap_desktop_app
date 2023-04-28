package main.app.mapRepository.composite;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.observer.Publisher;
import main.app.gui.swing.observer.Subscriber;
import main.app.mapRepository.implementation.Element;
import main.app.messageGenerator.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MapNodeComposite.class, name = "MapNodeComposite"),
        @JsonSubTypes.Type(value = Element.class, name = "Element")
})
public abstract class MapNode implements Publisher {

    private String name;
    @ToString.Exclude
    private transient MapNode parent;
    private transient List<Subscriber> subscribers;

    public MapNode(){}

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapNode mapNode = (MapNode) o;
        return Objects.equals(name, mapNode.name);
    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new LinkedList<>();
        if(this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
    }
    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;
        this.subscribers.remove(subscriber);
    }
    @Override
    public void notify(MyNotification notification) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(Subscriber subscriber : subscribers){
            subscriber.update(notification);
        }
    }
    @Override
    public void notifyView(MyNotification notification, Object o) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(Subscriber subscriber : subscribers){
            subscriber.updateView(notification, o);
        }
    }
    @Override
    public void notifyMessage(Message message) {}
    @Override
    public void notifyInfo(Message message) {}

}
