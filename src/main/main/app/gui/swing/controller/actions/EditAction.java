package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.dialog.RenameDialog;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;
import main.app.mapRepository.implementation.ProjectExplorer;

import java.awt.event.ActionEvent;

public class EditAction extends MyAbstractAction {

    private String editPic = "/images/edit.png";

    public EditAction(){
        putValue(NAME, "Edit");
        putValue(SMALL_ICON, loadIcon(editPic));
        putValue(SHORT_DESCRIPTION, "Edit");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        try{
            if(selected.getMapNode() instanceof Project || selected.getMapNode() instanceof MindMap){
                RenameDialog renameDialog = new RenameDialog(MainFrame.getInstance(), loadIcon(editPic));
                renameDialog.setVisible(true);
                String name = renameDialog.getNewName();

                for (MapNode child: ((MapNodeComposite) selected.getMapNode().getParent()).getChildren()){
                    if(child.getName().equals(name)){
                        ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_RENAME);
                        return;
                    }
                }
                if(!name.equals(""))
                    selected.getMapNode().setName(name);
                else
                    ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_RENAME);

            }else if(selected.getMapNode() instanceof ProjectExplorer)
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_EDIT_EXPLORER);

        }
        catch(NullPointerException exception){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }
    }
}
