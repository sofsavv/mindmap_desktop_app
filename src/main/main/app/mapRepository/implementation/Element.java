package main.app.mapRepository.implementation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;

import java.awt.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Concept.class, name = "Concept"),
        @JsonSubTypes.Type(value = Link.class, name = "Link")
})

@Getter
@Setter
public class Element extends MapNode {
    private int width;
    private Color color;

    public Element(){}

    public Element(String name, MapNode parent) {
        super(name, parent);
        this.setColor(Color.BLACK);
        this.setWidth(2);
        notifyView(MyNotification.NEW, this);
    }

    public void setWidth(int width) {
        this.width = width;
        notifyView(MyNotification.EDIT, this);
    }

    public void setColor(Color color) {
        this.color = color;
        notifyView(MyNotification.EDIT, this);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        notifyView(MyNotification.EDIT, this);
    }
}
