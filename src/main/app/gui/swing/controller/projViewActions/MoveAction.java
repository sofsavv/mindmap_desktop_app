package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveAction extends MyAbstractAction {
    public MoveAction(){
        putValue(NAME, "Move");
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(SHORT_DESCRIPTION, "Move");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startMoveState();
        System.out.println("... in move state ...");
    }
}
