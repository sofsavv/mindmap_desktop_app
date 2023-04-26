package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.implementation.MindMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends MyAbstractAction {
    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(NAME, "New");
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
            if(selected.getMapNode() instanceof MindMap)
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_ADD_CHILD);
            else
                MainFrame.getInstance().getMapTree().addChild(selected);

        }catch (NullPointerException nulE){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }
    }
}
