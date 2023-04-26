package main.app.state.concrete;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.view.mindMapView.ConceptPaint;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.gui.swing.view.mindMapView.Painter;
import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.command.concrete.LinkCommand;
import main.app.mapRepository.implementation.Concept;
import main.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Line2D;

@Getter
@Setter
public class LinkState extends State {

    private Concept cFrom = null;
    private Concept cTo = null;
    private Point point;
    private boolean drag;

    @Override
    public void myMousePressed(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();
        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        mapView.unselect();
        mapView.setCurrent();

        point = new Point(x,y);

        for (Painter paint : mapView.getPainters()) {
            if (paint instanceof ConceptPaint) {
                if(paint.elementAt(point)){
                    cFrom = ((ConceptPaint) paint).getConcept();
                    drag = true;
                    return;
                }
            }
        }
    }

    @Override
    public void myMouseDragged(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();
        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        if(drag){
            Line2D line = new Line2D.Double((cFrom.getPoint().x + cFrom.getW()/2), (cFrom.getPoint().y + cFrom.getH()/2), x,y);
            mapView.setLine(line);
            mapView.repaint();
        }
    }

    @Override
    public void myMouseReleased(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();
        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        mapView.setLine(null);
        mapView.repaint();

        point = new Point(x,y);

        for(Painter paint: mapView.getPainters()){
            if(paint instanceof ConceptPaint && cFrom != null) {
                if (paint.elementAt(point)) {
                    cTo = ((ConceptPaint) paint).getConcept();
                    AbstractCommand command = new LinkCommand(mapView.getMindMap(), cFrom, cTo);
                    mapView.getMindMap().getCommandManager().addCommand(command);
                    return;
                }
            }
        }
        drag = false;
        cFrom = null;
        mapView.getMindMap().setChanged(true);
    }

    @Override
    public void myWheelMoved(MindMapView mapView, MouseWheelEvent e) {}

    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {}

}
