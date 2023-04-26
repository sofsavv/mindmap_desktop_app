package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomAction extends MyAbstractAction {

    public ZoomAction(){
        putValue(NAME, "Zoom");
        putValue(SMALL_ICON, loadIcon("/images/zoom.png"));
        putValue(SHORT_DESCRIPTION, "Zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startZoomState();
        System.out.println("... in zoom state ...");
    }
}
