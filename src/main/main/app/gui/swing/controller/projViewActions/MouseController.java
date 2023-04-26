package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.mindMapView.MindMapView;

import java.awt.event.*;

public class MouseController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private MindMapView mapView;

    public MouseController(MindMapView mapView){
        this.mapView = mapView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getCurrProjectView().getStateManager().getCurrentState().myMouseClicked(e.getX(), e.getY(), mapView);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getCurrProjectView().getStateManager().getCurrentState().myMousePressed(e.getX(), e.getY(), mapView);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getCurrProjectView().getStateManager().getCurrentState().myMouseReleased(e.getX(), e.getY(), mapView);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getCurrProjectView().getStateManager().getCurrentState().myMouseDragged(e.getX(), e.getY(), mapView);
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getCurrProjectView().getStateManager().getCurrentState().myWheelMoved(mapView, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}

}
