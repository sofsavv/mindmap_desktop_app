package main.app.gui.swing.view.mindMapView;

import lombok.Getter;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.Link;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
public class LinkPaint extends Painter{

    private Link link;
    private Shape shape;

    public LinkPaint(Element element){
        super(element);
        link = (Link) element;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(link.getColor());
        g2D.setStroke(new BasicStroke(2));
        g2D.setStroke(new BasicStroke(link.getWidth()));
        findBest(link);
        shape = new Line2D.Double(link.getBestFrom(), link.getBestTo());
        g2D.draw(shape);
    }

    private int minDistance(Point pF, Concept pT){
        int dW = distance(pF, pT.getWest());
        int dN = distance(pF, pT.getNorth());
        int dE = distance(pF, pT.getEast());
        int dS = distance(pF, pT.getSouth());
        return Math.min(Math.min(dW, dN), Math.min(dE, dS));
    }

    private int distance(Point p1, Point p2){
        return (int) Math.sqrt((p2.x- p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
    }

    public void findBest(Link link){
        for(Point pTo: link.getTo().getPoints()) {
            if (minDistance(link.getFrom().getPoint(), link.getTo()) == distance(link.getFrom().getPoint(), pTo)) {
                link.setBestTo(pTo);
                break;
            }
        }
        for(Point pFrom: link.getFrom().getPoints()) {
            if (minDistance(link.getBestTo(), link.getFrom()) == distance(link.getBestTo(), pFrom)) {
                link.setBestFrom(pFrom);
                break;
            }
        }
    }

    @Override
    public boolean elementAt(Point point) {
        if(shape.contains(point))
            return true;
        return false;
    }
}
