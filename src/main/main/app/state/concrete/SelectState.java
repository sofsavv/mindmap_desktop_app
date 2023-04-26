package main.app.state.concrete;

import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.mindMapView.*;
import main.app.mapRepository.implementation.Element;
import main.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class SelectState extends State {

    private Point start;
    private Point movable;
    private Painter selectPainter;
    private Element element;

    private void singleSelect(Painter painter, MindMapView mapView){

        mapView.setCurrent();

        if(painter instanceof ConceptPaint){
            selectPainter = new SelectPaint(((ConceptPaint)painter).getConcept());
            element = ((ConceptPaint)painter).getConcept();
        }else{
            selectPainter = new SelectPaint(((LinkPaint)painter).getLink());
            element = ((LinkPaint)painter).getLink();
        }
        mapView.getFrames().add(selectPainter);
        mapView.getSelected().add(painter);
        mapView.getSelectedElements().add(element);
        mapView.update(MyNotification.SELECT);
        mapView.getMindMap().setChanged(true);
    }

    private void unselect(MindMapView mapView){
        mapView.getSelected().clear();
        mapView.getFrames().clear();
        mapView.getSelectedElements().clear();
        mapView.update(MyNotification.SELECT);
        mapView.getMindMap().setChanged(true);
    }

    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();

        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        Point point = new Point(x,y);
        for (Painter painter: mapView.getPainters()) {
            if (painter.elementAt(point)) {
                if (mapView.getSelected().isEmpty()) {
                    singleSelect(painter, mapView);
                    return;
                } else
                    unselect(mapView);
                return;
            }
        }
    }

    @Override
    public void myMousePressed(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();

        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        start = new Point(x, y);
        if(!mapView.getSelected().isEmpty())
            unselect(mapView);
    }

    @Override
    public void myMouseDragged(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();

        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        unselect(mapView);

        movable = new Point(x, y);

        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(start, movable);

        mapView.setRectangle(rectangle);

        for(Painter painter : mapView.getPainters()){
            if(painter instanceof ConceptPaint && rectangle.intersects(((ConceptPaint)painter).getShape().getBounds2D()))
                singleSelect(painter, mapView);
            else if(painter instanceof LinkPaint && rectangle.intersects(((LinkPaint)painter).getShape().getBounds2D()))
                singleSelect(painter, mapView);
        }
        mapView.repaint();
    }

    @Override
    public void myMouseReleased(int x, int y, MindMapView mapView) {
        mapView.setRectangle(null);
        mapView.repaint();
        mapView.getMindMap().setChanged(true);
    }

    @Override
    public void myWheelMoved(MindMapView mapView, MouseWheelEvent e) {}
}
