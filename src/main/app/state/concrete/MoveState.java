package main.app.state.concrete;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.mindMapView.ConceptPaint;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.gui.swing.view.mindMapView.Painter;
import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.command.concrete.MoveElementCommand;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State {

    private Point press;
    private Point startPoint;
    private java.util.List<Point> startPoints = new ArrayList<>();
    private java.util.List<Point> endPoints = new ArrayList<>();
    private List<Element> selected = new ArrayList<>();
    private int kx, ky;

    private void undo(MindMapView mapView){
        int i = 0;
        for(Element element: mapView.getSelectedElements()){
            if(element instanceof Concept) {
                ((Concept) element).setPoint(startPoints.get(i));
                ((Concept) element).setLinkPoints(startPoints.get(i));
                i++;
            }
        }
    }

    @Override
    public void myMousePressed(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();

        mapView.setCurrent();
        startPoints.clear();
        endPoints.clear();

        press = new Point(x, y);
        if(mapView.getSelected().isEmpty()) {
            startPoint = new Point(x, y);
        }else{
            for(Element element: mapView.getSelectedElements()){
                if(element instanceof Concept)
                    startPoints.add(new Point(((Concept) element).getPoint().x ,((Concept) element).getPoint().y));
            }
        }
    }

    @Override
    public void myMouseDragged(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();

        if(mapView.getSelected().isEmpty()){
            mapView.setXOffset(mapView.getXOffset() + (x - startPoint.x));
            mapView.setYOffset(mapView.getYOffset() + (y - startPoint.y));
            mapView.repaint();
            startPoint.x = x;
            startPoint.y = y;

        }else{
            selected = mapView.getSelectedElements();
            kx = x - press.x;
            ky = y - press.y;

            for(Element el: mapView.getSelectedElements()){
                if(el instanceof Concept){
                    double nx = ((Concept)el).getPoint().getX();
                    double ny = ((Concept)el).getPoint().getY();
                    nx += kx;
                    ny += ky;
                    Point newPoint = new Point((int)nx, (int)ny);
                    ((Concept) el).setPoint(newPoint);
                    ((Concept) el).setLinkPoints(newPoint);
                }
            }
            press.x += kx;
            press.y += ky;
        }
    }

    @Override
    public void myMouseReleased(int x, int y, MindMapView mapView) {

        for(Painter painter: mapView.getPainters()){
            for(Painter moved: mapView.getSelected()){
                if(painter instanceof ConceptPaint && moved instanceof ConceptPaint && !painter.equals(moved)){
                    Rectangle2D rect = new Rectangle(((ConceptPaint) painter).getConcept().getPoint().x,
                            ((ConceptPaint) painter).getConcept().getPoint().y, (int) ((ConceptPaint) painter).getConcept().getW(),
                            (int) ((ConceptPaint) painter).getConcept().getH());
                    if (((ConceptPaint) moved).getShape().intersects(rect)){
                        ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.OVERLAP);
                        undo(mapView);
                        return;
                    }
                }
            }
        }
        for(Painter p: mapView.getSelected()){
            if(p instanceof ConceptPaint)
                endPoints.add(new Point(((ConceptPaint) p).getConcept().getPoint().x ,((ConceptPaint) p).getConcept().getPoint().y));
        }
        AbstractCommand command = new MoveElementCommand(selected, startPoints, endPoints);
        mapView.getMindMap().getCommandManager().addCommand(command);
    }

    @Override
    public void myWheelMoved(MindMapView mapView, MouseWheelEvent e) {}
    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {}

}
