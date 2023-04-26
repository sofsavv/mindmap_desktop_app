package main.app.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Concept extends Element{

    private Point point;
    private double w, h;
    private Point north, east, south, west;
    private List<Point> points;

    public Concept(){}

    public Concept(String text, MapNode parent, Point point, double w, double h) {
        super(text, parent);
        this.point = point;
        this.w = w;
        this.h = h;
        points = new LinkedList<>();
    }

    public void setLinkPoints(Point point){
        points.clear();
        north = new Point(point.x + (int) this.w / 2, point.y);
        points.add(north);
        east = new Point(point.x + (int) this.w, point.y + (int) this.h / 2);
        points.add(east);
        south = new Point(point.x + (int) this.w / 2, point.y + (int)this.h);
        points.add(south);
        west = new Point(point.x, point.y + (int) this.h / 2);
        points.add(west);
        notifyView(MyNotification.MOVE, this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Concept){
            Concept concept = (Concept) obj;
            if(concept.getName().equals(this.getName()))
                return true;
        }
        return false;
    }

    public void setPoint(Point point) {
        this.point = point;
        notifyView(MyNotification.MOVE, this);
    }

}
