package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteElementAction extends MyAbstractAction {
    public DeleteElementAction(){
        putValue(NAME, "Delete element");
        putValue(SMALL_ICON, loadIcon("/images/delete_element.png"));
        putValue(SHORT_DESCRIPTION, "Delete element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startDeleteState();
        System.out.println("... in delete state ...");
    }
}
