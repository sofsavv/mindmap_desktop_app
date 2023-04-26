package main.app.state;

import main.app.gui.swing.view.mindMapView.MindMapView;

import java.awt.event.MouseWheelEvent;

public abstract class State {
    public abstract void myMouseClicked(int x, int y, MindMapView mapView);
    public abstract void myMouseDragged(int x, int y, MindMapView mapView);
    public abstract void myMousePressed(int x, int y, MindMapView mapView);
    public abstract void myMouseReleased(int x, int y, MindMapView mapView);
    public abstract void myWheelMoved(MindMapView mapView, MouseWheelEvent e);
}
