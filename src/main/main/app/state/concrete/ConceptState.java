package main.app.state.concrete;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.dialog.NewConceptDialog;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.command.concrete.ConceptCommand;
import main.app.mapRepository.composite.MapNode;
import main.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class ConceptState extends State {

    @Override
    public void myMouseClicked(int x, int y, MindMapView mapView) {

        x /= mapView.getZoomFactor();
        y /= mapView.getZoomFactor();
        x -= (mapView.getXOffset() / mapView.getZoomFactor());
        y -= (mapView.getYOffset() / mapView.getZoomFactor());

        mapView.unselect();
        mapView.setCurrent();

        NewConceptDialog dialog = new NewConceptDialog(MainFrame.getInstance());
        dialog.setVisible(true);

        if(!dialog.isCant_add()){
            for(MapNode element: mapView.getMindMap().getChildren()){
                if(element.getName().equals(dialog.getConceptName())){
                    ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.EXISTS);
                    return;
                }
            }
            Point point = new Point(x, y);
            AbstractCommand command = new ConceptCommand(dialog.getConceptName(), mapView.getMindMap(), point);
            mapView.getMindMap().getCommandManager().addCommand(command);
            mapView.getMindMap().setChanged(true);
        }
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
