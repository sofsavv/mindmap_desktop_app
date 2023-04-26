package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveAction extends MyAbstractAction {
    public RemoveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(NAME, "Delete");
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        try{
            if(selected.getMapNode() instanceof ProjectExplorer)
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_DELETE_NODE);
            else
                MainFrame.getInstance().getMapTree().deleteChild(selected);

        }catch (NullPointerException nullPointerException){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }

    }
}
