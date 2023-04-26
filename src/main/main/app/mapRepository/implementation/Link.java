package main.app.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;

import java.awt.*;
@Getter
@Setter
public class Link extends Element{
    private Concept from, to;
    private Point bestFrom, bestTo;

    public Link(){}

    public Link(String name, MapNode parent, Concept from, Concept to) {
        super(name, parent);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Link){
            Link link = (Link) obj;
            if((link.getFrom().equals(this.getFrom()) && link.getTo().equals(this.getTo())) ||
                    (link.getTo().equals(this.getFrom()) && link.getFrom().equals(this.getTo())))
                return true;
            else if(link.getName().equals(this.getName()))
                return true;
        }
        return false;
    }

    public void setBestTo(Point bestTo) {
        this.bestTo = bestTo;
        notifyView(MyNotification.REPAINT, this);
    }

    public void setBestFrom(Point bestFrom) {
        this.bestFrom = bestFrom;
        notifyView(MyNotification.REPAINT, this);
    }
}
