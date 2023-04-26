package main.app.state.concrete;

import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.state.State;

import java.awt.event.MouseWheelEvent;

public class ZoomState extends State {

    @Override
    public void myWheelMoved(MindMapView mapView, MouseWheelEvent e) {

        mapView.setCurrent();
        mapView.unselect();

        if (e.getWheelRotation() < 0) {
            mapView.setZoomFactor(mapView.getZoomFactor() * 1.1D);
            mapView.repaint();
        }
        if (e.getWheelRotation() > 0) {
            mapView.setZoomFactor(mapView.getZoomFactor() / 1.1D);
            mapView.repaint();
        }
    }
    @Override
    public void myMousePressed(int x, int y, MindMapView mapView) {}

    @Override
    public void myMouseDragged(int x, int y, MindMapView mapView) {}

    @Override
    public void myMouseReleased(int x, int y, MindMapView mapView) {}

    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {}
}
