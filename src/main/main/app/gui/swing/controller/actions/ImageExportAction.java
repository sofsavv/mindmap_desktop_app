package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.MindMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;

public class ImageExportAction extends MyAbstractAction {
    public ImageExportAction() {
        putValue(NAME, "Export");
        putValue(SMALL_ICON, loadIcon("/images/image.png"));
        putValue(SHORT_DESCRIPTION, "Export as image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        String name = "project_map";

        if(selected != null){
            MapNode node = selected.getMapNode();
            if(node instanceof MindMap){
                name = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getName();
                exportImage(name, (MindMap) node);
            }else
                takeOpenedTab(name);
        }else if(MainFrame.getInstance().getCurrProjectView().getCurrMapView() != null)
            takeOpenedTab(name);
        else {
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);
            return;
        }
        ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.SAVED);
    }

    private void exportImage(String name, MindMap mindMap){

        JPanel panel = MainFrame.getInstance().getCurrProjectView().getCurrMapView();
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.paint(img.getGraphics());
        try {
            String pathname = mindMap.getParent().getName().replaceAll(" ", "") + "_" + name.replaceAll(" ", "");
            pathname = pathname.toLowerCase(Locale.ROOT) + ".png";

            File file = new File(pathname);
            ImageIO.write(img, "png", file);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    private void takeOpenedTab(String name){
        int i =  MainFrame.getInstance().getCurrProjectView().getTabbedPane().getSelectedIndex();
        MindMapView mapView = MainFrame.getInstance().getCurrProjectView().getMapViewList().get(i);
        MainFrame.getInstance().getCurrProjectView().setCurrMapView(mapView);
        name = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getName();
        exportImage(name, MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap());
    }
}
