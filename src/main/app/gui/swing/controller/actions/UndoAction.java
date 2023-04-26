package main.app.gui.swing.controller.actions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends MyAbstractAction {
    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(NAME, "Undo");
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getCurrProjectView().getCurrMapView() != null)
            MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getCommandManager().undoCommand();
    }
}
