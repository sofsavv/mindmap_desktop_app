package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends MyAbstractAction {

    public SelectAction(){
        putValue(NAME, "Select");
        putValue(SMALL_ICON, loadIcon("/images/select.png"));
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startSelectState();
        System.out.println("... in select state ...");
    }

}
