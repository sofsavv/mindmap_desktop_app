package main.app.gui.swing.controller.actions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends MyAbstractAction {
    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(NAME, "Redo");
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getCurrProjectView().getCurrMapView() != null)
            MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getCommandManager().doCommand();
    }
}
