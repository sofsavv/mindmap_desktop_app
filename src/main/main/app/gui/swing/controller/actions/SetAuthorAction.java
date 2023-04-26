package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.dialog.AuthorDialog;
import main.app.mapRepository.implementation.Project;

import java.awt.event.ActionEvent;

public class SetAuthorAction extends MyAbstractAction {
    public SetAuthorAction(){
        putValue(NAME, "Set Author");
        putValue(SHORT_DESCRIPTION, "Author");
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem node = MainFrame.getInstance().getMapTree().getSelectedNode();
        try {
            if(node.getMapNode() instanceof Project){
                AuthorDialog authorDialog = new AuthorDialog(MainFrame.getInstance(),loadIcon("/images/person.png"));
                authorDialog.setVisible(true);
                String str = authorDialog.getAuthor();
                ((Project) node.getMapNode()).setAuthor(str);
                System.out.println("action: " + str);
            }else{
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_ADD_AUTHOR);
            }
        }catch (NullPointerException nullE){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }

    }
}
