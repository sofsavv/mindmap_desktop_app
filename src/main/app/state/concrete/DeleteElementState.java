package main.app.state.concrete;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.command.concrete.DeleteElementCommand;
import main.app.state.State;

import java.awt.event.MouseWheelEvent;

public class DeleteElementState extends State {

    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();
        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());
        mapView.setCurrent();

        if(mapView.getSelected().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);
        }else{
            AbstractCommand command = new DeleteElementCommand(mapView.getSelectedElements(), mapView.getMindMap());
            mapView.getMindMap().getCommandManager().addCommand(command);
            mapView.getFrames().clear();
        }
        mapView.getMindMap().setChanged(true);
    }
    @Override
    public void myMouseDragged(int x, int y, MindMapView mapView) {}
    @Override
    public void myMousePressed(int x, int y, MindMapView mapView) {}
    @Override
    public void myMouseReleased(int x, int y, MindMapView mapView) {}
    @Override
    public void myWheelMoved(MindMapView mapView, MouseWheelEvent e) {}
}
